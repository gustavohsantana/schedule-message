package com.api.desafio.controller;

import com.api.desafio.domain.Communication;
import com.api.desafio.domain.ResponseMessage;
import com.api.desafio.dto.CommunicationDTO;
import com.api.desafio.enums.CommunicationType;
import com.api.desafio.enums.Status;
import com.api.desafio.repository.CommuncationRepository;
import com.api.desafio.service.CommunicationService;
import com.api.desafio.service.CommunicationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CommunicationController.class)
@ContextConfiguration(classes={CommunicationServiceImpl.class,CommunicationController.class})
public class CommunicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunicationService communicationService;

    @MockBean
    private CommuncationRepository communicationRepository;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void givenCommunication_whenCreateCommunication_thenReturnJson() throws Exception{

        Communication communication = new Communication();
        communication.setCommunicationType(CommunicationType.EMAIL);
        communication.setDestinatario("teste@domain.com");
        communication.setData(new Date());
        communication.setStatus(Status.NAO_ENVIADO);

        CommunicationDTO communicationDTO = new CommunicationDTO();
        communicationDTO.setData( new Date());
        communicationDTO.setDestinatario("1129292929");
        communicationDTO.setTipoCommunicacao(CommunicationType.SMS);
        communicationDTO.setStatus(Status.ENVIADO);

        Mockito.when(communicationService.createMessage(Mockito.any())).thenReturn(communication);
        Mockito.when(communicationRepository.save(communication)).thenReturn(communication);

        mockMvc.perform(post("/communication/scheduleMessage")
                        .content(new ObjectMapper().writeValueAsString(communicationDTO))
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("communication.destinatario").value(communication.getDestinatario()));
    }

    @Test
    public void givenCommunicationId_whenCFindCommunication_thenReturnCommunication() throws Exception{
        Communication communication = new Communication();
        ResponseMessage responseMessage = new ResponseMessage(communication);

        Mockito.when(communicationService.findMessage(Mockito.anyLong())).thenReturn(communication);

        mockMvc.perform(get("/communication/findMessage",communication.getId())
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("communication",is(responseMessage.getCommunication())));
    }

    @Test
    public void givenCommunicationId_whenCFindCommunication_thenReturnNotFound() throws Exception{
        Mockito.when(communicationService.findMessage(Mockito.anyLong())).thenReturn(null);

        mockMvc.perform(get("/communication/findMessage",Mockito.anyLong())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("communication",is(null)));
    }



    @Test
    public void givenCommunication_whenDeleteCommunication_thenReturnTrueForDeleted() throws Exception{
        Communication communication = new Communication();
        communication.setCommunicationType(CommunicationType.EMAIL);
        communication.setDestinatario("teste@domain.com");
        communication.setData(new Date());
        communication.setStatus(Status.NAO_ENVIADO);

        CommunicationDTO communicationDTO = new CommunicationDTO();communicationDTO.setId(1L);

        Mockito.when(communicationService.deleteMessage(Mockito.any())).thenReturn(true);

        mockMvc.perform(get("/communication/deleteMessage", communicationDTO.getId())
                .content(new ObjectMapper().writeValueAsString(communicationDTO.getId()))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("communication",is(true)));
    }

    @Test
    public void givenCommunication_whenDeleteCommunication_thenReturnFalseForNotDeleted() throws Exception{
        Communication communication = new Communication();
        communication.setCommunicationType(CommunicationType.EMAIL);
        communication.setDestinatario("teste@domain.com");
        communication.setData(new Date());
        communication.setStatus(Status.NAO_ENVIADO);

        CommunicationDTO communicationDTO = new CommunicationDTO();

        Mockito.when(communicationService.deleteMessage(Mockito.any())).thenReturn(false);

        mockMvc.perform(get("/communication/deleteMessage", communicationDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("communication",is(false)));
    }

}

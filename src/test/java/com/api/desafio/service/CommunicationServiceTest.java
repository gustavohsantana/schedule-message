package com.api.desafio.service;

import com.api.desafio.domain.Communication;
import com.api.desafio.domain.ResponseMessage;
import com.api.desafio.dto.CommunicationDTO;
import com.api.desafio.enums.CommunicationType;
import com.api.desafio.enums.Status;
import com.api.desafio.repository.CommuncationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
public class CommunicationServiceTest {

        @TestConfiguration
        static class ZipCodeServiceImplTestConfiguration{
            @Autowired
            private CommuncationRepository communcationRepository;

            @Bean
            public CommunicationService zipCodeService(){
                return new CommunicationServiceImpl(communcationRepository);
            }

        }

        @Autowired
        private CommunicationService communicationService;

        Communication communication;

        CommunicationDTO communicationDTO;

        ResponseMessage responseMessage;

        @MockBean
        private CommuncationRepository communcationRepository;

        @Before
        public void setup(){
            communication = new Communication();
            communication.setCommunicationType(CommunicationType.EMAIL);
            communication.setId(1L);
            communication.setDestinatario("teste@domain.com");
            communication.setData( new Date());
            communication.setStatus(Status.NAO_ENVIADO);

            communicationDTO = new CommunicationDTO();
            communicationDTO.setId(1L);
            communicationDTO.setData( new Date());
            communicationDTO.setDestinatario("1129292929");
            communicationDTO.setTipoCommunicacao(CommunicationType.SMS);
            communicationDTO.setStatus(Status.ENVIADO);

            responseMessage = new ResponseMessage(communication);

            Mockito.when(communcationRepository.findById(communication.getId())).thenReturn(java.util.Optional.ofNullable((communication)));
            Mockito.when(communcationRepository.existsById(communication.getId())).thenReturn(true);
            Mockito.when(communcationRepository.save(Mockito.any())).thenReturn(communication);

        }

        @Test
        public void whenGivenCommunication_createCommunication(){
            Assert.assertEquals(communication,
                    communicationService.createMessage(communicationDTO));
        }

        @Test
        public void whenGivenCommunicationId_findCommunication(){
            Assert.assertEquals(communication,
                    communicationService.findMessage(communication.getId()));
        }

        @Test
        public void whenUpdateCommunicationStatus_thenReturnUpdatedStatus(){
            communication.setStatus(Status.ENVIADO);

            Assert.assertEquals(Status.ENVIADO,
                    communicationService.updateMessage(communicationDTO).getStatus());
        }

        @Test
        public void whenGivenCommunicationIdForDelete_thenReturnDeletedResponse(){

            Mockito.when(communcationRepository.existsById(communication.getId())).thenReturn(true);

            Assert.assertEquals(true,
                    communicationService.deleteMessage(communicationDTO.getId()));
        }

    }

package com.api.desafio.service;

import com.api.desafio.domain.Communication;
import com.api.desafio.domain.ResponseMessage;
import com.api.desafio.dto.CommunicationDTO;
import com.api.desafio.enums.Status;
import com.api.desafio.repository.CommuncationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommunicationServiceImpl implements CommunicationService{

    @Autowired
    private final CommuncationRepository communcationRepository;

    public Communication createMessage(CommunicationDTO communicationDTO) {

        Communication communication = new Communication();
        communication.setCommunicationType(communicationDTO.getTipoCommunicacao());
        communication.setData(communicationDTO.getData());
        communication.setDestinatario(communicationDTO.getDestinatario());
        communication.setStatus(Status.NAO_ENVIADO);

        communication = communcationRepository.save(communication);

        return communication;
    }

    public Communication updateMessage(CommunicationDTO communicationDTO) {

        Communication communication = communcationRepository.findById(communicationDTO.getId()).get();

        communication.setCommunicationType(communicationDTO.getTipoCommunicacao());
        communication.setData(communicationDTO.getData());
        communication.setDestinatario(communicationDTO.getDestinatario());
        communication.setStatus(communicationDTO.getStatus());

        communication = communcationRepository.save(communication);

        return communication;
    }

    public Communication findMessage(Long id) {

        if(communcationRepository.existsById(id)) {
            return communcationRepository.findById(id).get();
        }
        else {
            return null;
        }
    }

    public boolean deleteMessage(Long id) {

        if(communcationRepository.existsById(id)) {
            Communication communication = communcationRepository.findById(id).get();
            communcationRepository.delete(communication);
        }

        return communcationRepository.existsById(id);
    }
}

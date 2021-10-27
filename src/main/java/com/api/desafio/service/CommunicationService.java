package com.api.desafio.service;

import com.api.desafio.domain.Communication;
import com.api.desafio.domain.ResponseMessage;
import com.api.desafio.dto.CommunicationDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommunicationService {

    public Communication createMessage(CommunicationDTO communicationDTO);

    public Communication updateMessage(CommunicationDTO communicationDTO);

    public Communication findMessage(Long id);

    public boolean deleteMessage(Long id);
}

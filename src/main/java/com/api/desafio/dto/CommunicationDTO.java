package com.api.desafio.dto;

import com.api.desafio.enums.CommunicationType;
import com.api.desafio.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommunicationDTO {

    private Long id;
    private Date data;
    private String destinatario;
    private CommunicationType tipoCommunicacao;
    private Status status;

}

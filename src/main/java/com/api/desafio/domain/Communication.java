package com.api.desafio.domain;

import com.api.desafio.enums.CommunicationType;
import com.api.desafio.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "COMMUNICATION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "COM_TYPE")
    private CommunicationType communicationType;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "DESTINATARIO")
    private String destinatario;

    @Column(name = "DATA")
    private Date data;

}

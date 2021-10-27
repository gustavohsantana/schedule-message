package com.api.desafio.repository;

import com.api.desafio.DesafioApplication;
import com.api.desafio.domain.Communication;
import com.api.desafio.enums.CommunicationType;
import com.api.desafio.enums.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes={DesafioApplication.class})
public class CommunicationRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommuncationRepository communcationRepository;

    @Test
    public void whenFindByPostalCode_thenReturnZipCode(){
        Communication communication = new Communication();
        communication.setCommunicationType(CommunicationType.EMAIL);
        communication.setDestinatario("teste2@domain.com");
        communication.setData(new Date());
        communication.setStatus(Status.NAO_ENVIADO);

        testEntityManager.persist(communication);
        testEntityManager.flush();

        Assert.assertEquals(communcationRepository.save(communication), communication);
    }
}
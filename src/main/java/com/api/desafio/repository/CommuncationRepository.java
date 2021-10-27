package com.api.desafio.repository;

import com.api.desafio.domain.Communication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuncationRepository extends CrudRepository<Communication, Long> {

}

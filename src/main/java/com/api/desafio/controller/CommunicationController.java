package com.api.desafio.controller;

import com.api.desafio.domain.Communication;
import com.api.desafio.domain.ResponseMessage;
import com.api.desafio.dto.CommunicationDTO;
import com.api.desafio.service.CommunicationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/communication")
@AllArgsConstructor
public class CommunicationController {

    @Autowired
    private CommunicationService communicationService;

    @PostMapping(value = "/scheduleMessage")
    public ResponseEntity<ResponseMessage> scheduleMessage(@RequestBody CommunicationDTO communicationDTO){
        return new ResponseEntity<>(new ResponseMessage(communicationService.createMessage(communicationDTO)), HttpStatus.OK);
    }

    @GetMapping(value = "/findMessage")
    @ResponseBody
    public ResponseEntity<ResponseMessage> findMessage(@PathVariable Long id){
        return ok().body(new ResponseMessage(communicationService.findMessage(id)));
    }

    @PutMapping(value = "/updateMessage")
    public ResponseEntity<ResponseMessage> updateMessage(@RequestBody CommunicationDTO communicationDTO){
        return ok().body(new ResponseMessage(communicationService.updateMessage(communicationDTO)));
    }

    @DeleteMapping(value = "/deleteMessage")
    public ResponseEntity<Boolean> deleteMessage(@PathVariable Long id){
        return ok().body(communicationService.deleteMessage(id));
    }

}

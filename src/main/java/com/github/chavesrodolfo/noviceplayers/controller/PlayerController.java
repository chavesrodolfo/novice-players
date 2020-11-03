package com.github.chavesrodolfo.noviceplayers.controller;

import com.github.chavesrodolfo.noviceplayers.model.MessageResponse;
import com.github.chavesrodolfo.noviceplayers.model.PlayersRequest;
import com.github.chavesrodolfo.noviceplayers.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlayerController {
    
    @Autowired
    PlayerService playerService;

    @GetMapping("/hello")
    public String hello() {
      return "Novice Players API";
    }

    @PostMapping("/players")
    public MessageResponse postPlayer(@RequestBody PlayersRequest playersRequest) {
		MessageResponse messageResponse = playerService.register(playersRequest);
		return messageResponse;
	}
}

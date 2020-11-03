package com.github.chavesrodolfo.noviceplayers.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.chavesrodolfo.noviceplayers.model.MessageResponse;
import com.github.chavesrodolfo.noviceplayers.model.Player;
import com.github.chavesrodolfo.noviceplayers.model.PlayerRepresentation;
import com.github.chavesrodolfo.noviceplayers.model.PlayersRequest;
import com.github.chavesrodolfo.noviceplayers.repository.PlayerRepository;
import com.github.chavesrodolfo.noviceplayers.service.PlayerProducer;
import com.github.chavesrodolfo.noviceplayers.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
	PlayerProducer playerProducer;

    private static final String EXPERT = "expert";
    private static final String NOVICE = "novice";

    @Override
    public MessageResponse register(PlayersRequest playersRequest) {
        log.info("init - registering players");

        List<String> result = new ArrayList<String>();

        if (playersRequest != null && playersRequest.getPlayers() != null) {

            for (PlayerRepresentation playerRep : playersRequest.getPlayers()) {
                
                if (EXPERT.equals(playerRep.getType())) {
                    log.info("Saving {} in H2 database.", playerRep);
                    
                    Player player = new Player();
                    player.setName(playerRep.getName());
                    player.setType(playerRep.getType());

                    playerRepository.save(player);

                    result.add(String.format("player %s stored in DB", playerRep.getName()));
                    
                } else if (NOVICE.equals(playerRep.getType())) {
                    log.info("Sending {} to the \"novice-players\" topic in Kafka.", playerRep);

                    playerProducer.sendMessage(playerRep.toString());

                    result.add(String.format("player %s sent to Kafka topic", playerRep.getName()));
                } else {
                    log.info("{} does not fit.", playerRep);

                    result.add(String.format("player %s did not fit", playerRep.getName()));
                }
            }
        }

        return new MessageResponse(result);
    }
    
}

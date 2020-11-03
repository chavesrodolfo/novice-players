package com.github.chavesrodolfo.noviceplayers.service;

import com.github.chavesrodolfo.noviceplayers.model.MessageResponse;
import com.github.chavesrodolfo.noviceplayers.model.PlayersRequest;

public interface PlayerService {

    /**
     * Players registration
     * @param playersRequest - list of PlayerRepresentation
     * @return MessageResponse - list of String
     */
    public MessageResponse register(PlayersRequest playersRequest);

}

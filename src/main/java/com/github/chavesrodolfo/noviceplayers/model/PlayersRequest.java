package com.github.chavesrodolfo.noviceplayers.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayersRequest {
    private List<PlayerRepresentation> players;
}

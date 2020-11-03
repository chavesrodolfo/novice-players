package com.github.chavesrodolfo.noviceplayers.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponse {
    private final List<String> result;
}
package com.github.chavesrodolfo.noviceplayers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerRepresentation {
    private String name;
    private String type;

    @Override
    public String toString() {
        return String.format("{ \"name\": \"%s\", \"type\": \"%s\" }", this.name, this.type);
    }
}

package com.github.chavesrodolfo.noviceplayers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Player {

    @Id
	@GeneratedValue
	private Long id;
	private String name;
	private String type;
}

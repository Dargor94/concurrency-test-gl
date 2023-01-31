package org.gl.franciscomasera.oauth2.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private UUID id;
    private String name;
    private Double price;

}

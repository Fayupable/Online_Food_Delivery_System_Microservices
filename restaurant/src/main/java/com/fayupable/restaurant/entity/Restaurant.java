package com.fayupable.restaurant.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "restaurants")
public class Restaurant {
    private String id;
    private String name;
    private String description;
    private String email;
    private String contactNumber;
    private Address address;
}

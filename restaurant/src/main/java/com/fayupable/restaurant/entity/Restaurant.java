package com.fayupable.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String description;
    private String email;
    private String contactNumber;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products;

}

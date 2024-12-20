package com.fayupable.userservice.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}

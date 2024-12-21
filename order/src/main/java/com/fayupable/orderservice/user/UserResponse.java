package com.fayupable.orderservice.user;

public record UserResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}

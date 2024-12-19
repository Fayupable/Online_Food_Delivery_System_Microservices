package com.fayupable.restaurant.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}

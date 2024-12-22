package com.fayupable.notification.email;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment_confirmation.html", "Payment confirmation"),
    ORDER_CONFIRMATION("order_confirmation.html", "Order confirmation");


    @Getter
    private final String templateName;
    @Getter
    private final String subject;

    EmailTemplates(String templateName, String subject) {
        this.templateName = templateName;
        this.subject = subject;
    }
}

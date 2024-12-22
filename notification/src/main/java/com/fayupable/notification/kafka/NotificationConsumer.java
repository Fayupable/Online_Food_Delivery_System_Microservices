package com.fayupable.notification.kafka;

import com.fayupable.notification.email.EmailService;
import com.fayupable.notification.kafka.order.OrderConfirmation;
import com.fayupable.notification.kafka.payment.PaymentConfirmation;
import com.fayupable.notification.notification.Notification;
import com.fayupable.notification.notification.NotificationRepository;
import com.fayupable.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info(format("Consumed payment confirmation: %s", paymentConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .build()
        );

        //todo send email
        var userName = paymentConfirmation.userFirstName() + " " + paymentConfirmation.userLastName();
        try {
            emailService.sendPaymentSuccessEmail(
                    paymentConfirmation.userEmail(),
                    userName,
                    paymentConfirmation.amount(),
                    paymentConfirmation.orderReference()
            );
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) {
        log.info(format("Consumed order confirmation: %s", orderConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //todo send email
        var userName = orderConfirmation.user().firstName() + " " + orderConfirmation.user().lastName();
        try {
            emailService.sendOrderConfirmationEmail(
                    orderConfirmation.user().email(),
                    userName,
                    orderConfirmation.totalAmount(),
                    orderConfirmation.orderReference(),
                    orderConfirmation.products()

            );
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

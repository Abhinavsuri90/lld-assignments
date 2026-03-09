package com.example.payments;

import java.util.Objects;

/**
 * Adapter: wraps FastPayClient to satisfy the PaymentGateway interface.
 * Maps charge(customerId, amountCents) -> FastPayClient.payNow(custId, amountCents).
 */
public class FastPayAdapter implements PaymentGateway {

    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        return client.payNow(customerId, amountCents);
    }
}

package com.example.payments;

import java.util.Objects;

/**
 * Adapter: wraps SafeCashClient to satisfy the PaymentGateway interface.
 * Maps charge(customerId, amountCents) -> SafeCashClient.createPayment(amount, user).confirm().
 */
public class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}

## Payment Validation Service

The **Payment Validation Service** is responsible for validating payment requests before sending them to the Stripe Provider Service.

Currently the system implements a **Duplicate Transaction Validation Rule** to prevent processing the same transaction multiple times.

The validation framework is designed using the **Strategy Design Pattern**, allowing new validation rules to be added easily in the future.

---

## Validation Rule Implemented

### Duplicate Transaction Validation

This rule checks whether the same transaction is already being processed.

Validation Conditions:

- Same `endUserID`
- Same `merchantTxnReference`
- Transaction already in **processing state**

If the transaction already exists, the system **stops further processing and prevents calling the Stripe API**.

### Purpose

- Prevent duplicate payment processing
- Avoid unnecessary calls to Stripe
- Maintain transaction integrity

---

## Validation Configuration (Database Driven)

Validation rules are configured using **SQL scripts** and stored in the database.

The script is executed using **DBeaver** to insert validation rules.

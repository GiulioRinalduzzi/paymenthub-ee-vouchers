# paymenthub-ee-vouchers

The voucher service of Mifos Payment Hub EE: it issues vouchers to beneficiaries, keeps track of their status, and turns a redeemed voucher into a real payment.

[![License](https://img.shields.io/badge/License-MPL--2.0-blue.svg)](LICENSE)

## What it does

- Creates vouchers in batches. One request carries many voucher instructions, and each one becomes a voucher tied to a beneficiary, an amount, a currency and an expiry date.
- Keeps the full life cycle of a voucher: inactive, active, cancelled, expired, utilised, suspended, error. Vouchers can be activated, suspended, reactivated and cancelled through the API.
- Redeems a voucher. When an agent redeems it, the service checks the voucher is valid, marks it as used, and starts the workflow that pays the beneficiary.
- Answers questions about a voucher: fetch one by serial number, list them with paging, read its status, check whether it is still valid.
- Never stores a voucher secret number in the clear. The number arrives encrypted, is decrypted with an RSA key pair, and only its hash is written to the database.
- Checks that every request carries the headers the program requires (`X-Program-Id`, `X-Registering-Institution-ID`, `X-CallbackURL`) and rejects it with a clear error if not.
- Calls back the URL the caller provided, because voucher creation and redemption finish asynchronously.
- Serves an OpenAPI (Swagger) UI so the exposed APIs are easy to explore.

## How it fits into Payment Hub EE

This is the component behind the G2P voucher use case. A program — say a government or aid programme — issues vouchers instead of paying beneficiaries directly. This service is what holds those vouchers and what happens to them.

It works on both sides of Payment Hub. On one side it exposes REST APIs that a channel or a program calls. On the other it takes part in Zeebe workflows: it starts `voucher_budget_check` when a batch of vouchers is created, and `redeem_and_pay_voucher` when a voucher is redeemed. It also runs Zeebe workers that the engine hands work to, so it both starts workflows and serves them.

To do its job it talks to three other components:

- **identity-account-mapper** — to look up which account belongs to the beneficiary being paid.
- **operations-app** — to check whether the transfer that pays out a redeemed voucher actually completed, retrying until it knows.
- **the payment schema** — to authorise the total amount of a voucher batch before the vouchers are handed out.

Its own state lives in a MySQL database (`vouchers` and `error` tables), whose schema is created and versioned by Liquibase.

## Tech stack

- Java 21
- Spring Boot 3.4 (Web, Data JPA, Actuator)
- Apache Camel 4 (Camel Spring Boot, HTTP, Jetty, Undertow)
- Zeebe / Camunda client (workflow orchestration)
- MySQL with Liquibase migrations
- Gradle build
- Depends on `paymenthub-ee-bom` for versions and `paymenthub-ee-core` for shared connector code

## Build

```shell
./gradlew clean bootJar
```

Formatting and static checks:

```shell
./gradlew spotlessApply
./gradlew checkstyleMain
```

## Branches

- `dev` is the active development branch — all PRs should target `dev`.
- `main` holds released versions.

## Contributing

See [contributing.md](contributing.md), our [Code of Conduct](CODE_OF_CONDUCT.md) and the [security policy](security.md).

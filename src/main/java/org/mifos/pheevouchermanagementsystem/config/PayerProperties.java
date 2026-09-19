package org.mifos.pheevouchermanagementsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** The party the voucher budget is drawn from. */
@ConfigurationProperties(prefix = "payer")
public record PayerProperties(String tenant, String identifier, String identifierType) {
}

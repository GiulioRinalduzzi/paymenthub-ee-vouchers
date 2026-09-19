package org.mifos.pheevouchermanagementsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** This service's own address, which it hands to the payment scheme as the callback URL. */
@ConfigurationProperties(prefix = "voucher")
public record VoucherProperties(String hostname) {
}

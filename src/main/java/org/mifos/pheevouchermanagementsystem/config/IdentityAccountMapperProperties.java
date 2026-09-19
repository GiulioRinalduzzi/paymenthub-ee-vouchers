package org.mifos.pheevouchermanagementsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** The account mapper this connector asks where a payee's money should go. */
@ConfigurationProperties(prefix = "identity-account-mapper")
public record IdentityAccountMapperProperties(String hostname) {}

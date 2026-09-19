package org.mifos.pheevouchermanagementsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/** The operations API this connector polls for the transfer status of a redemption. */
@ConfigurationProperties(prefix = "operations")
public record OperationsApiProperties(String hostname, @DefaultValue Endpoints endpoints) {

    public record Endpoints(String transfers) {
    }
}

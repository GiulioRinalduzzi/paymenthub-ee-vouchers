package org.mifos.pheevouchermanagementsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/** Where the Zeebe broker is and how hard to poll it. */
@ConfigurationProperties(prefix = "zeebe")
public record ZeebeProperties(@DefaultValue Broker broker, @DefaultValue Client client) {

    public record Broker(String contactpoint) {
    }

    public record Client(@DefaultValue("50") int maxExecutionThreads, @DefaultValue("10") int pollInterval,
            @DefaultValue("1000") int evenlyAllocatedMaxJobs) {
    }
}

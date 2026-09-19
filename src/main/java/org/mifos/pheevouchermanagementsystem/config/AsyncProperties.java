package org.mifos.pheevouchermanagementsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/** Sizing of the executor behind the {@code asyncExecutor} bean. */
@ConfigurationProperties(prefix = "async")
public record AsyncProperties(@DefaultValue("10") int corePoolSize, @DefaultValue("10") int maxPoolSize,
        @DefaultValue("100") int queueCapacity) {
}

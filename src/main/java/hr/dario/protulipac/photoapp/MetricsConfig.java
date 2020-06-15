package hr.dario.protulipac.photoapp;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {
    @Bean
    public Timer findAllPicturesTimer(MeterRegistry meterRegistry) {
        return Timer.builder("photoapp.timer").register(meterRegistry);
    }
}

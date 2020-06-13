package hr.dario.protulipac.photoapp.aspects;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HitCounterAspect {

    private MeterRegistry meterRegistry;
    private Counter hitCounter;

    public HitCounterAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initCounters();
    }

    private void initCounters() {
        hitCounter = meterRegistry.counter("photoapp-rest", "api", "hits");
    }

    @Pointcut(value = "execution(* hr.dario.protulipac.photoapp.rest.PhotoRestController.*(..))")
    public void countHits() {
    }

    @After(value = "countHits()")
    public void incrementHits() {
        hitCounter.increment(1);
    }


}

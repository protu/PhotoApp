package hr.dario.protulipac.photoapp.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail exercisePrintJobDetail() {
        return JobBuilder.newJob(pictureListJob.class).withIdentity("exercisePrintJob")
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger exercisePrintTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();

        return TriggerBuilder.newTrigger().forJob(exercisePrintJobDetail())
                .withIdentity("exercisePrintTrigger").withSchedule(scheduleBuilder).build();
    }

}

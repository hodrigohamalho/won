package won.task;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Startup
@Singleton
public class MetricsCollector {

    @PostConstruct
    private void startup(){
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail datasource = newJob(DatasourceMetrics.class).build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(40)
                    .repeatForever())
                    .build();

            scheduler.scheduleJob(datasource, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    @PreDestroy
    private void shutdown(){
        System.out.println("SHUTDOWN!!!");
    }


}

package won.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
public class DatasourceMetrics implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Executando o job :)");
    }

}

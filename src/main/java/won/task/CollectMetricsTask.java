package won.task;

import javax.annotation.Resource;
import javax.ejb.*;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Singleton
public class CollectMetricsTask {

    @Resource
    TimerService timerService;

    @Timeout
    public void collectMetrics(Timer timer){
        System.out.println("Entrei no método collectMetrics.");
        System.out.println("Timeout: "+timer.getInfo());
    }

    public void setTimer(long intervalDuration) {
        System.out.println("Setting a programmatic timeout for " + intervalDuration + " milliseconds from now.");
        timerService.createIntervalTimer(100, 1000, new TimerConfig());
    }

}

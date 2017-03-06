package kavya.sample.testapplication.utils;

import rx.Scheduler;

/**
 * Created by ksreeniv on 06/03/17.
 */

public interface ISchedulerProvider {

    Scheduler mainThread();

    Scheduler computation();
}

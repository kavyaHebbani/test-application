package kavya.sample.testapplication;

import kavya.sample.testapplication.utils.ISchedulerProvider;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class TestSchedulerProvider implements ISchedulerProvider{

    @Override
    public Scheduler mainThread() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.immediate();
    }
}

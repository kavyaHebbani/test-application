package kavya.sample.testapplication.utils;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class SchedulerProvider implements ISchedulerProvider {

    private static SchedulerProvider mInstance;

    private SchedulerProvider() {
        // no operation
    }

    public static SchedulerProvider getInstance() {
        if (mInstance == null) {
            mInstance = new SchedulerProvider();
        }
        return mInstance;
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }
}

package talex.zsw.baselibrary.xbus.scheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import talex.zsw.baselibrary.xbus.Bus;


/**
 * User: mcxiaoke
 * Date: 15/8/4
 * Time: 16:04
 */
class ExecutorScheduler implements Scheduler {
    private Bus mBus;
    private Executor mExecutor;

    public ExecutorScheduler(final Bus bus) {
        mBus = bus;
        mExecutor = Executors.newCachedThreadPool();
    }

    @Override
    public void post(final Runnable runnable) {
        mExecutor.execute(runnable);
    }
}

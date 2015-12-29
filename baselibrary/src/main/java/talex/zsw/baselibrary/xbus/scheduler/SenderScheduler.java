package talex.zsw.baselibrary.xbus.scheduler;


import talex.zsw.baselibrary.xbus.Bus;

/**
 * User: mcxiaoke
 * Date: 15/8/4
 * Time: 16:00
 */
class SenderScheduler implements Scheduler {
    private Bus mBus;

    public SenderScheduler(final Bus bus) {
        mBus = bus;
    }

    @Override
    public void post(final Runnable runnable) {
        runnable.run();
    }
}

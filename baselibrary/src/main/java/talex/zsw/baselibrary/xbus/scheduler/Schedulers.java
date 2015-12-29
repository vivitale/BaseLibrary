package talex.zsw.baselibrary.xbus.scheduler;

import android.os.Handler;
import android.os.Looper;

import talex.zsw.baselibrary.xbus.Bus;


/**
 * User: mcxiaoke
 * Date: 15/8/4
 * Time: 15:58
 */
public final class Schedulers {

    public static Scheduler sender(final Bus bus) {
        return new SenderScheduler(bus);
    }

    public static Scheduler main(final Bus bus) {
        return new HandlerScheduler(bus, new Handler(Looper.getMainLooper()));
    }

    public static Scheduler thread(final Bus bus) {
        return new ExecutorScheduler(bus);
    }
}

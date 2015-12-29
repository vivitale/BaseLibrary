package talex.zsw.baselibrary.xbus.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import talex.zsw.baselibrary.xbus.Bus;


/**
 * User: mcxiaoke
 * Date: 15/7/30
 * Time: 18:12
 * 标识一个方法为事件接收器方法
 * 同时这个方法需要满足条件：
 * public/非static/一个参数/无返回值
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BusReceiver {

    Bus.EventMode mode() default Bus.EventMode.Main;
}

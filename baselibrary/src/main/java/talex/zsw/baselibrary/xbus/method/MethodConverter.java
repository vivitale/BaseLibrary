package talex.zsw.baselibrary.xbus.method;

import java.lang.reflect.Method;

import talex.zsw.baselibrary.xbus.MethodInfo;

/**
 * User: mcxiaoke
 * Date: 15/8/4
 * Time: 18:32
 */
interface MethodConverter {

    MethodInfo convert(final Method method);
}

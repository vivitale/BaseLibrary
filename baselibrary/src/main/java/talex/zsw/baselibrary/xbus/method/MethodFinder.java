package talex.zsw.baselibrary.xbus.method;

import java.util.Set;

import talex.zsw.baselibrary.xbus.Bus;
import talex.zsw.baselibrary.xbus.MethodInfo;


/**
 * User: mcxiaoke
 * Date: 15/8/4
 * Time: 18:14
 */
public interface MethodFinder {

    Set<MethodInfo> find(final Bus bus, final Class<?> targetClass);
}

package talex.zsw.baselibrary.xbus.method;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import talex.zsw.baselibrary.xbus.Bus;
import talex.zsw.baselibrary.xbus.MethodInfo;
import talex.zsw.baselibrary.xbus.annotation.BusReceiver;
import talex.zsw.baselibrary.xbus.exception.BusException;


/**
 * User: mcxiaoke
 * Date: 15/8/4
 * Time: 18:16
 */
public class MethodHelper {


    public static boolean shouldSkipClass(final Class<?> clazz) {
        if (clazz == null || Object.class.equals(clazz)) {
            return true;
        }
        final String clsName = clazz.getName();
        return clsName.startsWith("java.")
                || clsName.startsWith("javax.")
                || clsName.startsWith("android.")
                || clsName.startsWith("com.android.");
    }

    public static boolean isValidMethod(final Method method) {
        // method must be public
        if (!Modifier.isPublic(method.getModifiers())) {
            throw new BusException("event method: " + getMethodSignature(method)
                    + " must be public!");
        }
        // method must not static
        if (Modifier.isStatic(method.getModifiers())) {
            throw new BusException("event method: " + getMethodSignature(method) +
                    " must not be static!");
        }
        // method must has exact one parameter
        if (method.getParameterTypes().length != 1) {
            throw new BusException("event method: " + getMethodSignature(method)
                    + " must have exact one parameter!");
        }
        // must not be volatile
        // fix getDeclaredMethods bug, if method in base class,
        // it returns duplicate method,
        // one is normal, the other is the same but with volatile modifier
        return !Modifier.isVolatile(method.getModifiers());
    }

    public static Set<MethodInfo> findSubscriberMethods(
            final Class<?> targetClass, MethodConverter converter) {
        Class<?> clazz = targetClass;
        final Set<MethodInfo> methods = new HashSet<MethodInfo>();
        while (!shouldSkipClass(clazz)) {
            final Method[] clsMethods = clazz.getDeclaredMethods();
//            System.out.println("findSubscriberMethods() " + clazz.getSimpleName()
//                    + " has " + clsMethods.length + " methods");
            for (final Method method : clsMethods) {
                final MethodInfo methodInfo = converter.convert(method);
                if (methodInfo != null) {
                    methods.add(methodInfo);
                }
            }
            // search more methods in super class
            clazz = clazz.getSuperclass();
        }
        return methods;
    }

    public static Set<MethodInfo> findSubscriberMethodsByAnnotation(
            final Class<?> targetClass) {
        final MethodConverter converter = new MethodConverter() {
            @Override
            public MethodInfo convert(final Method method) {
                // check annotation
                final BusReceiver annotation = method.getAnnotation(BusReceiver.class);
                if (annotation == null) {
                    return null;
                }
                if (!isValidMethod(method)) {
                    return null;
                }
                return new MethodInfo(method, targetClass, annotation.mode());
            }
        };
        return findSubscriberMethods(targetClass, converter);
    }

    public static Set<MethodInfo> findSubscriberMethodsByName(
            final Class<?> targetClass, final String name) {
        final MethodConverter converter = new MethodConverter() {
            @Override
            public MethodInfo convert(final Method method) {
                // check name
                if (!method.getName().equals(name)) {
                    return null;
                }
                if (!isValidMethod(method)) {
                    return null;
                }
                return new MethodInfo(method, targetClass, Bus.EventMode.Main);
            }
        };
        return findSubscriberMethods(targetClass, converter);
    }

    private static String getMethodSignature(final Method method) {
        return method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
    }
}

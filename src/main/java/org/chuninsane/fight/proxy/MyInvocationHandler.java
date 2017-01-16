package org.chuninsane.fight.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ClassName: MyInvocationHandler
 * Date: 2017/1/16
 * Time: 上午9:27
 *
 * @author hzzhangliang1
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke method:" + method.getName() + "!");
        return method.invoke(target, args);
    }
}

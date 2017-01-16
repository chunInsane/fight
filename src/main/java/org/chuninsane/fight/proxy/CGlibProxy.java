package org.chuninsane.fight.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ClassName: CGlibProxy
 * Date: 2017/1/16
 * Time: 上午10:26
 *
 * @author hzzhangliang1
 */
public class CGlibProxy {

    public static Object generateProxy(final Object target)
    {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor()
        {
            public Object intercept(Object obj, Method method, Object[] args,
                                    MethodProxy proxy) throws Throwable
            {
                System.out.println("I'm proxy!");
                return method.invoke(target, args);
            }
        });
        return enhancer.create();
    }

}

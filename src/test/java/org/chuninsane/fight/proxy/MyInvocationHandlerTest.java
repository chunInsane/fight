package org.chuninsane.fight.proxy;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * ClassName: MyInvocationHandlerTest
 * Date: 2017/1/16
 * Time: 上午9:43
 *
 * @author hzzhangliang1
 */
public class MyInvocationHandlerTest {

    @Test
    public void testProxy() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyClass = Proxy.getProxyClass(MyInvocationHandlerTest.class.getClassLoader(), HelloWorld.class);
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        InvocationHandler ih = new MyInvocationHandler(new HelloWorldImpl());
        HelloWorld helloWorld= (HelloWorld)constructor.newInstance(ih);
        helloWorld.sayHello();
    }

    @Test
    public void testProxy2() {
        HelloWorld helloWorld = (HelloWorld)Proxy.newProxyInstance(MyInvocationHandlerTest.class.getClassLoader(), new Class[]{HelloWorld.class}, new MyInvocationHandler(new HelloWorldImpl()));
        helloWorld.sayHello();
    }
}

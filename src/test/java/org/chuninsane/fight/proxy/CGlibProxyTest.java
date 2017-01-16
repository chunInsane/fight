package org.chuninsane.fight.proxy;

import org.junit.Test;

/**
 * ClassName: CGlibProxyTest
 * Date: 2017/1/16
 * Time: 上午10:32
 *
 * @author hzzhangliang1
 */
public class CGlibProxyTest {

    @Test
    public void test() {
        CGlibHosee hosee = (CGlibHosee)CGlibProxy.generateProxy(new CGlibHosee());
        hosee.sayhi();
    }
}

package org.chuninsane.fight.encrypt;

import org.chuninsane.fight.encrypt.base64.Base64;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * ClassName: Base64Test
 * Date: 2017/1/16
 * Time: 下午12:06
 *
 * @author hzzhangliang1
 */
public class Base64Test {

    @Test
    public void testEncode() {
        String source = "base64加解密测试";
        byte[] b1 = source.getBytes(StandardCharsets.UTF_8);
        String base64Str= Base64.encode(source.getBytes(StandardCharsets.UTF_8));
        byte[] b2 = Base64.decode(base64Str);
        String decodeResult = new String(Base64.decode(base64Str), StandardCharsets.UTF_8);
        Assert.assertEquals(source, decodeResult);
    }

    @Test
    public void testDecode() {

    }
}

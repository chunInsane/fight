package org.chuninsane.fight.encrypt.base64;

import java.util.ArrayList;
import java.util.List;

/**
 * Base64
 *
 * ClassName: Base64
 * Date: 2017/1/16
 * Time: 上午10:35
 *
 * @author hzzhangliang1
 */
public class Base64 {

    /**
     * base64 char map
     */
    private static final char[] pem_array = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /**
     * base64 encrypt
     *
     * @param byt source byte array
     * @return encrypt string
     */
    public static String encode(final byte[] byt) {
        StringBuilder target = new StringBuilder();
        if (byt != null && byt.length > 0) {
            int len = byt.length;
            for (int i = 0; i < len; i += 3) {
                int threeByte = 0x00000000;
                for (int j = 0; j < 3; ++j) {
                    threeByte <<= 8;
                    if (i + j < len) {
                        threeByte |= (byt[i + j] & 0xff);
                    }
                }
                target.append(threeByteToFourChar(threeByte));
            }
            //use '=' mark padding byte number
            String suffix = "";
            int targetLen = target.length();
            if (len % 3 == 1) {
                target.replace(targetLen - 2, targetLen, "==");
            }
            if (len % 3 == 2) {
                target.replace(targetLen - 1, targetLen, "=");
            }
            target.append(suffix);
        }
        return target.toString();
    }

    /**
     * base64 decrypt
     *
     * @param source base64 string
     * @return source byte array
     */
    public static byte[] decode(final String source) {
        byte[] result = new byte[0];
        if (source != null && source.length() > 0) {
            List<Byte> bytes = new ArrayList<>();
            int len = source.length();
            int padding = 0;

            for (int j = len - 1; j >=0; j--) {
                if (source.charAt(j) != '=') {
                    break;
                }
                padding++;
            }

            int i = 0;
            for (; i + 4 <= len; i += 4) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 4; ++j) {
                    sb.append(source.charAt(i + j));
                }
                int threeByte = fourCharToThreeByte(sb.toString());
                for (int j = 0; j < 3; ++j) {
                    threeByte = threeByte << 8;
                    bytes.add((byte)((threeByte & 0xff000000) >>> 24));
                }
            }
            result = byteListToByteAry(bytes, padding);
        }
        return result;
    }

    private static String threeByteToFourChar(int threeByte) {
        StringBuilder result = new StringBuilder();
        threeByte <<= 8;
        for (int i = 0; i < 4; ++i) {
            result.append(pem_array[(threeByte & 0xfc000000) >>> 26]);
            threeByte <<= 6;
        }
        return result.toString();
    }

    private static byte[] byteListToByteAry(List<Byte> byteList, int padding) {
        byte[] bytes = new byte[byteList.size() - padding];
        for (int i = 0; i < byteList.size() - padding; ++i) {
            bytes[i] = byteList.get(i);
        }
        return bytes;
    }

    private static int fourCharToThreeByte(String str) {
        int result = 0;
        for (int i = 0; i < 4; ++i) {
            result = result << 6;
            char chr = str.charAt(i);
            if (chr >= 'A' && chr <= 'Z') {
                result += (chr - 'A');
            } else if (chr >= 'a' && chr <= 'z') {
                result += (chr - 'a' + 26);
            } else if (chr >= '0' && chr <= '9') {
                result += (chr - '0' + 52);
            } else if (chr == '+') {
                result += 62;
            } else if (chr == '/') {
                result += 63;
            } else if (chr == '=') {
                result += 0;
            } else {
                throw new IllegalArgumentException("illegal char '" + String.valueOf(chr) + "'");
            }
        }
        return result;
    }
}

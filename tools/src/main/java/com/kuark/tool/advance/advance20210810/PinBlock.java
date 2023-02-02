package com.kuark.tool.advance.advance20210810;

import java.util.Random;

/**
 * @author rock
 * @detail 参照：https://zhuanlan.zhihu.com/p/47203032
 * @date 2021/10/27 15:32
 */
public class PinBlock {
    public static void main(String[] args) {
        String pin = "1234";
        String accno = "1234567890123456";
        String s = format0(pin, accno);
        System.out.println("pin block="+s);
    }

    /**
     * Format 0 PIN block
     *
     * @param PIN 密码
     * @param PAN 账号
     * @return Format 0 PIN block 十六进制字符串
     */
    private static String format0(String PIN, String PAN) {
        // PIN域，64bit，16位十六进制数字
        // 固定值0x0 + PIN长度 + PIN(不足14位右补F)
        String PINField = "0"
                + Integer.toHexString(PIN.length())
                + String.format("%-14s", PIN).replace(' ', 'F');
        // PAN域，64bit，16位十六进制数字
        // 固定值0x0000 + PAN，去掉校验数字，从右边数12位，不足12位左补0x0
        String PANWithoutCheckDigit = PAN.substring(0, PAN.length() - 1);
        String PANField = "0000"
                + (PANWithoutCheckDigit.length() > 12 ? PANWithoutCheckDigit
                .substring(PANWithoutCheckDigit.length() - 12,
                        PANWithoutCheckDigit.length()) : String.format(
                "%12s", PANWithoutCheckDigit).replace(' ', '0'));
        // 十六进制转byte数组
        byte[] PINFieldByteArray = hexString2ByteArr(PINField);
        // 十六进制转byte数组
        byte[] PANFieldByteArray = hexString2ByteArr(PANField);
        // 异或
        byte[] PINBlockByteArray = new byte[8];
        for (int i = 0; i < 8; i++) {
            PINBlockByteArray[i] = (byte) (PINFieldByteArray[i] ^ PANFieldByteArray[i]);
        }
        // 返回十六进制
        return byteArr2HexString(PINBlockByteArray).toUpperCase();
    }

    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String byteArr2HexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * hex字符串转byte数组
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexString2ByteArr(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    /**
     * Hex字符串转byte
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /**
     * Format 1 PIN block
     *
     * @param PIN 密码
     * @return Format 1 PIN block 十六进制字符串
     */
    private static String format1(String PIN) {
        // PIN block，64bit，16位十六进制数字
        // 固定值0x1 + PIN长度 + PIN，不足14位右补交易域
        String PINBlock = "1" + Integer.toHexString(PIN.length()) + PIN;
        // 交易域使用随机数，取值范围是0x0~0xF
        Random r = new Random();
        for (int i = 0; i < 14 - PIN.length(); i++) {
            PINBlock += Integer.toHexString(r.nextInt(16));
        }
        return PINBlock.toUpperCase();
    }

    /**
     * Format 2 PIN block
     *
     * @param PIN 密码
     * @return Format 2 PIN block 十六进制字符串
     */
    private static String format2(String PIN) {
        // PIN block，64bit，16位十六进制数字
        // 固定值0x2 + PIN长度 + PIN(不足14位右补F)
        return "2"
                + Integer.toHexString(PIN.length())
                + String.format("%-14s", PIN).replace(' ', 'F');
    }

    /**
     * Format 3 PIN block
     *
     * @param PIN 密码
     * @param PAN 账号
     * @return Format 3 PIN block 十六进制字符串
     */
    private static String format3(String PIN, String PAN) {
        // PIN域，64bit，16位十六进制数字
        // 固定值0x3 + PIN长度 + PIN(不足14位右补随机数)
        String PINField = "3" + Integer.toHexString(PIN.length()) + PIN;
        // 随机数取值范围0xA~0xF
        Random r = new Random();
        for (int i = 0; i < 14 - PIN.length(); i++) {
            PINField += Integer.toHexString(r.nextInt(6) + 10);
        }
        // PAN域，64bit，16位十六进制数字
        // 固定值0x0000 + PAN，去掉校验数字，从右边数12位，不足12位左补0x0
        String PANWithoutCheckDigit = PAN.substring(0, PAN.length() - 1);
        String PANField = "0000"
                + (PANWithoutCheckDigit.length() > 12 ? PANWithoutCheckDigit
                .substring(PANWithoutCheckDigit.length() - 12,
                        PANWithoutCheckDigit.length()) : String.format(
                "%12s", PANWithoutCheckDigit).replace(' ', '0'));
        // 十六进制转byte数组
        byte[] PINFieldByteArray = hexString2ByteArr(PINField);
        // 十六进制转byte数组
        byte[] PANFieldByteArray = hexString2ByteArr(PANField);
        // 异或
        byte[] PINBlockByteArray = new byte[8];
        for (int i = 0; i < 8; i++) {
            PINBlockByteArray[i] = (byte) (PINFieldByteArray[i] ^ PANFieldByteArray[i]);
        }
        // 返回十六进制
        return byteArr2HexString(PINBlockByteArray).toUpperCase();
    }
}


import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class CardAESUtil {

    public static String getEncryptCardNo(String cardNo, String key) {
        try {
            log.info("加密卡号：{}， key：{}", cardNo, key);
            return AESUtil.getEncryptHexStr(cardNo.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.warn("获取加密卡号失败");
            return null;
        }
    }

    public static String getDecryptCardNo(String encryptCardNo, String key) {
        try {
//            log.info("解密卡号：{}， key：{}", encryptCardNo, key);
            return AESUtil.getDecryptHexStr(encryptCardNo, key.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.warn("解密卡号失败");
            return null;
        }
    }


}

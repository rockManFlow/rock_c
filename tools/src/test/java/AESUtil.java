import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * AES加密工具类
 */
public class AESUtil {

  /**
   * 密钥长度: 128, 192 or 256
   */
  private static final int KEY_SIZE = 128;

  /**
   * 加密/解密算法名称
   */
  private static final String ALGORITHM = "AES";

  /**
   * 随机数生成器（RNG）算法名称
   */
  private static final String RNG_ALGORITHM = "SHA1PRNG";

  /**
   * 生成密钥对象
   */
  private static SecretKey generateKey(byte[] key) throws Exception {
    // 创建安全随机数生成器
    SecureRandom random = SecureRandom.getInstance(RNG_ALGORITHM);
    // 设置 密钥key的字节数组 作为安全随机数生成器的种子
    random.setSeed(key);
    // 创建 AES算法生成器
    KeyGenerator gen = KeyGenerator.getInstance(ALGORITHM);
    // 初始化算法生成器
    gen.init(KEY_SIZE, random);
    // 生成 AES密钥对象, 也可以直接创建密钥对象: return new SecretKeySpec(key, ALGORITHM);
    return gen.generateKey();
  }

  /**
   * 数据加密: 明文 -> 密文字节码数组
   */
  public static byte[] getEncryptBytes(byte[] plainBytes, byte[] key) throws Exception {
    // 生成密钥对象
    SecretKey secKey = generateKey(key);
    // 获取 AES 密码器
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    // 初始化密码器（加密模型）
    cipher.init(Cipher.ENCRYPT_MODE, secKey);
    // 加密数据, 返回密文
    return cipher.doFinal(plainBytes);
  }

  /**
   * 数据加密: 明文 -> 密文可读字符串
   */
  public static String getEncryptHexStr(byte[] plainBytes, byte[] key) throws Exception {
    byte[] encryptBytes = getEncryptBytes(plainBytes, key);
    return parseByte2HexStr(encryptBytes);
  }

  /**
   * 数据解密: 密文 -> 明文字节码数组
   */
  public static byte[] getDecryptBytes(byte[] cipherBytes, byte[] key) throws Exception {
    // 生成密钥对象
    SecretKey secKey = generateKey(key);
    // 获取 AES 密码器
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    // 初始化密码器（解密模型）
    cipher.init(Cipher.DECRYPT_MODE, secKey);
    // 解密数据, 返回明文
    return cipher.doFinal(cipherBytes);
  }

  /**
   * 数据解密: 密文 -> 明文可读字符串
   */
  public static String getDecryptHexStr(String hexStr, byte[] key) throws Exception {
    byte[] decryptBytes = getDecryptBytes(parseHexStr2Byte(hexStr), key);
    return new String(decryptBytes);
  }

  /**
   * 将16进制转换为二进制
   *
   * @param hexStr 字符串
   * @return 字节数组
   */
  private static byte[] parseHexStr2Byte(String hexStr) {
    if (hexStr.length() < 1) {
      return null;
    }
    byte[] result = new byte[hexStr.length() / 2];
    for (int i = 0; i < hexStr.length() / 2; i++) {
      int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
      int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
      result[i] = (byte) (high * 16 + low);
    }
    return result;
  }

  /**
   * 将二进制转换成16进制
   *
   * @param buf 字节数组
   * @return 字符串
   */
  private static String parseByte2HexStr(byte[] buf) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < buf.length; i++) {
      String hex = Integer.toHexString(buf[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      sb.append(hex.toUpperCase());
    }
    return sb.toString();
  }

}

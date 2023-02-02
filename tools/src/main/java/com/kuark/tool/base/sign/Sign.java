package com.kuark.tool.base.sign;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.*;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名生成的通用步骤如下：
 * <p/>
 * 第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。
 * 特别注意以下重要规则：
 * ◆ 参数名ASCII码从小到大排序（字典序）；
 * ◆ 如果参数的值为空不参与签名；
 * ◆ 参数名区分大小写；
 * ◆ 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。
 * ◆ 微信接口可能增加字段，验证签名时必须支持增加的扩展字段
 * 第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。
 * key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
 * 举例：
 * 假设传送的参数如下：
 * appid： wxd930ea5d5a258f4f
 * mch_id： 10000100
 * device_info： 1000
 * body： test
 * nonce_str： ibuaiVcKdpRxkhJA
 * 第一步：对参数按照key=value的格式，并按照参数名ASCII字典序排序如下：
 * stringA="appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
 * 第二步：拼接API密钥：
 * stringSignTemp="stringA&key=192006250b4c09247ec02edce69f6a2d"
 * sign=MD5(stringSignTemp).toUpperCase()="9A0A8659F005D6984697E2CA0A9CF3B7"
 * 最终得到最终发送的数据：
 * <xml>
 * <appid>wxd930ea5d5a258f4f</appid>
 * <mch_id>10000100</mch_id>
 * <device_info>1000<device_info>
 * <body>test</body>
 * <nonce_str>ibuaiVcKdpRxkhJA</nonce_str>
 * <sign>9A0A8659F005D6984697E2CA0A9CF3B7</sign>
 * <xml>
 * <p/>
 * <p/>
 * 生成签名
 * Created by caoqingyuan on 2016/8/2.
 * TODO 怎么进行验签的设置？？？
 */
public class Sign {
    private static final String key = "Dy1uj92hf631qjTc23K09r7ssdP";
    private static PrivateKey privateKey=null;
    private static PublicKey publicKey=null;

    static {
        KeyPairGenerator myKeyGen = null;
        try {
            myKeyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        myKeyGen.initialize(1024);
        KeyPair myKeyPair = myKeyGen.generateKeyPair();
        privateKey=myKeyPair.getPrivate();
        publicKey=myKeyPair.getPublic();
    }
    @Test
    public void verifySignTest(){
        String data="zheshiyigece多喝点的好处";
        signVefity(data);
    }

    public static void main(String[] args) {
        //treeMap 集合的特点是：会自动对放入其中的对象进行排序。默认根据字典顺序。还可以自己规定好排序方法
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("id", "12");
        treeMap.put("name", "xiaohong");
        treeMap.put("age", "18");
        treeMap.put("address", "北京通州区");
        String sign = sign(treeMap);
        System.out.println("sign=" + sign);
    }

    //指定签名格式
    private static String sign(TreeMap treeMap) {
        Iterator iterator = treeMap.entrySet().iterator();
        StringBuilder stringA = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry entey = (Map.Entry) iterator.next();
            String key = (String) entey.getKey();
            String value = (String) entey.getValue();
            if (value != null && !"".equals(value)) {
                stringA.append(key);
                stringA.append("=");
                stringA.append(value);
                stringA.append("&");
            }
        }
        stringA.append("key");
        stringA.append("=");
        stringA.append(key);
        return DigestUtils.md5Hex(stringA.toString()).toUpperCase();
    }

    //使用RSA生成签名。生成签名和验证签名java.security
    public void signVefity(String data) {
        try {
            byte[] info = data.getBytes();

            //产生RSA密钥对(myKeyPair)
//            KeyPairGenerator myKeyGen = KeyPairGenerator.getInstance("RSA");
//            myKeyGen.initialize(1024);
//            KeyPair myKeyPair = myKeyGen.generateKeyPair();
//            System.out.println("得到RSA密钥对    " + myKeyPair);

            //产生Signature对象,用私钥对信息(info)签名.
            Signature mySig = Signature.getInstance("SHA1WithRSA");  //用指定算法产生签名对象
            mySig.initSign(privateKey);  //用私钥初始化签名对象
            mySig.update(info);  //将待签名的数据传送给签名对象(须在初始化之后)
            byte[] sigResult = mySig.sign();  //返回签名结果字节数组
            System.out.println("签名后信息: " + new String(sigResult));

            //用公钥验证签名结果
            mySig.initVerify(publicKey);  //使用公钥初始化签名对象,用于验证签名
            mySig.update(info); //更新签名内容
            boolean verify = mySig.verify(sigResult); //得到验证结果
            System.out.println("签名验证结果: " + verify);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

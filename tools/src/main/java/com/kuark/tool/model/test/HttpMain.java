package com.kuark.tool.model.test;

import com.kuark.tool.model.connection.HttpConnect;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.xmlbeans.impl.util.Base64;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caoqingyuan on 2017/3/13.
 */
public class HttpMain {
    private static final String url="http://localhost:8080/mod/download/checkfile";
    public static void main(String[] args) throws IOException {
        Map requestMap=new HashMap();
        requestMap.put("accountId","cqy88888");
        requestMap.put("billDate","20170120");
        requestMap.put("accountPwd","huh72dgy721u9382e32d2332djdsw");
        byte[] response = HttpConnect.httpConnection(url, requestMap.toString().getBytes(), 30);
        String resp=new String(Base64.decode(response),"UTF-8");//使用utf-8解码
        System.out.println("file=["+resp+"]");
    }
    @Test
    public void tttt(){
        String st="{VERSION=4.0.0.0, txn_type=0431, data_type=json, terminal_id=32876, txn_sub_type=13, member_id=1054063, data_content=70edb65133421a79859e9a34f2312c3623b4e3422e85d2652e9911a99000e08150bfe6eb796b8101b7e9b003a8a2cf25a68f1a871629fa21e1cd14b846830995f55cad43871b980128cfc70ada753edb18fbc5e65b031c5f9fc94a0ddabcaeb92f8bca92f418688384d599cc48b2e7dfbb931ba0aaaa0faef2c246b999a7628a47f51a1556519597fce5c7ac2208756a1f28c07f24247b530e1f09314737dd0b6369d411bda2454db710b1d02d24d33abc6895fadddbe4b17c8fe0b1bccba86b03e7f6177be66af6c288031a86aeacbd71100d369415563b44d8de3ba25ae9ed8e629db7c93b0dbee6c78e6055cff5af883db51ed649b317b93c53af6adf087d4af5b2ab1a4490eaea2f78ba402a41acb9477a04c0ecef4c7ad42696a599d44a63d04bafea5cc85ec3d6b816975fb8b6117ad334e24e49c8cca47cc6d172b0092eb6e4260bcea7833d43c306636730b9cc3d821f6db2a25513c6d1735e7c071ee1f8e95daef163cc073905c6ba74ab50b2d1b88558d2c82b139b773d6c963eca000d884c91916c00140323ccfcb9aadc78ad478ec7d93dd0a2559d4425ed2be42c7eca08183602fb9eaac183d684295bba22eee3d4037701c2bc07a7b9d8aa1d213ae558c78bbe875e8562aaaf59d21ad1f44c1ca5d311cc092c48edece0b9252f2b32e8e131318cd87c2a10d4718cdeee4100974890f1e7919dd20df1c0d7e031cc05b3f35151c7021881e330ed4b42c86bf406f6b9c3ba6aab03c7a6cf730dd30e72f8828d81840794e59c7ee87041fd31d1549873b619c9d30e453bbd95c95f4fc9771420829e272723aacd499422732534d8c5e47bbb3fc922950016b24a465abad0e6620429c81a1b67842a4516237a2347661fa76eb71e885adb926fb3}";
        ListOrderedMap map=tranfor(st);
        String bb=(String)map.get("txn_type");
        System.out.println("bb="+bb);
    }
    public static ListOrderedMap tranfor(String str){
        if(str==null){
            return null;
        }
        String request=str.substring(1,str.length()-1);
        ListOrderedMap list=new ListOrderedMap();
        String[] keys=request.split("\\,");
        for(String st:keys){
            String[] keyValues=st.split("=");
            list.put(keyValues[0].trim(),keyValues[1]);
        }
        return list;
    }
}

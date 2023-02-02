package com.kuark.tool.advance.advance20191031;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.dom4j.*;
import org.dom4j.tree.DefaultAttribute;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author rock
 * @detail
 * @date 2019/10/31 17:53
 */
public class Xml2Json {

    public static void main(String[] args) throws DocumentException {
        String xmlStr="<PayUVasResponse>\n" +
                "<MerchantReference>20136102358</MerchantReference>\n" +
                "<PayUVasReference>1802</PayUVasReference>\n" +
                "<ResultCode>00</ResultCode>\n" +
                "<ResultMessage>Success</ResultMessage>\n" +
                "<VasProvider>MCA_ACCOUNT_SQ_NG</VasProvider>\n" +
                "<VasProviderReference>20136102358</VasProviderReference>\n" +
                "<CustomFields>\n" +
                "<Customfield Key='BASKET_ID' Value='28114'/>\n" +
                "<Customfield Key='FIRSTNAME' Value=''/>\n" +
                "<Customfield Key='SURNAME' Value='OPOPOPJP'/>\n" +
                "<Customfield Key='ACCOUNT_NUMBER' Value='41945832'/>\n" +
                "<Customfield Key='DSTV_CUSTOMER_NUMBER' Value='53984480'/>\n" +
                "<Customfield Key='ACCOUNT_STATUS' Value='Suspended'/>\n" +
                "<Customfield Key='BOX_OFFICE' Value='false'/>\n" +
                "<Customfield Key='INVOICE_PERIOD' Value='1'/>\n" +
                "<Customfield Key='QUOTE_TOTAL_AMOUNT' Value='9420.00'/>\n" +
                "<Customfield Key='ADDON_PRODUCT_CODE_0' Value='FRN15W7'/>\n" +
                "<Customfield Key='ADDON_PRODUCT_DESC_0' Value='DStv French Plus'/>\n" +
                "<Customfield Key='ADDON_PRODUCT_PRICE_0' Value='5040.00'/>\n" +
                "<Customfield Key='ADDON_PRODUCT_CUREENCY_0' Value='NIR'/>\n" +
                "<Customfield Key='ADDON_IS_PRODUCTONREQUEST_0' Value='false'/>\n" +
                "<Customfield Key='ADDON_PRODUCT_CODE_1' Value='FRNS7W8'/>\n" +
                "<Customfield Key='ADDON_PRODUCT_DESC_1' Value='DStv French Touch'/>\n" +
                "<Customfield Key='ADDON_PRODUCT_PRICE_1' Value='1400.00'/>\n" +
                "<Customfield Key='ADDON_PRODUCT_CUREENCY_1' Value='NIR'/>\n" +
                "<Customfield Key='ADDON_IS_PRODUCTONREQUEST_1' Value='false'/>\n" +
                "<Customfield Key='UPGRADE_PRODUCT_CODE_0' Value='PRWW4'/>\n" +
                "<Customfield Key='UPGRADE_PRODUCT_DESC_0' Value='DStv Premium'/>\n" +
                "<Customfield Key='UPGRADE_PRODUCT_PRICE_0' Value='13980.00'/>\n" +
                "<Customfield Key='UPGRADE_PRODUCT_CUREENCY_0' Value='NIR'/>\n" +
                "<Customfield Key='UPGRADE_IS_PRODUCTONREQUEST_0' Value='false'/>\n" +
                "<Customfield Key='UPGRADE_PRODUCT_CODE_1' Value='PRWASI'/>\n" +
                "<Customfield Key='UPGRADE_PRODUCT_DESC_1' Value='DStv Premium + Indian'/>\n" +
                "<Customfield Key='UPGRADE_PRODUCT_PRICE_1' Value='15740.00'/>\n" +
                "<Customfield Key='UPGRADE_PRODUCT_CUREENCY_1' Value='NIR'/>\n" +
                "<Customfield Key='UPGRADE_IS_PRODUCTONREQUEST_1' Value='false'/>\n" +
                "</CustomFields>\n" +
                "</PayUVasResponse>";
        JSONObject jsonObject = xml2JsonResp(xmlStr);
        System.out.println("jsonObject="+jsonObject.toJSONString());


        Map<String,String> firstLayerMap=new HashMap<>();

        firstLayerMap.put("MerchantId","TestID");
        firstLayerMap.put("MerchantReference","2013102525");
        firstLayerMap.put("TransactionType","SINGLE");
        firstLayerMap.put("VasId","MCA_ACCOUNT_SQ_NG");
        firstLayerMap.put("CountryCode","NG");
        firstLayerMap.put("AmountInCents","200");
        firstLayerMap.put("CustomerId","4131963384");

        Map<String,String> secondLayerMap=new HashMap<>();
        secondLayerMap.put("BasketId","28114");

        String xml = map2XmlReq(firstLayerMap, secondLayerMap);
        System.out.println("xml="+xml);
    }

    public static JSONObject xml2JsonResp(String xmlStr) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json = new JSONObject();
        Element root = doc.getRootElement();
        List<Element> children = root.elements();
        for (Element e : children) {
            //返回该标签对象包含的所有属性
            if("CustomFields".equals(e.getName())){
                List<Element> elements = e.elements();
                for(Element e1:elements){
                    Iterator<Attribute> iterator = e1.attributeIterator();
                    Attribute attribute1 = iterator.next();
                    Attribute attribute2 = iterator.next();
                    String key="";
                    String value="";
                    if("Key".equals(attribute1.getName())){
                        key=attribute1.getValue();
                    }
                    if("Value".equals(attribute2.getName())){
                        value=attribute2.getValue();
                    }
                    json.put(key,value);
                }
            }else{
                json.put(e.getName(),e.getText());
            }
        }
        return json;
    }

    public static String map2XmlReq(Map<String,String> firstLayerMap,Map<String,String> secondLayerMap){
        StringBuilder xmlReq=new StringBuilder("<PayUVasRequest Ver=\"1.0\">");
        if(MapUtils.isNotEmpty(firstLayerMap)){
            firstLayerMap.keySet().stream().
                    forEach(key->xmlReq.append("<").append(key).append(">")
                            .append(firstLayerMap.get(key))
                            .append("</").append(key).append(">"));
        }
        if(MapUtils.isNotEmpty(secondLayerMap)){
            xmlReq.append("<CustomFields>");
            secondLayerMap.keySet().stream().forEach(
                    key->xmlReq.append("<Customfield  Key=\"").append(key).append("\" Value=\"")
                    .append(secondLayerMap.get(key)).append("\" />")
            );
            xmlReq.append("</CustomFields>");
        }
        xmlReq.append("</PayUVasRequest>");

        return xmlReq.toString();
    }
}

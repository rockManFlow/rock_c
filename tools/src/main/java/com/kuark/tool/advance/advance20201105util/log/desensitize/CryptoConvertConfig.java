package com.kuark.tool.advance.advance20201105util.log.desensitize;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.kuark.tool.advance.advance20201105util.log.desensitize.interfaces.ICryptoConvertor;
import com.kuark.tool.advance.advance20201105util.log.desensitize.interfaces.SensitiveObjectLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 转换器配置
 */
@Slf4j
public class CryptoConvertConfig {

    /**
     * email转换key
     */
    public static final String                         EMAIL_CONVERT     = "_email_convert_";
    /**
     * 电话+手机转换key
     */
    public static final String                         PHONE_CONVERT     = "_phone_convert_";
    /**
     * idcard转换key
     */
    public static final String                         IDCARD_CONVERT    = "_idcard_convert_";
    /**
     * 身份标示(手机号，邮箱，用户名，身份证)转换key
     */
    public static final String                         IDENTITY_CONVERT  = "_identity_convert_";
    /**
     * 默认转换key
     */
    public static final String                         DEFAULT_CONVERT   = "_default_convert_";
    /**
     * 银行卡转换key
     */
    public static final String                         BANK_CARD_CONVERT = "_bank_card_convert_";
    /**
     * json字符串转换key
     */
    public static final String                         JSON_CONVERT      = "_json_convert_";

    //转换器集合
    private static final Map<String, ICryptoConvertor> convertors        = new HashMap<String, ICryptoConvertor>();
    private static final Set<String>                   DEFAULT_KEYS      = new HashSet<String>();

    static {
        DEFAULT_KEYS.add(EMAIL_CONVERT);
        DEFAULT_KEYS.add(PHONE_CONVERT);
        DEFAULT_KEYS.add(IDCARD_CONVERT);
        DEFAULT_KEYS.add(IDENTITY_CONVERT);
        DEFAULT_KEYS.add(DEFAULT_CONVERT);
        DEFAULT_KEYS.add(BANK_CARD_CONVERT);
        DEFAULT_KEYS.add(JSON_CONVERT);
        registConvertor(EMAIL_CONVERT, new DefaultEmailCryptoConvertor());
        registConvertor(PHONE_CONVERT, new DefaultPhoneCryptoConvertor());
        registConvertor(IDCARD_CONVERT, new DefaultIDCardCryptoConvertor());
        registConvertor(IDENTITY_CONVERT, new DefaultIdentityCryptoConvertor());
        registConvertor(DEFAULT_CONVERT, new DefaultCryptoConvertor());
        registConvertor(BANK_CARD_CONVERT, new DefaultBankCardCryptoConvertor());
        registConvertor(JSON_CONVERT, new DefaultJsonCryptoConvertor());
    }

    /**
     * 获取转换实现
     *
     * @param name 转换实现名
     * @return
     */
    public static ICryptoConvertor getConvertor(String name) {
        ICryptoConvertor convertor = convertors.get(name);
        if (convertor == null && DEFAULT_KEYS.contains(name)) {
            switch (name) {
                case EMAIL_CONVERT:
                    convertor = new DefaultEmailCryptoConvertor();
                    break;
                case PHONE_CONVERT:
                    convertor = new DefaultPhoneCryptoConvertor();
                    break;
                case IDCARD_CONVERT:
                    convertor = new DefaultIDCardCryptoConvertor();
                    break;
                case IDENTITY_CONVERT:
                    convertor = new DefaultIdentityCryptoConvertor();
                    break;
                case DEFAULT_CONVERT:
                    convertor = new DefaultCryptoConvertor();
                    break;
                case BANK_CARD_CONVERT:
                    convertor = new DefaultBankCardCryptoConvertor();
                    break;
                default:
                    break;
            }
            if (convertor != null) {
                registConvertor(name, convertor);
            }
        }
        return convertor;
    }

    /**
     * 获取Convertor,如果没有使用默认Convertor
     *
     * @param name 名称
     * @return
     */
    public static ICryptoConvertor getDefault(String name) {
        ICryptoConvertor convertor = getConvertor(name);
        if (convertor == null) {
            convertor = getConvertor(DEFAULT_CONVERT);
        }
        return convertor;
    }

    public static ICryptoConvertor getEmailConvertor() {
        return getConvertor(EMAIL_CONVERT);
    }

    public static ICryptoConvertor getPhoneConvertor() {
        return getConvertor(PHONE_CONVERT);
    }

    public static ICryptoConvertor getIDCardConvertor() {
        return getConvertor(IDCARD_CONVERT);
    }

    public static ICryptoConvertor getIdentityConvertor() {
        return getConvertor(IDENTITY_CONVERT);
    }

    public static ICryptoConvertor getBankCardConvertor() {
        return getConvertor(BANK_CARD_CONVERT);
    }

    public static ICryptoConvertor getJsonConvertor() {
        return getConvertor(JSON_CONVERT);
    }

    /**
     * 注册convertor
     *
     * @param name 名称
     * @param convertor 实现
     */
    public static void registConvertor(String name, ICryptoConvertor convertor) {
        requiredParam(name, "registConvertor", "name");
        requiredParam(convertor, "registConvertor", "convertor");
        convertors.put(name, convertor);
    }

    /**
     * 反注册convertor
     *
     * @param name 名称
     * @return
     */
    public static boolean unregistConvert(String name) {
        if (convertors.containsKey(name)) {
            convertors.remove(name);
            return true;
        } else {
            return false;
        }
    }

    private static String convertBankCard(String valueStr) {
        int length = valueStr.length();
        StringBuilder sb = new StringBuilder(length);
        sb.insert(0, valueStr);
        /*
         * int count = 0; for (int i = length - 1; i >= 0; i--) { if
         * (Character.isDigit(sb.charAt(i))) { if (count < 4) { ++count;
         * continue; } else { sb.setCharAt(i, '*'); } } }
         */
        short[] mark = new short[length];
        int fromIndex = 0;
        int count = 0;
        for (int i = 0; i < length; i++) {
            mark[i] = Character.isDigit(sb.charAt(i)) ? (short) 1 : (short) 0;
            if (mark[i] == 1) {
                count++;
                if (count <= 6) {
                    fromIndex = i;
                }
            }
        }

        if (count > 10) {
            int endIndex = 0;
            count = 0;
            for (int i = length - 1; i >= 0; i--) {
                if (mark[i] == 1) {
                    count++;
                    if (count >= 4) {
                        endIndex = i;
                        break;
                    }
                }
            }
            for (int i = fromIndex + 1; i < endIndex; i++) {
                if (mark[i] == 1) {
                    sb.setCharAt(i, '*');
                }
            }
        } else {
            count = 0;
            for (int i = length - 1; i >= 0; i--) {
                if (Character.isDigit(sb.charAt(i))) {
                    if (count < 4) {
                        ++count;
                        continue;
                    } else {
                        sb.setCharAt(i, '*');
                    }
                }
            }
        }
        return sb.toString();
    }

    private static String convertIDCard(String valueStr) {
        // 前留6位，后留3位
        int length = valueStr.length();
        StringBuilder sb = new StringBuilder(length);
        sb.insert(0, valueStr);

        /*
         * for (int i = 6; i < length - 3; i++) { sb.setCharAt(i, '*'); }
         */
        for (int i = 6; i < length - 4; i++) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

    private static String convertTelephone(String valueStr) {
        int length = valueStr.length();
        StringBuilder sb = new StringBuilder(valueStr.length());
        sb.insert(0, valueStr);
        for (int i = length - 3; i > length - 6; i--) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

    private static String convertMobile(String valueStr) {
        int length = valueStr.length();
        StringBuilder sb = new StringBuilder(valueStr.length());
        sb.insert(0, valueStr);
        /*
         * for (int i = length - 4; i > length - 8; i--) { sb.setCharAt(i, '*');
         * }
         */
        for (int i = length - 5; i > length - 9; i--) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

    private static String convertEmail(String valueStr) {
        int indexOfAt = valueStr.indexOf('@');
        StringBuilder sb = new StringBuilder(valueStr.length());
        sb.insert(0, valueStr);
        for (int i = 1; i < indexOfAt; i++) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

    private static String connvertCommon(String valueStr) {
        if (isEmpty(valueStr)) {
            return valueStr;
        } else {
            if (valueStr.length() == 1) {
                return valueStr;
            }
            if (valueStr.length() == 2) {
                return valueStr.substring(0, 1) + "*";
            }
            StringBuilder sb = new StringBuilder(valueStr.length());
            sb.insert(0, valueStr);
            for (int i = 1; i < valueStr.length() - 1; i++) {
                sb.setCharAt(i, '*');
            }
            return sb.toString();
        }
    }

    private static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }
        String s = String.valueOf(value).trim();
        if ("".equals(s) || "null".equalsIgnoreCase(s)) {
            return true;
        }
        return false;
    }

    private static void requiredParam(Object param, String method, String parameter) {
        if (null == param) {
            throw new IllegalArgumentException(
                    "Required parameter: {param: " + parameter + ", method: " + method + "}");
        }
    }

    public static class DefaultBankCardCryptoConvertor implements ICryptoConvertor {
        @Override
        public String convert(Object value) {
            String valueStr = String.valueOf(value);
            if (RegExValidateUtils.isBankCard(valueStr)) {
                return convertBankCard(valueStr);
            }
            return connvertCommon(valueStr);
        }
    }

    /**
     * 身份标示Convertor
     */
    public static class DefaultIdentityCryptoConvertor implements ICryptoConvertor {
        @Override
        public String convert(Object value) {
            String valueStr = String.valueOf(value);
            if (RegExValidateUtils.isMobile(valueStr)) {
                return convertMobile(valueStr);
            } else if (RegExValidateUtils.isEmail(valueStr)) {
                return convertEmail(valueStr);
            } else if (RegExValidateUtils.isIDCardStrict(valueStr)) {
                return convertIDCard(valueStr);
            } else if (RegExValidateUtils.isBankCard(valueStr)) {
                return convertBankCard(valueStr);
            } else {
                return connvertCommon(valueStr);
            }
        }
    }

    /**
     * IDCard的Convertor
     */
    public static class DefaultIDCardCryptoConvertor implements ICryptoConvertor {
        @Override
        public String convert(Object value) {
            String valueStr = String.valueOf(value);
            if (RegExValidateUtils.isIDCardStrict(valueStr)) {
                return convertIDCard(valueStr);
            } else {
                return connvertCommon(valueStr);
            }
        }
    }

    /**
     * Phone的Convertor
     */
    public static class DefaultPhoneCryptoConvertor implements ICryptoConvertor {
        @Override
        public String convert(Object value) {
            String valueStr = String.valueOf(value);
            if (RegExValidateUtils.isMobile(valueStr)) {
                return convertMobile(valueStr);
            } else if (RegExValidateUtils.isTelephone(valueStr)) {
                return convertTelephone(valueStr);
            } else {
                return connvertCommon(valueStr);
            }
        }

    }

    ;

    /**
     * email的convertor
     */
    public static class DefaultEmailCryptoConvertor implements ICryptoConvertor {
        @Override
        public String convert(Object value) {
            String valueStr = String.valueOf(value);
            if (RegExValidateUtils.isEmail(valueStr)) {
                return convertEmail(valueStr);
            } else {
                return connvertCommon(valueStr);
            }
        }
    }

    /**
     * json的convertor
     *
     * @author mingli
     */
    public static class DefaultJsonCryptoConvertor implements ICryptoConvertor {

        private static Set<String> sensitiveWordSet = new HashSet<>();

        static {
            /** 没有敏感词库，简单进行穷举 */
            sensitiveWordSet.add("pwd");
            sensitiveWordSet.add("password");
            sensitiveWordSet.add("cardno");
            sensitiveWordSet.add("bankcardno");
            sensitiveWordSet.add("certno");
            sensitiveWordSet.add("cardname");
            sensitiveWordSet.add("username");
            sensitiveWordSet.add("certiname");

            try {
                //加载脱敏字段文件 pom文件中把指定路径设置为resource类型文件
                List<String> sensitiveWordsList = IOUtils
                        .readLines(DefaultJsonCryptoConvertor.class.getResourceAsStream("config/sensitive_words.txt"));
                if (CollectionUtils.isNotEmpty(sensitiveWordsList)) {
                    sensitiveWordSet.addAll(sensitiveWordsList);
                }
            } catch (IOException e) {
            }
        }

        @Override
        public String convert(Object value) {
            if (value == null) {
                return String.valueOf(value);
            }
            try {
                if (value.toString().startsWith("{") && value.toString().endsWith("}")) {// 对象
                    JSONObject jsonObject = JSON.parseObject(value.toString());
                    convertJSONObject(jsonObject);
                    return formatJson(JSON.toJSONString(jsonObject));
                } else if (value.toString().startsWith("[") && value.toString().endsWith("]")) {// 数组
                    JSONArray jsonArray = JSON.parseArray(value.toString());
                    convertJSONArray(jsonArray);
                    return formatJson(JSON.toJSONString(jsonArray));
                } else {
                    return String.valueOf(value);
                }
            } catch (Exception e) {
                log.error("convert error", e);
                return String.valueOf(value);
            }
        }

        /**
         * 格式化
         *
         * @param jsonStr
         * @return
         */
        public static String formatJson(String jsonStr) {
            if (StringUtils.isBlank(jsonStr))
                return "";
            StringBuilder sb = new StringBuilder();
            char last = '\0';
            char current = '\0';
            int indent = 0;
            for (int i = 0; i < jsonStr.length(); i++) {
                last = current;
                current = jsonStr.charAt(i);
                switch (current) {
                    case '{':
                    case '[':
                        sb.append(current);
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                        break;
                    case '}':
                    case ']':
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                        sb.append(current);
                        break;
                    case ',':
                        sb.append(current);
                        if (last != '\\') {
                            sb.append('\n');
                            addIndentBlank(sb, indent);
                        }
                        break;
                    default:
                        sb.append(current);
                }
            }

            return sb.toString();
        }

        /**
         * 添加space
         *
         * @param sb
         * @param indent
         */
        private static void addIndentBlank(StringBuilder sb, int indent) {
            for (int i = 0; i < indent; i++) {
                sb.append('\t');
            }
        }

        /**
         * 对象转换
         *
         * @param jsonObject
         * @return
         */
        private void convertJSONObject(JSONObject jsonObject) {
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                if (entry.getValue() instanceof JSONArray) {
                    convertJSONArray((JSONArray) entry.getValue());
                }
                if (entry.getValue() instanceof JSONObject) {
                    convertJSONObject((JSONObject) entry.getValue());
                }
                if (entry.getValue() instanceof String) {
                    //value 可能是json格式
                    if (entry.getValue().toString().startsWith("{") && entry.getValue().toString().endsWith("}")) {// 对象
                        JSONObject innerJSONObject = JSON.parseObject(entry.getValue().toString());
                        convertJSONObject(innerJSONObject);
                        entry.setValue(innerJSONObject.toJSONString());
                    } else if (entry.getValue().toString().startsWith("[")
                            && entry.getValue().toString().endsWith("]")) {// 数组
                        JSONArray jsonArray = JSON.parseArray(entry.getValue().toString());
                        convertJSONArray(jsonArray);
                        entry.setValue(jsonArray.toJSONString());
                    } else {
                        if (RegExValidateUtils.isEmail(entry.getValue().toString())) {// 是否为邮箱
                            entry.setValue(convertEmail(entry.getValue().toString()));
                        } else if (RegExValidateUtils.isIDCard(entry.getValue().toString())) {// 是否为身份证号
                            entry.setValue(convertIDCard(entry.getValue().toString()));
                        } else if (RegExValidateUtils.isMobile(entry.getValue().toString())) {// 是否为手机号码
                            entry.setValue(convertMobile(entry.getValue().toString()));
                        } else if (sensitiveWordSet.contains(entry.getKey())) {// key是否为敏感字
                            entry.setValue(connvertCommon(entry.getValue().toString()));
                        }
                    }
                }
            }
        }

        /**
         * 数组转换
         *
         * @param jsonArray
         * @return
         */
        private void convertJSONArray(JSONArray jsonArray) {
            for (Object object : jsonArray) {
                if (object instanceof JSONObject) {
                    convertJSONObject((JSONObject) object);
                }
                if (object instanceof JSONArray) {
                    convertJSONArray((JSONArray) object);
                }
            }
        }
    }

    /**
     * 默认convertor
     */
    public static class DefaultCryptoConvertor implements ICryptoConvertor {
        @Override
        public String convert(Object value) {
            return connvertCommon(String.valueOf(value));
        }
    }

    /**
     * 默认敏感对象转换类
     */
    public static class DefaultSensitiveObjectFastJsonConvertor extends JavaBeanSerializer
            implements ICryptoConvertor, ValueFilter, PropertyPreFilter {
        private Map<String, ICryptoConvertor> convertorMap    = new HashMap<>();
        private Set<String>                   ignoreOutputSet = new HashSet<>();

        private Class<?>                      clazz;

        public DefaultSensitiveObjectFastJsonConvertor(Class<?> clazz) {
            super(clazz);
            this.clazz = clazz;
        }

        public DefaultSensitiveObjectFastJsonConvertor(Class<?> clazz, String... aliasList) {
            super(clazz, aliasList);
            this.clazz = clazz;
        }

        public DefaultSensitiveObjectFastJsonConvertor(Class<?> clazz, Map<String, String> aliasMap) {
            super(clazz, aliasMap);
            this.clazz = clazz;
        }

        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
                throws IOException {
            if (!serializer.getValueFilters().contains(this)) {
                serializer.getValueFilters().add(this);
            }
            if (!this.ignoreOutputSet.isEmpty() && !serializer.getPropertyPreFilters().contains(this)) {
                serializer.getPropertyPreFilters().add(this);
            }
            super.write(serializer, object, fieldName, fieldType, features);
        }

        @Override
        public boolean writeReference(JSONSerializer serializer, Object object, int fieldFeatures) {
            if (!serializer.getValueFilters().contains(this)) {
                serializer.getValueFilters().add(this);
            }
            if (!this.ignoreOutputSet.isEmpty() && !serializer.getPropertyPreFilters().contains(this)) {
                serializer.getPropertyPreFilters().add(this);
            }
            return super.writeReference(serializer, object, fieldFeatures);
        }

        public void add(String name, ICryptoConvertor convertor) {
            convertorMap.put(name, convertor);
        }

        public void ignoreOutPut(String name) {
            this.ignoreOutputSet.add(name);
        }

        @Override
        public boolean apply(JSONSerializer serializer, Object source, String name) {
            if (source == null) {
                return true;
            }

            if (clazz != null && !clazz.isInstance(source)) {
                return true;
            }

            if (this.ignoreOutputSet.contains(name)) {
                return false;
            }

            return true;
        }

        @Override
        public Object process(Object object, String name, Object value) {
            if (clazz != null && clazz.isInstance(object)) {
                ICryptoConvertor convertor = this.convertorMap.get(name);
                if (convertor != null) {
                    return convertor.convert(value);
                }
            }
            return value;
        }

        @Override
        public String convert(Object value) {
            return SensitiveObjectLogUtils.SensitiveObjectLogConvertorHolder.INSTANCE.toJSONString(value);
        }
    }
}

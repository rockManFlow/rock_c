package com.kuark.tool.advance.advance20201105util.log.desensitize.interfaces;

/**
 * 敏感信息转换器
 */
public interface ICryptoConvertor {
	/**
	 * 将信息转换成用*或其他字符替换的字符串
	 * @param value 输入
	 * @return
	 */
	String convert(Object value);
}

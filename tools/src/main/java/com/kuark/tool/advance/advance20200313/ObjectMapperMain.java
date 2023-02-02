package com.kuark.tool.advance.advance20200313;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuark.tool.advance.advance20200313.vo.QueryVoucherResp;
import com.kuark.tool.advance.advance20200313.vo.ResultResp;

import java.io.IOException;

/**
 * @author rock
 * @detail
 * @date 2020/6/1 16:36
 */
public class ObjectMapperMain {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonStr="{\n" +
                "    \"code\":\"00000\",\n" +
                "    \"data\":{\n" +
                "        \"records\":[\n" +
                "            {\n" +
                "                \"voucher_id\":1000\n" +
                "            },\n" +
                "            {\n" +
                "                \"voucher_id\":2000\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"message\":\"SUCCESS\"\n" +
                "}";
        TypeReference<ResultResp<QueryVoucherResp>> type=new TypeReference<ResultResp<QueryVoucherResp>>(){};
        Object readValue = mapper.readValue(jsonStr, type);
        System.out.println(readValue);
    }
}

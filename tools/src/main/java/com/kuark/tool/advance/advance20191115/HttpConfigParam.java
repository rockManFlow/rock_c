package com.kuark.tool.advance.advance20191115;

import lombok.Builder;
import lombok.Data;

/**
 * @author rock
 * @detail
 * @date 2019/12/13 14:26
 */
@Data
@Builder
public class HttpConfigParam {
    private Integer connectTimeout;
    private Integer socketTimeout;
    private Integer requestTimeout;
}

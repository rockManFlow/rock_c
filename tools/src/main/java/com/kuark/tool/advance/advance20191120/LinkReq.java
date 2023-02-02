package com.kuark.tool.advance.advance20191120;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author rock
 * @detail
 * @date 2019/11/13 15:20
 */
@Data
@Builder
public class LinkReq implements Serializable {
    private String reqNo;
    private String reqMsg;
    private String value;
}

package com.kuark.tool.advance.advance20201105util.excel.base;

import lombok.Data;

/**
 * @author rock
 * @detail 这里的排序和excel里面的排序一致
 * 示例接收实体类
 * @date 2020/11/4 17:12
 */
@Data
public class RetailerUploadData {
    private String storeName;
    private String contactName;
    private String contactPhone;
    private String location;
    private Long salesmanId;
    private String locationName;
}

package com.kuark.tool.advance.advance20201111.clone;

import java.io.Serializable;

/**
 * @author rock
 * @detail
 * @date 2021/6/2 11:34
 */
public class ParamCloneVo2 implements Serializable {
    private String detail;
    private Integer price;

    @Override
    public String toString() {
        return "ParamCloneVo{" +
                "detail='" + detail + '\'' +
                ", price=" + price +
                '}';
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

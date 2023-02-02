package com.kuark.tool;

import lombok.Data;

/**
 * @author rock
 * @detail
 * @date 2020/3/3 11:36
 */
public class TimePeride {
    private String start;
    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

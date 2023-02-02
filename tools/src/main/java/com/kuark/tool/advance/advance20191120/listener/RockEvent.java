package com.kuark.tool.advance.advance20191120.listener;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rock
 * @detail
 * @date 2019/11/25 20:27
 */
@Setter
@Getter
public class RockEvent {
    private String msgNo;
    private String msg;
    private String context;

    @Override
    public String toString(){
        return "msgNo:"+msgNo+"|msg:"+msg+"|context:"+context;
    }
}

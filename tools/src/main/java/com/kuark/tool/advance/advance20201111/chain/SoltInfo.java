package com.kuark.tool.advance.advance20201111.chain;

import lombok.Data;

@Data
public class SoltInfo {
    private SoltInfo next;

    private Integer no;

    public void entry(){
        System.out.println("out no:"+no);

        if(next!=null){
            next.entry();
        }
    }
}

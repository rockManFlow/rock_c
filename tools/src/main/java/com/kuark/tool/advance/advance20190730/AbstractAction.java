package com.kuark.tool.advance.advance20190730;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/30 13:59
 */
public abstract class AbstractAction implements Action{

    public Object execute(){
        Object req=assembleParam();
        Object resp= sendReq(req);
        return assembleResp(resp);
    }
}

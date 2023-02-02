package com.kuark.tool.advance.advance20220418.pay;

import com.kuark.tool.advance.advance20220418.pay.model.ReqModel;
import com.kuark.tool.advance.advance20220418.pay.model.RespModel;

/**
 * @author rock
 * @detail ¶¥¼¶½Ó¿Ú
 * @date 2022/4/18 14:38
 */
public interface IChannelService<T extends ReqModel,R extends RespModel> {
    R invoke(T request);
}

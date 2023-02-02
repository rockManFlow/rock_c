package com.kuark.tool.advance.advance20201111;

import lombok.Getter;

/**
 * @author rock
 * @detail
 * @date 2021/8/5 17:13
 */
@Getter
public enum TestEnum {
    NO_TYPE(0,"无权益"),
    OPEN_OWEALTH(1,"开通owealth账户"),
    OPEN_AUTO_INVEST(2,"开启自动投资"),
    ADD_HOLDER_CARD_USER(3,"开通联名卡加息权限"),
    SEND_COUPON(4,"发放联名卡加息劵"),
    USE_COUPON(5,"激活联名卡加息劵"),;
    private Integer code;
    private String desc;

    TestEnum(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }
}

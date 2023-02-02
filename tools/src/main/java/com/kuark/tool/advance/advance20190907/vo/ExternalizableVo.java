package com.kuark.tool.advance.advance20190907.vo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author rock
 * @detail
 * @date 2019/9/7 15:21
 */
public class ExternalizableVo implements Externalizable {
    private String name;
    private String pwd;

    //可以在这个里面自定义输出什么字段信息，不输出什么信息
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(pwd);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name=(String)in.readObject();
        this.pwd=(String)in.readObject();
    }
}

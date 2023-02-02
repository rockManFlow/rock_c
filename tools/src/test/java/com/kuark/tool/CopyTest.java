package com.kuark.tool;

import java.io.*;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/5/7 20:22
 */
public class CopyTest implements Serializable{
    private String name;
    private CopyVo copyVo;

    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream ba=new ByteArrayOutputStream();
        ObjectOutputStream ob=new ObjectOutputStream(ba);
        ob.writeObject(this);

        ByteArrayInputStream bai=new ByteArrayInputStream(ba.toByteArray());
        ObjectInputStream obi=new ObjectInputStream(bai);
        return obi.readObject();
    }
}

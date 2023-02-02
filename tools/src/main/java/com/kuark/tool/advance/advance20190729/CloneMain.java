package com.kuark.tool.advance.advance20190729;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import java.io.*;
import java.math.BigDecimal;

/**
 * @author rock
 * @detail
 * @date 2022/2/14 11:13
 */
@Data
public class CloneMain implements Cloneable, Serializable {
    private BigDecimal price;
    private CloneA a;

    /**
     * 深拷贝方式1
     * @return
     */
    @Override
    public CloneMain clone(){
        CloneMain b=null;
        try{
            //浅拷贝
            b=(CloneMain)super.clone();
            b.a=a.clone();//深拷贝
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 深拷贝第二种方式 Serializable
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public CloneMain deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream outputStream=new ObjectOutputStream(byteOutputStream);
        outputStream.writeObject(this);

        ByteArrayInputStream byteInputStream=new ByteArrayInputStream(byteOutputStream.toByteArray());
        ObjectInputStream inputStream=new ObjectInputStream(byteInputStream);
        return (CloneMain)inputStream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CloneMain cm1=new CloneMain();
        cm1.setPrice(BigDecimal.valueOf(100));
        CloneMain.CloneA ca=new CloneMain().new CloneA();
        ca.setAa("1111");
        ca.setAge(10);
        cm1.setA(ca);

        CloneMain cm2=cm1.deepClone();
        System.out.println(cm2);
    }

    @Data
    public class CloneA implements Cloneable,Serializable{
        private String aa;
        private Integer age;
        @Override
        public CloneA clone(){
            CloneA a=null;
            try{
                a=(CloneA)super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return a;
        }
    }
}

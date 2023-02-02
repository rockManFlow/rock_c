package com.kuark.tool.advance.advance20191220Algorithm.ecc;

import com.kuark.tool.advance.advance20191220Algorithm.ecc.elliptic.*;
import com.kuark.tool.advance.advance20201105util.formal.MD5Util;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Random;

/**
 * 通过验证
 */
@Slf4j
public class EcdhAlgorithm {
    private BigInteger selfRandom;
    private ECPoint point;

    public EcdhAlgorithm(int numBits,boolean definitionEllipticCurve){
        try {
            selfRandom = generateRandom(numBits);
            point = generateECPoint(definitionEllipticCurve);
        }catch (Exception e){

        }
    }


    /**
     * 生成指定位数的大数随机数
     * @param numBits 大数位数
     * @return
     */
    private BigInteger generateRandom(int numBits){
        /**
         * 随机数建议采用默认种子，如果是指定种子
         */
        return new BigInteger(numBits, new Random());
    }

    /**
     * todo 这块可以抽象一下，便于自定义各种实现方式
     * @param definitionEllipticCurve
     * @return
     * @throws InsecureCurveException
     * @throws NotOnMotherException
     */
    private ECPoint generateECPoint(boolean definitionEllipticCurve) throws InsecureCurveException, NotOnMotherException {
        EllipticCurve e ;
        ECPoint point;
        if(definitionEllipticCurve){
            e = new EllipticCurve(new BigInteger("2"),
                    new BigInteger("4"), new BigInteger("11"));
            System.out.println("EllipticCurve: " + e + " created succesfully!");

            //前面定义的曲线，下面定义的点必须是在这个曲线上的，否则会报错。定义了x的值，之后y的值需要通过x的值计算得出
            // 生成基点G---找到该曲线上的一个点，及x\y的值
            point=new ECPoint(e, new BigInteger("2"), new BigInteger("4"));
        }else {
            e = new EllipticCurve(new secp256r1());
            point = new ECPoint(e.getGenerator().compress(), e);
        }

        return point;
    }

    /**
     * 计算自己的计算结果
     * @return
     */
    public ECPoint calculatePoint(){
        return this.point.multiply(selfRandom);
    }

    /**
     * 计算协商结果
     * @param sidePoint
     * @return
     */
    public String calculateResult(ECPoint sidePoint) {
        ECPoint multiply = sidePoint.multiply(this.selfRandom);
        return MD5Util.stringMD5(multiply.toString());
    }
}



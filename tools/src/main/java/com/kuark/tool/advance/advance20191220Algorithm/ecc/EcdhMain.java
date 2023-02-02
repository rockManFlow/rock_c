package com.kuark.tool.advance.advance20191220Algorithm.ecc;

import com.kuark.tool.advance.advance20191220Algorithm.ecc.elliptic.ECPoint;
import com.kuark.tool.advance.advance20191220Algorithm.ecc.elliptic.EllipticCurve;
import com.kuark.tool.advance.advance20191220Algorithm.ecc.elliptic.InsecureCurveException;
import com.kuark.tool.advance.advance20191220Algorithm.ecc.elliptic.NotOnMotherException;

import java.math.BigInteger;
import java.util.Random;

/**
 * ecdh 算法：密钥协商算法
 * 用于在不安全的网络环境中进行传输用于加密的密钥
 *
 * 解释
 * ECDH 全称是椭圆曲线迪菲-赫尔曼秘钥交换（Elliptic Curve Diffie–Hellman key Exchange），
 * 主要是用来在一个不安全的通道中建立起安全的共有加密资料，一般来说交换的都是私钥，
 * 这个密钥一般作为“对称加密”的密钥而被双方在后续数据传输中使用。
 *
 * 核心：ECDH是建立在这样一个前提之上的，给定椭圆曲线上的一个点P，一个整数k，求Q=KP很容易；但是通过Q，P求解K很难。---离散对数问题

 * 密钥交换过程：
 *
 * 假设密钥交换双方为Alice、Bob，其有共享曲线参数（椭圆曲线E、阶N、基点G）。
 *
 *  1.Alice生成随机整数a，计算A=a*G。Bob生成随机整数b，计算B=b*G。
 *  2.Alice将A传递给Bob。A的传递可以公开，即攻击者可以获取A。由于椭圆曲线的离散对数问题是难题，所以攻击者不可以通过A、G计算出a。Bob将B传递给Alice。同理，B的传递可以公开。
 *
 * 3.Bob收到Alice传递的A，计算Q=b*A
 *
 * 4.Alice收到Bob传递的B，计算Q‘=a*B
 *
 *  总结：
 *
 *   Alice、Bob双方即得Q=b*A=b*(a*G)=(b*a)*G=(a*b)*G=a*(b*G)=a*B=Q' (交换律和结合律)，即双方得到一致的密钥Q。
 *
 *   todo 最终结果Q即为双方协商的密钥。（即使中间信息被改变，导致Q计算不一致，之后的通信数据也是没法解密的---没问题）
 */
public class EcdhMain {
    public static void main2(String[] args) throws InsecureCurveException, NotOnMotherException {
        Random r1 = new Random(100);
        BigInteger a = new BigInteger(60, r1);// Alice生成随机整数a
        System.out.println("Alice:" + a);
        Random r2 = new Random(20);
        BigInteger b = new BigInteger(50, r2);// Bob生成随机整数b
        System.out.println("Bob:" + b);

        //构建一个椭圆曲线---公式定死的，不太好
        EllipticCurve e = new EllipticCurve(new BigInteger("2"),
                new BigInteger("4"), new BigInteger("11"));
        System.out.println("EllipticCurve: " + e + " created succesfully!");

        //前面定义的曲线，下面定义的点必须是在这个曲线上的，否则会报错。定义了x的值，之后y的值需要通过x的值计算得出
        // 生成基点G---找到该曲线上的一个点，及x\y的值
        ECPoint G = new ECPoint(e, new BigInteger("2"), new BigInteger("4"));


        ECPoint A, B;
        A = G.multiply(a);// 计算A=a*G    A这个是需要发送给B的
        System.out.println("A=a*G: "+a + " * " + G + " = " + A);
        B = G.multiply(b);// 计算B=b*G    B这个是需要发送给A的
        System.out.println("B=b*G: "+b + " * " + G + " = " + B);

        ECPoint Q1, Q2;
        Q1 = A.multiply(b);// Bob收到Alice传递的A，计算Q =b*A
        Q2 = B.multiply(a);// Alice收到Bob传递的B，计算Q`=a*B
        System.out.println("Q1:"+Q1);
        System.out.println("Q2:"+Q2);
        System.out.println(Q1.equals(Q2));

    }

    public static void main(String[] args) {
        EcdhAlgorithm a=new EcdhAlgorithm(20,false);
        ECPoint A = a.calculatePoint();

        EcdhAlgorithm b=new EcdhAlgorithm(10,false);
        ECPoint B = b.calculatePoint();

        //
        String resultA = a.calculateResult(B);
        String resultB = b.calculateResult(A);
        System.out.println("resultA="+resultA);//AFBA4FDBAA5C2243E6C29266DA54F722  CD2270AC75A490CE46E1D43F477EE1DD
        System.out.println("resultB="+resultB);
        System.out.println(resultB.equals(resultA));//
    }
}

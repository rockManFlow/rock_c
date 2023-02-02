package com.kuark.tool.advance.advance20201111;

/**
 * @author rock
 * @detail instanceof用法：类的多级继承使用instanceof也是会返回true，属于同一个继承树分支
 * @date 2021/6/10 18:00
 */
public class InstanceofMain {
    public static void main(String[] args) {
        A a = null;
        B b = null;
        boolean result;
        result = a instanceof A;
        System.out.println(result); // 结果：false null用instanceof跟任何类型比较时都是false
        result = b instanceof B;
        System.out.println(result); // 结果：false null用instanceof跟任何类型比较时都是false


        a = new B();
        b = new B();
        result = a instanceof A;
        System.out.println(result); // 结果：true a是接口A的实例对象引用指向子类类B，类B实现了接口A，所以属于同一个继承树分支
        result = a instanceof B;
        System.out.println(result); // 结果：true a是接口A的实例对象引用指向子类类B，类B实现了接口A，所以属于同一个继承树分支
        result = b instanceof A;
        System.out.println(result);// 结果：true b是类B的实例对象，类B实现了接口A，所以属于同一个继承树分支
        result = b instanceof B;
        System.out.println(result);// 结果：true b是类B的实例对象，类B实现了接口A，所以属于同一个继承树分支


        B b2 = new C();
        result = b2 instanceof A;
        System.out.println(result); // 结果：true b2是父类B引用指向子类C，类B实现了接口A，所以属于同一个继承树分支
        result = b2 instanceof B;
        System.out.println(result); // 结果：true b2是父类B引用指向子类C，所以属于同一个继承树分支
        result = b2 instanceof C;
        System.out.println(result); // 结果：true b2是父类B引用指向子类C，所以属于同一个继承树分支
    }
    public interface A {
    }

    public static class B implements A{

    }

    public static class C extends B{
    }
}

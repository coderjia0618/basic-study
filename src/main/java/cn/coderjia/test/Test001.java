package cn.coderjia.test;

/**
 * @Author CoderJiA
 * @Description Test001
 * @Date 13/2/19 上午10:42
 **/
public class Test001 {

    public static void main(String[] args) {
        System.out.println(B.CONST);
    }

}


class A {

    public static final String CONST = "A";

    static {
        System.out.println("A static init...");
    }
}

class B extends A {
    static {
        System.out.println("B static init ...");
    }
}
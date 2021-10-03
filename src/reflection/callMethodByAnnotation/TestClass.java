package reflection.callMethodByAnnotation;

public class TestClass {
    @Weight(10)
    void method1() {
        System.out.println("method1");
    }

    @Weight(3)
    void method2() {
        System.out.println("method2");
    }


    void method3() {
        System.out.println("method3");
    }
}

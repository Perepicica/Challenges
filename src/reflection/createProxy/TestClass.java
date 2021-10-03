package reflection.createProxy;

public class TestClass implements Base {
    @Logging
    public void method1() {
        System.out.println("TestClass method1");
    }

    public void method2() {
        System.out.println("TestClass method2");
    }
}


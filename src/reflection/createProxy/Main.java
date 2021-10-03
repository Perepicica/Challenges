package reflection.createProxy;


public class Main {
    public static void main(String[] args) throws Exception {
        Base proxy = createProxy(new TestClass());
        proxy.method1();

    }

    /**
     * proxy для объекта;
     * гарантируется, что у всех вызываемых методов отсутствуют аргументы;
     * если метод аннотирован аннотацией @Logging,
     * то перед вызовом метода вывести в консоль "Before call MethodName",
     * a после вызова метода вывести в консоль "After call MethodName".
     * @param object
     */
    private static Base createProxy(Base object) {
        return (Base) java.lang.reflect.Proxy.newProxyInstance(
                Base.class.getClassLoader(),
                new Class []{Base.class},
                (proxy, method, args) -> {
                    if (object.getClass().getDeclaredMethod(method.getName()).isAnnotationPresent(Logging.class)) {
                        System.out.println("Before call " + method.getName());
                        method.invoke(object, args);
                        System.out.println("After call " + method.getName());
                    } else {
                        method.invoke(object, args);
                    }
                    return proxy;
                }
        );
    }
}

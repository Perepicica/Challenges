package reflection.callMethodByName;
import java.lang.reflect.Method;


public class Main {
    public static void main(String[] args) throws Exception{
        callMethodByName(new TestClass(), "sayHello");
    }

    /**
     * у объекта вызывается метод с соответствующим названием;
     * гарантируется, что у объекта названия всех методов уникальны.
     * @param object объект
     * @param methodName название метода
     * @param params аргументы вызываемого метода
     */
    private static void callMethodByName(Object object, String methodName, Object... params) throws Exception {
        for (Method m : object.getClass().getDeclaredMethods()) {
            if (m.getName().equals(methodName))
                m.invoke(object,params);
        }
    }
}

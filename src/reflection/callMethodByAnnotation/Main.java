package reflection.callMethodByAnnotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) {
        callMethodByAnnotation(new TestClass());
    }

    /**
     * вызываются все методы, помеченные аннотацией @Weight, в порядке возрастания их весов;
     * гарантируется, что значения весов у методов уникальны;
     * гарантируется, что у всех вызываемых методов отсутствуют аргументы.
     * @param object объект
     */
    private static void callMethodByAnnotation(Object object)  {
        Method[] methods = object.getClass().getDeclaredMethods();
        Map<Integer, String> map = new TreeMap<>();
        for (Method m : methods) {
            Weight a = m.getDeclaredAnnotation(Weight.class);
            if (a != null) {
                map.put(a.value(), m.getName());
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}

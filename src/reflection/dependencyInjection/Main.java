package reflection.dependencyInjection;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws Exception {
        dependencyInjection(Arrays.asList(new TestClassA(),new TestClassB(),new TestClassC()));
    }

    /**
     * гарантируется, что каждый класс представлен единственным объектом;
     * в объектах могут встречаться поля, помеченные аннотацией @Autowired;
     * для полей со стереотипом @Autowired выбирается из пула объектов подходящий объект
     * по типу и сетится
     * если подходящий объект по типу не найден, то нужно бросить исключение CandidateNotFindException.
     *
     * @param objects пул объектов
     * @throws Exception /
     */
    private static void dependencyInjection(List<Object> objects) throws Exception {
        Map<Class, Object> mapObjects = objects.stream().collect(Collectors.toMap(Object::getClass, o -> o));
        for (Object obj : objects) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field fld : fields) {
                if (fld.isAnnotationPresent(Autowired.class)) {
                    if (mapObjects.containsKey(fld.getType())) {
                        fld.set(obj, mapObjects.get(fld.getType()));
                    } else throw new CandidateNotFindException();
                }
            }
        }
    }
}

package reflection.operationContainer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatePoolOperation {
    /**
     * в некоторых объектах встречаются методы с аннотацией @BotRequestMapping;
     * в аннотации @BotRequestMapping представлен url некоторой операции;
     * при вызове операции по url должен вызываться метод с таким же url;
     * гарантируется, что все методы без аргументов;
     * гарантируется, что каждый класс представлен единственным объектом.
     *
     * @param objects пул объектов
     * @return HashMap с операциями, где ключ - url операции, а значением — сама операция
     * @throws Exception
     */
    Map<String, Operation> createPoolOperation(List<Object> objects) throws Exception {
        Map<String, Operation> map = new HashMap<>();
        for (Object obj : objects) {
            for (Method meth : obj.getClass().getDeclaredMethods()) {
                if (meth.isAnnotationPresent(BotRequestMapping.class)) {
                    map.put(meth.getDeclaredAnnotation(BotRequestMapping.class).value(),
                            () -> meth.invoke(obj));
                }
            }
        }
        return map;
    }

}

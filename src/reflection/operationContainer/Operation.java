package reflection.operationContainer;

@FunctionalInterface
interface Operation {
    void call() throws Exception;
}
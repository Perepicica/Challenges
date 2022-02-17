package stream.stepic.asynchronous;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class QueryUtils {
    /**
     * the method must wait until all the CompletableFuture objects are completed and then return the map;
     * if there has been an exception in the query, the specified defaultValue must be put in the map for a particular table.
     */
    public static <R> Map<String, R> execute(List<String> tables, Function<String, R> query, R defaultValue) {
        Map<String, R> tableToResultMap = new ConcurrentHashMap<>();

        CompletableFuture<?>[] futures = tables.stream()
                .map(table -> CompletableFuture
                        .supplyAsync(() -> tableToResultMap.put(table, query.apply(table)))
                        .exceptionally(th -> defaultValue))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
        return tableToResultMap;
    }
}
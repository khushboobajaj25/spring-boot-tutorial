import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Q12: Word frequency (sorted by frequency desc)
        List<String> words = Arrays.asList(
                "apple", "banana", "apple", "orange", "banana", "banana",
                "apple", "kiwi", "banana", "kiwi", "kiwi", "kiwi", "kiwi");

        words.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(comparingLong((es) -> es.getValue()))
                .sorted((c1, c2) -> Long.compare(c1.getValue(), c2.getValue()))
                .forEach(System.out::println);
        // System.out.println(map);
        // System.out.println(m);

    }

    public static Comparator<Map.Entry<String, Long>> comparingLong(
            ToLongFunction<? super Map.Entry<String, Long>> keyExtractor) {
        return (c1, c2) -> Long.compare(keyExtractor.applyAsLong(c1),
                keyExtractor.applyAsLong(c2));
    }
}
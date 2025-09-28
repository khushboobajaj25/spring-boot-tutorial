package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LazyEvalutionDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Bob", "Alice", "Charlie", "David");

        Stream<String> nstream = names
                .stream();
        Stream<String> s1 = applyFilter(nstream);
        Stream<String> s2 = applyFilter2(s1);
        s2.forEach(System.out::println);
        System.out.println(s2.count());
    }

    public static Stream<String> applyFilter(Stream<String> s) {
        return s.filter(f -> f.length() > 3);
    }

    public static Stream<String> applyFilter2(Stream<String> s) {
        return s.filter(f -> f.length() > 1);
    }
    /**
     * Khushboo:
     * Alice, Charlie, David
     * 3
     * 
     * Prium:
     * "All Print"
     * 4
     * 
     * Anisha:
     * "All Print"
     * 4
     * 
     * Karan:
     * ""
     * Runtime error on line 16
     */
}
package streams;

import java.lang.foreign.Linker.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOps {
        public static void main(String[] args) {
                List<Integer> lst = List.of(1, 2, 3, 4, 5);
                // filter
                Stream<Integer> s1 = lst.stream().filter((num) -> num % 2 == 0);

                // map
                Stream<String> s2 = lst.stream().map((num) -> String.valueOf(num));
                // peek

                lst.stream()
                                .filter(num -> num % 2 == 0)
                                .peek(num -> System.out.println(num))
                                .map(num -> String.valueOf(num))
                                .forEach(num -> System.out.println(num));
                // sorted
                lst.stream()
                                .sorted();
                // skip
                System.out.println(lst.stream().skip(2).toList());

                // limit
                System.out.println(lst.stream().limit(2).toList());
                // flatMap
                List<List<Integer>> lst2 = List.of(
                                List.of(1, 2, 3, 4, 5),
                                List.of(6, 7, 8, 9, 10));
                // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
                lst2.stream().flatMap(l -> l.stream());

                List<List<List<Integer>>> lstOfLst = List.of(
                                List.of(
                                                List.of(1, 2, 3, 4),
                                                List.of(1, 2, 3, 4)));
                // 1, 2, 3, 4, 5, 6
                var lstFlat = lstOfLst.stream().flatMap(l -> l.stream()).flatMap(l -> l.stream())
                                .filter(n -> n % 2 == 0)
                                .toList();
                System.out.println(lstFlat);
                // distinct
                System.out.println(
                                lstFlat.stream().distinct().toList());

                // findAny, findFirst, min, max, reduce
                // Optional<Integer> op = lst.stream().findAny();
                System.out.println(lst.stream().findFirst().get());
                System.out.println(lst.stream().findAny().get());
                System.out.println(lst.stream().min((a, b) -> a - b).get());
                System.out.println(lst.stream().max((a, b) -> a - b).get());
                System.out.println(lst.stream().reduce(0, (curr, prev) -> curr + prev));

                // Example: Length gt 3
                List<String> names = Arrays.asList("Karan", "Purab", "Somesh", "Jayesh", "Bob", "Anu");

                // Given a array of integers find the product of all odd numbers
        }
}

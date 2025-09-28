package streams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public class TerminalOps {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        
        // for loop
        // for each loop
        // iterators
        // streams

        // Terminal functions
        // forEach
        // toList
        // collect
        // count
        // anyMatch, allMatch, noneMatch
        // findFirst, findAny
        // min, max
        // forEachOrdered

        nums.stream().forEach((num) -> {
            System.out.println(num);
        });

        List<Integer> nums1 = nums.stream().toList();
        System.out.println(nums1);

        Long count = nums1.stream().count();

        System.out.println(count);

        // anyMatch, allMatch, noneMatch
        boolean isAnyEven = nums1.stream().anyMatch((num) -> num % 2 == 0);
        System.out.println(isAnyEven);

        boolean isAllMatch = nums1.stream().allMatch((num) -> num % 1 == 0);
        System.out.println(isAllMatch);

        boolean isNoneMatch = nums1.stream().noneMatch((num) -> num % 10 == 0);
        System.out.println(isNoneMatch);
    }
}

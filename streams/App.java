package streams;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@FunctionalInterface
interface InnerApp {
    void display(int a);
}

public class App {
    public static void main(String[] args) {
        // Preicate, Function, Consumer, Supplier
        System.out.println("---- Predicate ----");
        Predicate<Integer> isEven = x -> x % 2 == 0;

        System.out.println(isEven.test(5));
        System.out.println(isEven.test(2));

        System.out.println("---- Function ----");
        Function<String, String> toLowerCase = x -> x.toLowerCase();
        System.out.println(toLowerCase.apply("KARAN"));

        Function<Integer, Integer> addTwo = x -> x + 2;
        Function<Integer, Integer> mulFive = x -> x * 5;

        int var1 = mulFive.apply(addTwo.apply(10));
        System.out.println(var1);

        int var = addTwo.andThen(mulFive).andThen(mulFive).apply(10);
        System.out.println(var);
        Function<String, Function<Integer, String>> concatInt = str -> i1 -> str + " " + i1;

        System.out.println("---- Consumer ----");
        Consumer<String> print1st = str -> System.out.println(str.charAt(0));
        print1st.accept("Karan");

        System.out.println("---- Supplier ----");
        Supplier<String> sayHello = () -> "Hello";
        System.out.println(sayHello.get());

        // BiPredicate, BiConsumer, BiFunction
        BiPredicate<Integer, Integer> isSumEven = (x, y) -> (x + y) % 2 == 0;
        BiConsumer<Integer, Integer> printSum = (x, y) -> System.out.println(x + y);
        BiFunction<String, String, Integer> getConcatLen = (s1, s2) -> s1.length() + s2.length();

        // UnaryOperator, BinaryOperator
        UnaryOperator<Integer> addTwo_ = x -> x + 2;
        BinaryOperator<String> getConcatLen_ = (s1, s2) -> String.valueOf((s1.length() + s2.length()));
    }
}

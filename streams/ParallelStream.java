package streams;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStream {
    public static void main(String[] args) {
        List<Long> lst = Stream.iterate(1l, (p) -> p + 1).limit(10000).toList();
        Long startTime = System.currentTimeMillis();
        lst.stream().forEach((n) -> factorial(n));
        Long endTime = System.currentTimeMillis();

        System.out.println("Stream " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        lst.parallelStream().forEach((n) -> factorial(n));
        endTime = System.currentTimeMillis();

        System.out.println("Parallel Stream " + (endTime - startTime) + " ms");
    }

    public static BigInteger factorial(Long n) {
        BigInteger result = new BigInteger(n.toString());
        for (Long i = 2l; i <= n; i++) {
            result = result.multiply(new BigInteger(i.toString()));
        }
        return result;
    }
}

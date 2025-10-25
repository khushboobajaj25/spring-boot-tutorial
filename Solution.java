import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Result {

    public static List<Integer> sortTransactions(List<Integer> transactions) {
        int n = transactions.size();
        if (n == 0)
            return new ArrayList<>();

        return transactions.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((a, b) -> {
                    int freqCompare = -b.getValue().compareTo(a.getValue());
                    if (freqCompare != 0)
                        return freqCompare;
                    return a.getKey().compareTo(b.getKey());
                })
                .flatMap(entry -> Stream.generate(() -> entry.getKey()).limit(entry.getValue()))
                .collect(Collectors.toList());
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        // BufferedReader bufferedReader = new BufferedReader(new
        // InputStreamReader(System.in));
        // BufferedWriter bufferedWriter = new BufferedWriter(new
        // FileWriter(System.getenv("OUTPUT_PATH")));

        // int transactionsCount = Integer.parseInt(bufferedReader.readLine().trim());

        // List<Integer> transactions = new ArrayList<>();

        // for (int i = 0; i < transactionsCount; i++) {
        // int transactionsItem = Integer.parseInt(bufferedReader.readLine().trim());
        // transactions.add(transactionsItem);
        // }

        // List<Integer> result = Result.sortTransactions(transactions);

        // for (int i = 0; i < result.size(); i++) {
        // bufferedWriter.write(String.valueOf(result.get(i)));

        // if (i != result.size() - 1) {
        // bufferedWriter.write("\n");
        // }
        // }

        // bufferedWriter.newLine();

        // bufferedReader.close();
        // bufferedWriter.close();
        List<Integer> res1 = Result.sortTransactions(List.of(3, 1, 2, 2, 4));
        List<Integer> res2 = Result.sortTransactions(List.of(8, 5, 5, 5, 5, 1, 1, 1, 4, 4, 4));
        System.out.println(res1);
        System.out.println(res2);

    }
}
package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorsDemo {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Bharlie", "David", "Eve");

        // toList
        names.stream().collect(Collectors.toList());
        names.stream().collect(Collectors.toSet());
        names.stream().collect(Collectors.counting());

        // toCollection
        LinkedList<String> linkedLst = names.stream().collect(Collectors.toCollection(() -> {
            LinkedList<String> l = new LinkedList<>();
            System.out.println(l.hashCode());
            System.out.println(System.identityHashCode(l));
            return l;
        }));
        System.out.println(System.identityHashCode(linkedLst));
        System.out.println(linkedLst.hashCode());
        linkedLst.add("Anisha");
        System.out.println(linkedLst.hashCode());

        // joining
        String jo = names.stream().collect(
                Collectors.joining(","));
        System.out.println(jo);

        // summarizing
        IntSummaryStatistics statistics = names.stream().collect(
                Collectors.summarizingInt((name) -> name.length()));
        System.out.println(statistics);

        System.out.println(names.stream().collect(
                Collectors.averagingInt((name) -> name.length())));

        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple");

        System.out.println(
                words.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.filtering((name) -> name.length() > 5, Collectors.toList()))));

        System.out.println(
                names.stream().collect(
                        Collectors.partitioningBy(
                                (name) -> name.length() > 5,
                                Collectors.partitioningBy(name -> name.length() > 3, Collectors.counting()))));

        Map<Character, String> groupedNames = names.stream()
                .collect(Collectors.groupingBy(
                        name -> name.charAt(0),
                        Collectors.joining(", ")));
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 50000),
                new Employee("Bob", 75000),
                new Employee("Charlie", 60000),
                new Employee("Diana", 90000),
                new Employee("Eve", 85000),
                new Employee("Frank", 40000));
        System.out.println(
                employees.stream().sorted(
                        Comparator.comparingInt(Employee::getSalary).reversed()).limit(3)
                        .collect(Collectors.toList()));

        employees.stream().forEach(System.out::println);
        // Q13: Multi-level grouping (Department + Salary range)
        List<EmployeeDept> empDept = Arrays.asList(
                new EmployeeDept("Alice", "HR", 45000),
                new EmployeeDept("Bob", "IT", 75000),
                new EmployeeDept("Charlie", "IT", 95000),
                new EmployeeDept("Diana", "Finance", 60000),
                new EmployeeDept("Eve", "HR", 70000),
                new EmployeeDept("Frank", "Finance", 85000));
        System.out.println(empDept.stream().collect(Collectors.groupingBy(
                (emp) -> emp.getDepartment(),
                Collectors.partitioningBy(
                        (emp) -> emp.getSalary() > 70000))));

        System.out.println(
                empDept
                        .stream()
                        .collect(Collectors
                                .groupingBy(e -> e.department,
                                        Collectors.groupingBy(
                                                e -> e.salary > 70_000 ? "HIGH" : "LOW"))));
        // Q12: Word frequency (sorted by frequency desc)
        words = Arrays.asList(
                "apple", "banana", "orange", "banana",
                "apple", "kiwi", "banana", "kiwi", "kiwi");
        // Map<String, Long> map =
        List<Entry<String, Long>> map = words.stream().collect(Collectors
                .groupingBy((na) -> na, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((es1, es2) -> (int) (es2.getValue() - es1.getValue()))
                // .sorted(Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
        // .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        System.out.println(map);
        String str = "KarKan";
        Object result = (char)str.chars()
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(es -> es.getValue() == 1)
                .findFirst()
                .get().getKey()
                .intValue();
        System.out.println(result);
    }
}

class EmployeeDept {
    String name;
    String department;
    double salary;

    EmployeeDept(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return name + " - " + department + " - " + salary;
    }
}

class Employee {
    String name;
    int salary;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public int getSalary() {
        return this.salary;
    }
}
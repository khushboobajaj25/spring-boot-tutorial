package oops;

// public class Main {
//     public static void main(String[] args) {
//         Parent child = new Child();
//         System.out.println("Value of x from Child: " + child.x);
//         System.out.println("Calling display method:");
//         child.display();
//     }
// }

class Parent {
    int x = 10;

    public void print() {
        System.out.println("Parent class display method");
    }

    public void display() {
        System.out.println("Value of x from Parent: " + x);
    }
}

class Child extends Parent {
    int x = 20;

    public void print() {
        System.out.println("Child class display method");
    }

    public void display() {
        System.out.println("Value of x from Child: " + x);
    }
}

public class Main {
    public static void main(String[] args) {
        Parent child = new Child();
        child.display();
        System.out.println("Value of x from Parent: " + child.x);
    }
}
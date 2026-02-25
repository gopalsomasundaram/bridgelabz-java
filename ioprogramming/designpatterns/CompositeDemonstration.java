/**
 * This program is a demonstration of the Composite design pattern.
 * It treats individual employees and groups of employees uniformly.
 */

package ioprogramming.designpatterns;

import java.util.*;

interface Employee {
    void showDetails();
}

class Developer implements Employee {
    private String name;
    public Developer(String name) { this.name = name; }
    public void showDetails() { System.out.println("Developer: " + name); }
}

class Manager implements Employee {
    private String name;
    private List<Employee> subordinates = new ArrayList<>();

    public Manager(String name) { this.name = name; }
    public void add(Employee e) { subordinates.add(e); }
    public void remove(Employee e) { subordinates.remove(e); }

    public void showDetails() {
        System.out.println("Manager: " + name);
        for (Employee e : subordinates) {
            e.showDetails();
        }
    }
}

public class CompositeDemonstration {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Alice");
        Developer dev2 = new Developer("Bob");
        Manager engManager = new Manager("Charlie");

        engManager.add(dev1);
        engManager.add(dev2);

        engManager.showDetails();
    }
}
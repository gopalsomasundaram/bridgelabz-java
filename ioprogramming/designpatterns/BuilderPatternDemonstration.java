/**
 * This program demonstrates Builder design pattern
 */
package ioprogramming.designpatterns;

class User{
    private int age;
    private String name;

    private User(Builder b) {
        name = b.name;
        age = b.age;
    }
        static class Builder {
            private String name;
            private int age;

            Builder name(String n) {
                name = n;
                return this;
            }

            Builder age(int num) {
                age = num;
                return this;
            }

            User build() {
                return new User(this);
            }
        }
        void print(){
        System.out.println("Name: "+name+"\nAge: "+age);
        }
}

public class BuilderPatternDemonstration {
    public static void main(String[] args){
        User user = new User.Builder()
                .age(44)
                .name("Modulus Primus")
                .build();
        user.print();
    }
}

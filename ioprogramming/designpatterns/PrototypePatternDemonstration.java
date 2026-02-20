/**
 * This program demonstrates usage of prototype design pattern
 */
package ioprogramming.designpatterns;

//class to be clones
class Employees implements Cloneable{
    private String name;
    //override of clone method with exception throws
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    String getName(){
        return name;
    }
    void setName(String name){
        this.name = name;
    }
}

public class PrototypePatternDemonstration {
    public static void main(String[] args){
        Employees emp1 = new Employees();
        emp1.setName("Henry Dumaz");
        System.out.println("originale name: "+emp1.getName());
        try {
            //cloning instance with management of exception
            Employees emp2 = (Employees) emp1.clone();
            System.out.println("clone Name: "+emp2.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

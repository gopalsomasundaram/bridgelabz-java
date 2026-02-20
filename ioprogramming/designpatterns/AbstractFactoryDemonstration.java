/**
 * This program demonstrates the Abstract Factory Design pattern
 */
package ioprogramming.designpatterns;

interface Car{void getType();}

interface Bike{void  getType();}

//concrete implementation
class Sedan implements Car{
    @Override
    public void getType(){
        System.out.println("Sedan");
    }
}
//concrete implementation
class SUV implements Car{
    @Override
    public void getType(){
        System.out.println("SUV");
    }

}
//concrete implementation
class SportsBike implements Bike{
    @Override
    public void getType(){
        System.out.println("Sports Bike");
    }
}
//concrete implementation
class NormalBike implements Bike{
    @Override
    public void getType(){
        System.out.println("Normal Bike");
    }
}
//vehicle factory serving as interface for car and bike factories
interface VehicleFactory{
    Car createCar();
    Bike createBike();
}
//performance car and bike factory implementation
class PerformaceFactory implements VehicleFactory{
    @Override
    public Car createCar(){
        return new Sedan();
    }
    @Override
    public Bike createBike(){
        return new SportsBike();
    }
}
//normal bike and car factory implementation
class NormalFactory implements VehicleFactory{
    @Override
    public Car createCar(){
        return new SUV();
    }
    @Override
    public Bike createBike(){
        return new NormalBike();
    }
}
//main class
public class AbstractFactoryDemonstration {
    public static void main (String[] args){
        VehicleFactory factory = new NormalFactory();
        Car firstCar = factory.createCar();
        Bike firstBike = factory.createBike();

        firstCar.getType();
        firstBike.getType();

        factory = new PerformaceFactory();
        Car secondCar = factory.createCar();
        Bike secondBike = factory.createBike();

        secondCar.getType();
        secondBike.getType();
    }
}

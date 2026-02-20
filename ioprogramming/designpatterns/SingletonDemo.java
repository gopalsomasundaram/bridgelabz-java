/**
 * This program demonstrates the use of singleton design pattern
 */
package ioprogramming.designpatterns;
//singleton class
class Logger{
    private static Logger singleInstance;
    //private constructor to not allow construction of class instances
    private Logger(){}
    //method to access only class instance
    public static Logger getInstance(){
        if(singleInstance == null){
            singleInstance = new Logger();
        }
        return singleInstance;
    }
    void log(String message){
        System.out.println(message);
    }
}
//main class
public class SingletonDemo {
    public static void main(String[] args){
        Logger inst = Logger.getInstance();
        inst.log("TEST");
        inst = Logger.getInstance();
        inst.log("ANOTHER TEST");
    }
}

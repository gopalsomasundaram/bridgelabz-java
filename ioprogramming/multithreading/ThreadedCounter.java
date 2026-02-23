/**
 * This program implements a simple threaded counter that counts from 1 to
 * a limit given by the user. It does this by implementing Runnable and
 * overriding the run method. It also uses Object for synchronization.
 */
package ioprogramming.multithreading;

import java.util.Scanner;

//class implementing Runnable interface for Multithreading
class Counter implements Runnable{
    //will be shared across multiple threads
    private static int count = 1;
    private final int limit;
    //object used for controlling which thread is allowed to update count field
    private static final Object lock = new Object();

    public Counter(int limit){
        this.limit = limit;
    }
    @Override
    public void run(){
        while(true){
            //ensures only one thread can run this block at a time
            synchronized (lock){
                if(count > limit){
                    break;
                }
                System.out.println(count);
                count++;
            }
            try{
                //sleep to slow down execution and give other threads time to grab lock
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
//main class
public class ThreadedCounter {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter limit for counter: ");
        Counter obj = new Counter(scanner.nextInt());
        Thread t1 = new Thread(obj);
        t1.start();
    }
}

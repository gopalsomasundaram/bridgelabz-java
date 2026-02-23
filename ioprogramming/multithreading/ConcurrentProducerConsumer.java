/**
 * This program implements a producer - consumer system that share a resource
 * (BlockingQueue) one thread produces tasks and adds them to the shared resource,
 * The other thread consumes the task and moves onto the next one when it's done
 * processing its current task. When it reads a task named -1 it stops.
 */
package ioprogramming.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.Scanner;

//This class produces tasks and adds them to shared resource
class Producer implements Runnable{
    private final BlockingQueue<Integer> sharedQueue;   //synchronized Queue
    private final int limit;    //controls total number of processes to be created

    //constructor to set fields
    public Producer(BlockingQueue<Integer> sharedQueue, int limit){
        this.limit = limit;
        this.sharedQueue = sharedQueue;
    }

    //run override to allow multithreading
    @Override
    public void run(){
        try {
            for (int i = 1; i <= limit; i++) {
                System.out.println("Produced: " + i);
                //adding task to shared resource
                sharedQueue.put(i);
                //waiting before adding the next task
                Thread.sleep(300);
            }
            //adding "poison pill" to indicate end
            sharedQueue.put(-1);
            System.out.println("All tasks produced");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

//This class consumes (processes) the tasks produced by producer
class Consumer implements Runnable{
    private final BlockingQueue<Integer> sharedQueue; //shared resource

    //constructor to set field
    public Consumer(BlockingQueue<Integer> sharedQueue){
        this.sharedQueue = sharedQueue;
    }

    //override of run for multithreading
    @Override
    public void run() {
        try{
            while(true){
                //getting task even if needing to wait for it to be added
                Integer task = sharedQueue.take();
                //exit condition
                if(task == -1) break;
                System.out.println("Consumed: "+task);
                //"processing" time
                Thread.sleep(600);
            }
            System.out.println("All tasks consumed");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
//Main class
public class ConcurrentProducerConsumer {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        try{
            //user input for number of tasks
            System.out.println("Enter the number of tasks to be generated: ");
            int taskCount = scanner.nextInt();

            //creation of shared queue
            BlockingQueue<Integer> sharedQueue = new LinkedBlockingDeque<>(5);

            //passing runnable objects to threads
            Thread t1 = new Thread(new Producer(sharedQueue,taskCount));
            Thread t2 = new Thread(new Consumer(sharedQueue));

            //starting execution
            t1.start();
            t2.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

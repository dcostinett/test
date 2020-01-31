package net.cozz;

import java.util.logging.Logger;

/**
 * @author danco on 9/29/15.
 */
public class ThreadTest {
    public static void main(String args[]) {

        PrintDemo pd = new PrintDemo();

        ThreadDemo T1 = new ThreadDemo("Thread - 1 ", pd);
        ThreadDemo T2 = new ThreadDemo("Thread - 2 ", pd);

        T1.start();
        T2.start();

        // wait for threads to end
        try {
            T1.join();
            T2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }


    static class PrintDemo {
        public void printCount() {
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println("Counter   ---   " + i);
                }
            } catch (Exception e) {
                System.out.println("Thread  interrupted.");
            }
        }

    }

    static class ThreadDemo extends Thread {
        private Thread t;
        private String threadName;
        private final PrintDemo pd;


        ThreadDemo(String name, PrintDemo pd) {
            threadName = name;
            this.pd = pd;
        }


        public void run() {
            synchronized (pd) {
                pd.printCount();
            }
            System.out.println("Thread " + threadName + " exiting.");
        }


        public void start() {
            System.out.println("Starting " + threadName);
            super.start();
        }

    }


    // example of thread deadlock -- order of lock acquisition invalid
    // reverse lock acquisition in ThreadDemo2 in order to avoid deadlock
    static class TestThread {
        private static final Logger LOGGER = Logger.getLogger(TestThread.class.getSimpleName());

        public static final Object Lock1 = new Object();
        public static final Object Lock2 = new Object();


        public static void main(String args[]) {

            ThreadDemo1 T1 = new ThreadDemo1();
            ThreadDemo2 T2 = new ThreadDemo2();
            T1.start();
            T2.start();
        }

        private static class ThreadDemo1 extends Thread {
            @Override
            public void run() {
                synchronized (Lock1) {
                    TestThread.LOGGER.info("Thread 1: Holding lock 1...");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    TestThread.LOGGER.info("Thread 1: Waiting for lock 2...");
                    synchronized (Lock2) {
                        TestThread.LOGGER.info("Thread 1: Holding lock 1 & 2...");
                    }
                }
            }
        }

        private static class ThreadDemo2 extends Thread {
            @Override
            public void run() {
                synchronized (Lock2) {
                    LOGGER.info("Thread 2: Holding lock 2...");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    LOGGER.info("Thread 2: Waiting for lock 1...");
                    synchronized (Lock1) {
                        LOGGER.info("Thread 2: Holding lock 1 & 2...");
                    }
                }
            }
        }
    }

    static class TestThread2 {
        private static final Logger LOGGER = Logger.getLogger(TestThread.class.getSimpleName());

        public static final Object Lock1 = new Object();
        public static final Object Lock2 = new Object();


        public static void main(String args[]) {

            ThreadDemo1 T1 = new ThreadDemo1();
            ThreadDemo2 T2 = new ThreadDemo2();
            T1.start();
            T2.start();
        }

        private static class ThreadDemo1 extends Thread {
            @Override
            public void run() {
                synchronized (Lock1) {
                    LOGGER.info("Thread 1: Holding lock 1...");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    LOGGER.info("Thread 1: Waiting for lock 2...");
                    synchronized (Lock2) {
                        LOGGER.info("Thread 1: Holding lock 1 & 2...");
                    }
                }
            }
        }

        private static class ThreadDemo2 extends Thread {
            @Override
            public void run() {
                synchronized (Lock1) {
                    LOGGER.info("Thread 2: Holding lock 2...");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    LOGGER.info("Thread 2: Waiting for lock 1...");
                    synchronized (Lock2) {
                        LOGGER.info("Thread 2: Holding lock 1 & 2...");
                    }
                }
            }
        }
    }

    /**
     * This is a demonstration of how NOT to write multi-threaded programs.
     * It is a program that purposely causes deadlock between two threads that
     * are both trying to acquire locks for the same two resources.
     * To avoid this sort of deadlock when locking multiple resources, all threads
     * should always acquire their locks in the same order.
     **/
    private static class Deadlock {
        public static void main(String[] args){
            //These are the two resource objects
            //we'll try to get locks for
            final Object resource1 = "resource1";
            final Object resource2 = "resource2";
            //Here's the first thread.
            //It tries to lock resource1 then resource2
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    //Lock resource 1
                    synchronized(resource1){
                        System.out.println("Thread 1: locked resource 1");
                        //Pause for a bit, simulating some file I/O or
                        //something. Basically, we just want to give the
                        //other thread a chance to run. Threads and deadlock
                        //are asynchronous things, but we're trying to force
                        //deadlock to happen here...
                        try{
                            Thread.sleep(50);
                        } catch (InterruptedException e) {}

                        //Now wait 'till we can get a lock on resource 2
                        synchronized(resource2){
                            System.out.println("Thread 1: locked resource 2");
                        }
                    }
                }
            };

            //Here's the second thread.
            //It tries to lock resource2 then resource1
            Thread t2 = new Thread(){
                @Override
                public void run(){
                    //This thread locks resource 2 right away
                    synchronized(resource2){
                        System.out.println("Thread 2: locked resource 2");
                        //Then it pauses, for the same reason as the first
                        //thread does
                        try{
                            Thread.sleep(50);
                        } catch (InterruptedException e){}

                        //Then it tries to lock resource1.
                        //But wait!  Thread 1 locked resource1, and
                        //won't release it till it gets a lock on resource2.
                        //This thread holds the lock on resource2, and won't
                        //release it till it gets resource1.
                        //We're at an impasse. Neither thread can run,
                        //and the program freezes up.
                        synchronized(resource1){
                            System.out.println("Thread 2: locked resource 1");
                        }
                    }
                }
            };

            //Start the two threads.
            //If all goes as planned, deadlock will occur,
            //and the program will never exit.
            t1.start();
            t2.start();
        }
    }
}



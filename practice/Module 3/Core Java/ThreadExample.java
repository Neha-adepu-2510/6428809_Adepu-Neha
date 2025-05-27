// Thread class extending Thread
class MyThread extends Thread {
    private String message;

    public MyThread(String message) {
        this.message = message;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(message + " - Count: " + i);
            try {
                Thread.sleep(500); // Pause for half a second
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        // Create thread objects
        MyThread thread1 = new MyThread("Thread 1 is running");
        MyThread thread2 = new MyThread("Thread 2 is running");

        // Start threads
        thread1.start();
        thread2.start();
    }
}

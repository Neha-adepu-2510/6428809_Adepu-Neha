public class VirtualThreadsExample {

    private static final int THREAD_COUNT = 100_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Running with traditional threads...");
        runTraditionalThreads();

        System.out.println("\nRunning with virtual threads...");
        runVirtualThreads();
    }

    private static void runTraditionalThreads() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        long start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                // Minimal task: print a message (commented to avoid flooding console)
                // System.out.println("Hello from traditional thread " + Thread.currentThread().getId());
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long end = System.currentTimeMillis();
        System.out.println("Traditional threads time: " + (end - start) + " ms");
    }

    private static void runVirtualThreads() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        long start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = Thread.startVirtualThread(() -> {
                // Minimal task: print a message (commented out for speed)
                // System.out.println("Hello from virtual thread " + Thread.currentThread().getId());
            });
        }

        for (Thread t : threads) {
            t.join();
        }

        long end = System.currentTimeMillis();
        System.out.println("Virtual threads time: " + (end - start) + " ms");
    }
}

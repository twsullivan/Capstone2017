package DNSQueryTool;

import java.util.concurrent.ThreadPoolExecutor;

public class MonitorThread implements Runnable {

    private final ThreadPoolExecutor executor;
    private final int seconds;
    private final int totalCount;
    private boolean run = true;

    public MonitorThread(ThreadPoolExecutor executor, int delay, int totalCount) {
        this.executor = executor;
        this.seconds = delay;
        this.totalCount = totalCount;
    }

    public void shutdown() {
        this.run = false;
    }

    @Override
    public void run() {
        System.out.println();
        while (run) {
            System.out.format("\r[Status] Active: %d, Completed: %d, Total: %d",
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.totalCount);
                    //this.executor.getTaskCount());
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}

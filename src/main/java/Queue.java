import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable{

    private BlockingQueue<Client> q;
    private boolean simRunning;
    private AtomicInteger waitingPeriod;
    private int simTime;

    Queue() {
        q = new  LinkedBlockingQueue<Client>();
        simRunning = true;
        waitingPeriod = new AtomicInteger(0);
    }

    public void run() {
        while(simRunning) {
            if (waitingPeriod.intValue() != 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Client c = q.peek();
                c.decreaseServiceTime();
                if (c.getServiceTime() == 0) {
                    q.remove(c);
                    waitingPeriod.getAndAdd(-1);
                }
                try {
                    c.setWaitingEnd(simTime);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addClient(Client client) {
        q.add(client);
        waitingPeriod.getAndAdd(1);
    }

    public int getTotalQueueTime() {
       int totalQueueTime = 0;
       for(Client client: q) {
           totalQueueTime += client.getServiceTime();
       }
       return totalQueueTime;
    }

    public boolean isRunning() {
        return simRunning;
    }

    public void stop() {
        simRunning = false;
    }

    public String toStringQueue() {
        String clients = "";
        for (Client c: q) {
            clients = clients + "(" + c.getId() + "," + c.getArrivalTime() + "," + c.getServiceTime() + ") ";
        }
        return clients;
    }

    public void setSimTime(int simTime) {
        this.simTime = simTime;
    }
    public int getWaitingPeriod() {
        return this.waitingPeriod.intValue();
    }
}

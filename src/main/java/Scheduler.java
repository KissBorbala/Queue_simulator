import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Queue> queues;
    private int maxNoQueues;
    private int maxClientsPerQueue;


    public Scheduler(int maxNoQueues, int maxClientsPerQueue) {
        queues = new ArrayList<Queue>(maxNoQueues);
        this.maxNoQueues = maxNoQueues;
        this.maxClientsPerQueue = maxClientsPerQueue;
        for (int i = 0; i < maxNoQueues; i++) {
           Queue q = new Queue();
           queues.add(q);
           Thread thread = new Thread(q);
           thread.start();
        }
    }

    public void dispatchClient(Client client) {
        StrategyTime.addClient(queues, client);
    }

    public List<Queue> getQueues() {
        return this.queues;
    }

    public void stop() {
        for (Queue queue: queues) {
            queue.stop();
        }
    }

}

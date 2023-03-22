import java.util.List;

public class StrategyTime {

    public static void addClient(List<Queue> queues, Client client) {

        int smallestTotalQueueTime = queues.get(0).getTotalQueueTime();
        Queue smallestQueue = queues.get(0);
        for (Queue q: queues) {
            if (smallestTotalQueueTime > q.getTotalQueueTime()) {
                smallestTotalQueueTime = q.getTotalQueueTime();
                smallestQueue = q;
            }
        }
        smallestQueue.addClient(client);
    }
}

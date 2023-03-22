import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation implements Runnable{

    private boolean simRunning = true;
    private AtomicInteger currentTime = new AtomicInteger();
    private List<Client> waitingClients;
    private List<Client> allClients;
    private Queue q;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int maxArrivingTime;
    private int minArrivingTime;
    private int nrOfQueues;
    private int nrOfClients;
    private int inQueue;
    private double averageWaitingTime = 0;
    private double averageServiceTime = 0;
    private int peakHour = 0;
    private int peakHourClientNr = 0;

    public Simulation(int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivingTime, int minArrivingTime, int nrOfQueues, int nrOfClients) {

        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivingTime = maxArrivingTime;
        this.minArrivingTime = minArrivingTime;
        this.nrOfQueues = nrOfQueues;
        this.nrOfClients = nrOfClients;
        this.scheduler = new Scheduler(nrOfQueues, nrOfClients);
        this.frame = new SimulationFrame(nrOfQueues, nrOfClients);
        generateNRandomClients();
    }

    private void generateNRandomClients() {
        int arrivingTime, processingTime;
        Client aux;

        waitingClients = new ArrayList<Client>(nrOfClients);
        allClients = new ArrayList<Client>(nrOfClients);
        for (int i = 0; i < nrOfClients; i++) {
            arrivingTime = (int) (Math.random() * (maxArrivingTime - minArrivingTime + 1) + minArrivingTime);
            processingTime = (int) (Math.random() * (maxProcessingTime - minProcessingTime + 1) + minProcessingTime);
            Client client = new Client(arrivingTime , processingTime);
            waitingClients.add(client);
        }
        for (int i = 0; i < nrOfClients; i++) {
            for (int j = i + 1; j < nrOfClients; j++) {
                if (waitingClients.get(i).getArrivalTime() > waitingClients.get(j).getArrivalTime()) {
                    aux = waitingClients.get(i);
                    waitingClients.set(i, waitingClients.get(j));
                    waitingClients.set(j, aux);
                }
            }
        }
        int id = 0;
        for (Client client: waitingClients) {
            client.setId(id);
            allClients.add(client);
            id++;
        }

    }

    public String toStringClients() {
        String clients = "Waiting clients: ";
        for(Client c: waitingClients) {
            clients = clients + "(" + c.getId() + "," + c.getArrivalTime() + "," + c.getServiceTime() + ") ";
        }
        return clients;
    }

    public void run() {
        currentTime.set(0);
        if (currentTime.intValue() < timeLimit) {
            simRunning = true;
        }
        else {
            simRunning = false;
        }
        while(simRunning && currentTime.intValue() <= timeLimit) {
            Iterator<Client> itr = waitingClients.iterator();
            while (itr.hasNext()) {
                Client client = itr.next();
                if (client.getArrivalTime() == currentTime.intValue()) {
                    scheduler.dispatchClient(client);
                    itr.remove();
                }
            }
            inQueue = 0;

            for (Queue queue: scheduler.getQueues()) {
                queue.setSimTime(currentTime.intValue());
                inQueue += queue.getWaitingPeriod();
            }
            if (inQueue == 0 && waitingClients.isEmpty()) {
                simRunning = false;
            }
            frame.setWaitingClients(toStringClients());
            frame.setTime(currentTime.intValue());
            for (int i = 0; i < nrOfQueues; i++) {
                if (scheduler.getQueues().get(i).getWaitingPeriod() != 0) {
                    frame.setQueues(scheduler.getQueues().get(i).toStringQueue(), i);
                }
                else {
                    frame.setQueues("closed", i);
                }
            }
            if (inQueue > peakHourClientNr) {
                peakHourClientNr = inQueue;
                peakHour = currentTime.intValue();
            }
            try {
                FileWriter file = new FileWriter("log.txt", true);
                file.write("Time " + currentTime + "\n");
                file.write(toStringClients() + "\n");
                for (int i = 0; i < nrOfQueues; i++) {
                    if (scheduler.getQueues().get(i).getWaitingPeriod() != 0) {
                        file.write("Queue " + (i + 1) + ": " + scheduler.getQueues().get(i).toStringQueue() + "\n");
                    } else {
                        file.write("Queue " + (i + 1) + ": closed\n");
                    }
                }
                file.write("\n");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentTime.getAndAdd(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        scheduler.stop();
        for (Client client: allClients) {
            averageWaitingTime = averageWaitingTime + client.getWaiting();
            averageServiceTime += client.getInitialService();
        }
        averageWaitingTime = averageWaitingTime/ nrOfClients;
        averageServiceTime = averageServiceTime / nrOfClients;
        String averageWaiting = Double.toString(averageWaitingTime);
        String averageService = Double.toString(averageServiceTime);
        frame.setWaitingClients("Average waiting time in line: " + averageWaiting + "  Average service time: " + averageService + " Peak hour: " + String.valueOf(peakHour));
        try {
            FileWriter file = new FileWriter("log.txt", true);
            file.write("Average waiting time in line: " + averageWaitingTime + "\n");
            file.write("Average service time: " + averageServiceTime + "\n");
            file.write("Peak hour: " + peakHour+ "\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentTime() {
        return this.currentTime.intValue();
    }
}

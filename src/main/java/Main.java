import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        StartFrame startFrame = new StartFrame("start");

        startFrame.addListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                int timeLimit = startFrame.getTimeLimit();
                int maxProcessingTime = startFrame.getMaxProcessingTime();
                int minProcessingTime = startFrame.getMinProcessingTime();
                int maxArrivingTime = startFrame.getMaxArrivingTime();
                int minArrivingTime = startFrame.getMinArrivingTime();
                int nrOfClients = startFrame.getNrOfClients();
                int nrOfQueues = startFrame.getNrOfQueues();
                startFrame.setVisible(false);
                Simulation s = new Simulation(timeLimit, maxProcessingTime, minProcessingTime, maxArrivingTime, minArrivingTime, nrOfQueues, nrOfClients);
                Thread t = new Thread(s);
                t.start();
            }
        });
    }

}

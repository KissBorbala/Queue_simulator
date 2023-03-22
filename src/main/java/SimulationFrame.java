import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {

    private int nrOfQueues;
    private int nrOfClients;
    private JLabel waitingClients;
    private JLabel simTime;
    private JLabel[] queues;

    private JPanel pane = new JPanel();

    public SimulationFrame(int nrOfQueues, int nrOfClients) {
        super("Simulation");
        this.nrOfQueues = nrOfQueues;
        this.nrOfClients = nrOfClients;

        simTime = new JLabel("Time: 0");
        waitingClients = new JLabel("Waiting clients: ");
        queues = new JLabel[nrOfQueues];
        for (int i = 0; i < nrOfQueues; i++) {
            queues[i] = new JLabel();

            queues[i].setText("Queue nr." + (i + 1));
        }

        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(simTime);
        pane.add(waitingClients);
        for (int i = 0; i < nrOfQueues; i++) {
            pane.add(queues[i]);
        }
        pane.setAlignmentY(Component.LEFT_ALIGNMENT);
        this.add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        setVisible(true);
    }

    public void setWaitingClients(String waiting) {
        waitingClients.setText(waiting);
    }
    public void setTime(int time) {
       this.simTime.setText("Time: " + String.valueOf(time));
    }
    public void setQueues(String clients, int i) {
        queues[i].setText("Queue nr." + (i+1) + ": " + clients);
    }
}

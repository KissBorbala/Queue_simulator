import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame{

    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    private JLabel nrOfQueuesLabel = new JLabel("Number of queues: ");
    private JLabel nrOfClientsLabel = new JLabel("Number of clients: ");
    private JLabel timeLimitLabel = new JLabel("Maximum time limit: ");
    private JLabel minProcessingTimeLabel = new JLabel("Minimum processing time: ");
    private JLabel maxProcessingTimeLabel = new JLabel("Maximum processing time: ");
    private JLabel minArrivingTimeLabel = new JLabel("Minimum arriving time: ");
    private JLabel maxArrivingTimeLabel = new JLabel("Maximum arriving time: ");
    private JTextField nrOfQueues = new JTextField(4);
    private JTextField nrOfClients = new JTextField(4);
    private JTextField timeLimit = new JTextField(4);
    private JTextField minProcessingTime = new JTextField(4);
    private JTextField maxProcessingTime = new JTextField(4);
    private JTextField minArrivingTime = new JTextField(4);
    private JTextField maxArrivingTime = new JTextField(4);
    private JButton start = new JButton("start");


    public StartFrame(String name) {
        super(name);
        c.gridx = 1;
        c.gridy = 0;
        pane.add(nrOfClientsLabel, c);
        c.gridx = 3;
        c.gridy = 0;
        pane.add(nrOfClients, c);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(nrOfQueuesLabel, c);
        c.gridx = 3;
        c.gridy = 1;
        pane.add(nrOfQueues, c);
        c.gridx = 1;
        c.gridy = 2;
        pane.add(timeLimitLabel, c);
        c.gridx = 3;
        c.gridy = 2;
        pane.add(timeLimit, c);
        c.gridx = 1;
        c.gridy = 3;
        pane.add(minArrivingTimeLabel, c);
        c.gridx = 3;
        c.gridy = 3;
        pane.add(minArrivingTime, c);
        c.gridx = 1;
        c.gridy = 4;
        pane.add(maxArrivingTimeLabel, c);
        c.gridx = 3;
        c.gridy = 4;
        pane.add(maxArrivingTime, c);
        c.gridx = 1;
        c.gridy = 5;
        pane.add(minProcessingTimeLabel, c);
        c.gridx = 3;
        c.gridy = 5;
        pane.add(minProcessingTime, c);
        c.gridx = 1;
        c.gridy = 6;
        pane.add(maxProcessingTimeLabel, c);
        c.gridx = 3;
        c.gridy = 6;
        pane.add(maxProcessingTime, c);
        c.gridx = 1;
        c.gridy = 8;
        pane.add(start, c);

        this.add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        setVisible(true);
    }

    public int getNrOfClients() {
        return Integer.parseInt(nrOfClients.getText());
    }
    public int getNrOfQueues() {
        return Integer.parseInt(nrOfQueues.getText());
    }
    public int getTimeLimit() {
        return Integer.parseInt(timeLimit.getText());
    }
    public int getMinArrivingTime() {
        return Integer.parseInt(minArrivingTime.getText());
    }
    public int getMaxArrivingTime() {
        return Integer.parseInt(maxArrivingTime.getText());
    }
    public int getMinProcessingTime() {
        return Integer.parseInt(minProcessingTime.getText());
    }
    public int getMaxProcessingTime() {
        return Integer.parseInt(maxProcessingTime.getText());
    }
    public void addListener(ActionListener action) {
        start.addActionListener(action);
    }



}


public class Client {

    private int arrivalTime;
    private int serviceTime;
    private int waitingEnd = -1;
    private int initialService;
    private int id;

    Client(int arrivalTime, int serviceTime) {

        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.initialService = serviceTime;
    }

    void decreaseServiceTime() {
        serviceTime = serviceTime - 1;
    }

    int getServiceTime() {
        return this.serviceTime;
    }
    int getArrivalTime() {
        return this.arrivalTime;
    }
    int getId () {
        return this.id;
    }
    int getWaiting() {
        int waiting = waitingEnd - arrivalTime;
        if (waiting == -1 || waiting == 0) {
            return 0;
        }
        else {
            return waiting;
        }
    }
    int getInitialService() {
        return this.initialService;
    }
    void setWaitingEnd(int end) {
        if (waitingEnd == -1) {
            this.waitingEnd = end;
        }
    }
    void setId(int id) {
        this.id = id;
    }


}

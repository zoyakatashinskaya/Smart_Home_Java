package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.Dishwasher;

public class StartCleaningCommand implements Command {

    private Dishwasher dishwasher;

    public StartCleaningCommand(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {
        (new Thread(() -> this.dishwasher.startCleaning())).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.dishwasher + ": Start cleaning");
    }

    @Override
    public boolean isAvailable() {
        return this.dishwasher.isOn()
                && this.dishwasher.isTimerSet()
                && this.dishwasher.timeLeft() > 0
                && !this.dishwasher.isRunning();
    }

    @Override
    public String toString() {
        return "Start cleaning";
    }

}

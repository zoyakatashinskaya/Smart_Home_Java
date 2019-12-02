package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.MicrowaveOven;

public class StartMicrowaveOvenCommand implements Command {

    private MicrowaveOven microwaveOven;

    public StartMicrowaveOvenCommand(MicrowaveOven microwaveOven) {
        this.microwaveOven = microwaveOven;
    }

    @Override
    public void execute() {
        (new Thread(() -> this.microwaveOven.startBaking())).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.microwaveOven + ": Started baking");
    }

    @Override
    public boolean isAvailable() {
        return this.microwaveOven.isOn()
                && this.microwaveOven.isTimerSet()
                && this.microwaveOven.timeLeft() > 0
                && this.microwaveOven.getTemperature() > 0
                && !this.microwaveOven.isRunning();
    }

    @Override
    public String toString() {
        return "Start microwave oven";
    }
}

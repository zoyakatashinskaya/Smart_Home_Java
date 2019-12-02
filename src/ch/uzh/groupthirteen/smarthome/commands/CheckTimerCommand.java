package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.Device;

public class CheckTimerCommand implements Command {

    Device device;

    public CheckTimerCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        System.out.println(this.device + ": " + this + " Time left: " + this.device.timeLeft() + ".");
    }

    @Override
    public boolean isAvailable() {
        return this.device.isOn() && this.device.isTimerSet();
    }

    @Override
    public String toString() {
        return "Check Timer";
    }
}

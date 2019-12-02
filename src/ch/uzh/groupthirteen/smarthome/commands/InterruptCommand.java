package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.Device;

public class InterruptCommand implements Command {

    Device device;

    public InterruptCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        this.device.interrupt();
        System.out.println(this.device + ": " + this + ".");
    }

    @Override
    public boolean isAvailable() {
        return this.device.isOn() && this.device.isTimerSet() && this.device.isRunning();
    }

    @Override
    public String toString() {
        return "Interrupt";
    }
}

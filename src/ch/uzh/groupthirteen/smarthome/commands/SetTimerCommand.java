package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.Helpers;
import ch.uzh.groupthirteen.smarthome.devices.Device;

public class SetTimerCommand implements Command {

    private Device device;

    public SetTimerCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        System.out.println("Please enter the desired Time.");
        int input = Helpers.getPositiveNumber();
        this.device.setTime(input);
        System.out.println(this.device + ": " + "Time set to " + input);
    }

    @Override
    public boolean isAvailable() {
        return this.device.isOn();
    }

    @Override
    public String toString() {
        return "Set timer";
    }
}

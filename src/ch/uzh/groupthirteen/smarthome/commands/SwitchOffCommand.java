package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.Device;

public class SwitchOffCommand implements Command {

    Device device;

    public SwitchOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        this.device.turnOff();
        System.out.println(this.device + ": " + this + ".");
    }

    @Override
    public boolean isAvailable() {
        return this.device.isOn();
    }

    @Override
    public String toString() {
        return "Switch Off";
    }
}

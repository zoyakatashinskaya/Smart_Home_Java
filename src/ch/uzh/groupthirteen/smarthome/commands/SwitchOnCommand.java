package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.Device;

public class SwitchOnCommand implements Command {

    Device device;

    public SwitchOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        this.device.turnOn();
        System.out.println(this.device + ": " + this + ".");
    }

    @Override
    public boolean isAvailable() {
        return !this.device.isOn();
    }

    @Override
    public String toString() {
        return "Switch On";
    }
}

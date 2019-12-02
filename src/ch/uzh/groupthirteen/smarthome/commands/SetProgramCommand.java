package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.Helpers;
import ch.uzh.groupthirteen.smarthome.devices.Device;
import ch.uzh.groupthirteen.smarthome.devices.Programmable;

public class SetProgramCommand implements Command {

    private Programmable programmable;

    public SetProgramCommand(Programmable programmable) {
        this.programmable = programmable;
    }

    @Override
    public void execute() {
        System.out.print("Available programs are");
        String[] programs = this.programmable.listPrograms();
        int i = 1;
        for (String program: programs) {
            System.out.print(" " + i++ + ": " + program);
        }
        System.out.println(".");

        int selected;
        while (true) {
            selected = Helpers.getPhoneInput();
            if (0 < selected && programs.length > selected-1) {
                break;
            } else {
                System.out.println("There is no program on the slot " + selected);
            }
        }
        this.programmable.setProgram(selected);
        System.out.println(this.programmable + ": Selected program " + programs[selected-1]);
    }

    @Override
    public boolean isAvailable() {
        Device device = (Device) this.programmable;
        return device.isOn()
                && (!device.isTimerSet() || !device.isRunning());
    }

    @Override
    public String toString() {
        return "Set program";
    }
}

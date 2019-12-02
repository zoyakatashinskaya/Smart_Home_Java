package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.WashingMachine;

public class TurnOffCommand implements Command {

    private WashingMachine washingMachine;

    public TurnOffCommand(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void execute() {
        this.washingMachine.softReset();
        System.out.println(this.washingMachine + ": Turned off");
    }

    @Override
    public boolean isAvailable() {
        return this.washingMachine.isOn() && this.washingMachine.isTimerSet() && !this.washingMachine.isRunning();
    }

    @Override
    public String toString() {
        return "Turn off";
    }
}

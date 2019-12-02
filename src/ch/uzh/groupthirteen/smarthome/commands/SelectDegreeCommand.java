package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.Helpers;
import ch.uzh.groupthirteen.smarthome.devices.WashingMachine;

public class SelectDegreeCommand implements Command {

    private WashingMachine washingMachine;

    public SelectDegreeCommand(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void execute() {
        System.out.print("Available degrees are");
        int[] degrees = this.washingMachine.listDegree();
        int i = 1;
        for (int degree: degrees) {
            System.out.print(" " + i++ + ": " + degree);
        }
        System.out.println(".");

        int selected;
        while (true) {
            selected = Helpers.getPhoneInput();
            if (0 < selected && degrees.length > selected) {
                break;
            } else {
                System.out.println("There is no degree on the slot " + selected);
            }
        }
        this.washingMachine.setDegree(selected);
        System.out.println(this.washingMachine + ": Selected degree " + degrees[selected-1]);
    }

    @Override
    public boolean isAvailable() {
        return this.washingMachine.isOn()
                && (!this.washingMachine.isTimerSet() || !this.washingMachine.isRunning());
    }

    @Override
    public String toString() {
        return "Select degree";
    }
}

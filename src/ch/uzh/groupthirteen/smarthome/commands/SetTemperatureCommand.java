package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.Helpers;
import ch.uzh.groupthirteen.smarthome.devices.Cooking;
import ch.uzh.groupthirteen.smarthome.devices.Device;

public class SetTemperatureCommand implements Command {

    private Cooking cooking;

    public SetTemperatureCommand(Cooking cooking) {
        this.cooking = cooking;
    }

    @Override
    public void execute() {
        System.out.println("Please enter a desired temperature.");
        int input;
        while (true) {
            input = Helpers.getPositiveNumber();
            if (250 >= input) {
                break;
            }
            System.out.println("Such a temperature is not possible.");
        }
        this.cooking.setTemperature(input);
        System.out.println(this.cooking + ": Temperature set to " + input);
    }

    @Override
    public boolean isAvailable() {
        return this.cooking.isOn();
    }

    @Override
    public String toString() {
        return "Set temperature";
    }
}

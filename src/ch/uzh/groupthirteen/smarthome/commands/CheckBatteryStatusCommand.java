package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.CleanerRobot;

public class CheckBatteryStatusCommand implements Command {

    private CleanerRobot cleanerRobot;

    public CheckBatteryStatusCommand(CleanerRobot cleanerRobot) {
        this.cleanerRobot = cleanerRobot;
    }

    @Override
    public void execute() {
        System.out.println(this.cleanerRobot + ": Battery percentage is: "
                + String.format("%.0f", this.cleanerRobot.getChargeLeftPercentage())
                + "%.");
    }

    @Override
    public boolean isAvailable() {
        return this.cleanerRobot.isOn() && !this.cleanerRobot.isCharging();
    }

    @Override
    public String toString() {
        return "Check battery status (percentage)";
    }
}

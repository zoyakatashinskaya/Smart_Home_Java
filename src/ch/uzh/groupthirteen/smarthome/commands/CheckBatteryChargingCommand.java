package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.CleanerRobot;

public class CheckBatteryChargingCommand implements Command {

    private CleanerRobot cleanerRobot;

    public CheckBatteryChargingCommand(CleanerRobot cleanerRobot) {
        this.cleanerRobot = cleanerRobot;
    }

    @Override
    public void execute() {
        System.out.print(this.cleanerRobot + ": The battery is ");
        if (this.cleanerRobot.isCharging()) {
            System.out.println("charging.");
        } else {
            System.out.println("NOT charging.");
        }
    }

    @Override
    public boolean isAvailable() {
        return this.cleanerRobot.isOn();
    }

    @Override
    public String toString() {
        return "Check battery charging";
    }
}

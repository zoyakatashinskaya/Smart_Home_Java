package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.CleanerRobot;

public class CheckCleaningPercentageCommand implements Command {

    private CleanerRobot cleanerRobot;

    public CheckCleaningPercentageCommand(CleanerRobot cleanerRobot) {
        this.cleanerRobot = cleanerRobot;
    }

    @Override
    public void execute() {
        System.out.println(this.cleanerRobot + ": Cleaning percentage is "
                        + String.format("%.0f", this.cleanerRobot.getCleaningPercentage())
                        + "%.");
    }

    @Override
    public boolean isAvailable() {
        return this.cleanerRobot.isOn();
    }

    @Override
    public String toString() {
        return "Check cleaning percentage";
    }
}

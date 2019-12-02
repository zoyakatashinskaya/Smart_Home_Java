package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.CleanerRobot;

public class ResumeCleaningCommand implements Command {

    private CleanerRobot cleanerRobot;

    public ResumeCleaningCommand(CleanerRobot cleanerRobot) {
        this.cleanerRobot = cleanerRobot;
    }

    @Override
    public void execute() {
        (new Thread(() -> this.cleanerRobot.completeOutstandingCleaning())).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.cleanerRobot + ": Resumed cleaning");
    }

    @Override
    public boolean isAvailable() {
        return this.cleanerRobot.isOn()
                && this.cleanerRobot.isTimerSet()
                && this.cleanerRobot.isInBase()
                && !this.cleanerRobot.isCharging();
    }

    @Override
    public String toString() {
        return "Resume cleaning";
    }
}

package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.CleanerRobot;

public class StartVacuumCleanerCommand implements Command {

    private CleanerRobot cleanerRobot;

    public StartVacuumCleanerCommand(CleanerRobot cleanerRobot) {
        this.cleanerRobot = cleanerRobot;
    }

    @Override
    public void execute() {
        (new Thread(() -> this.cleanerRobot.startVacuumCleaner())).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.cleanerRobot + ": Started vacuum cleaner");
    }

    @Override
    public boolean isAvailable() {
        return this.cleanerRobot.isOn()
                && this.cleanerRobot.isTimerSet()
                && this.cleanerRobot.timeLeft() > 0
                && this.cleanerRobot.isInBase()
                && this.cleanerRobot.isFirstRun()
                && !this.cleanerRobot.isCharging();
    }

    @Override
    public String toString() {
        return "Start vacuum cleaner";
    }
}

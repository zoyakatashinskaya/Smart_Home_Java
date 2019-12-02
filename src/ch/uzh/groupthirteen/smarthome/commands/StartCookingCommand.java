package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.Oven;

public class StartCookingCommand implements Command {

    private Oven oven;

    public StartCookingCommand(Oven oven) {
        this.oven = oven;
    }

    @Override
    public void execute() {
        (new Thread(() -> this.oven.startCooking())).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.oven + ": Start cooking");
    }

    @Override
    public boolean isAvailable() {
        return this.oven.isOn()
                && this.oven.isTimerSet()
                && this.oven.timeLeft() > 0
                && !this.oven.getProgram().equals("")
                && this.oven.getTemperature() > 0
                && !this.oven.isRunning();
    }

    @Override
    public String toString() {
        return "Start cooking";
    }
}

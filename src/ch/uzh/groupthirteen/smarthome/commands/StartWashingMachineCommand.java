package ch.uzh.groupthirteen.smarthome.commands;

import ch.uzh.groupthirteen.smarthome.devices.WashingMachine;

public class StartWashingMachineCommand implements Command {

    private WashingMachine washingMachine;

    public StartWashingMachineCommand(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void execute() {
        (new Thread(() -> this.washingMachine.startWorking())).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.washingMachine + ": Start washing");
    }

    @Override
    public boolean isAvailable() {
        return this.washingMachine.isOn()
                && this.washingMachine.isTimerSet()
                && this.washingMachine.timeLeft() > 0
                && !this.washingMachine.isRunning();
    }

    @Override
    public String toString() {
        return "Start washing";
    }
}

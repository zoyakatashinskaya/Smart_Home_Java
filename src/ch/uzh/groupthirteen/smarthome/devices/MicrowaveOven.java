package ch.uzh.groupthirteen.smarthome.devices;

import ch.uzh.groupthirteen.smarthome.FastTimer;
import ch.uzh.groupthirteen.smarthome.commands.*;

import java.util.Arrays;
import java.util.List;

public class MicrowaveOven extends Cooking {
    //has to do:
    //(○ Switch on) x
    //○ Set timer (in seconds) x
    //○ Set temperature x
    //○ Start Baking (only if the other parameters are set)
    //○ Check timer (if the microwave is in operation, check the active timer, otherwise the last one set)
    //○ Interrupt program (only possible if microwave is in operation)
    //(○ Switch off)

    private boolean isBaking;

    /**
     * starts baking if temperature, timer is set and the device is on
     */
    public void startBaking() {
        try {
            this.timer.resume();
        } catch (FastTimer.TimerNotUsedException e) {
            this.timer.start();
        }
        this.isBaking = true;
        while (this.timer.isRunning()) {}
        this.isBaking = false;
    }

    /**
     * Resets the MicrowaveOven
     */
    protected void reset() {
        super.reset();

        this.isBaking = false;
    }

    @Override
    public List<Command> listCommands() {
        return Arrays.asList(
                new SwitchOnCommand(this),
                new SetTimerCommand(this),
                new CheckTimerCommand(this),
                new SetTemperatureCommand(this),
                new StartMicrowaveOvenCommand(this),
                new InterruptCommand(this),
                new SwitchOffCommand(this)
        );
    }

    public boolean isBaking() {
        return isBaking;
    }

    public void setBaking(boolean baking) {
        isBaking = baking;
    }

    @Override
    public String toString() {
        return "Microwave Oven";
    }
}

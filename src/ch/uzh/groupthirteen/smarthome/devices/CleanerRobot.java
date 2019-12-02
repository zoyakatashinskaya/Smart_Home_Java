package ch.uzh.groupthirteen.smarthome.devices;

import ch.uzh.groupthirteen.smarthome.FastTimer;
import ch.uzh.groupthirteen.smarthome.commands.*;

import java.util.Arrays;
import java.util.List;

/**
 * The cleaner robot
 *
 * @author Dominik
 */
public class CleanerRobot extends Device {

    /*
     *
     * Hint
     * We don't have to check if the device is configured correctly here,
     * since this will be done via the Commands!
     *
     */

    private boolean inBase;
    private boolean isCharging;
    private boolean firstRun;

    private FastTimer rechargeTimer;
    private FastTimer batteryTimer;

    private int roomSizeInTime;
    private int rechargeTime;
    private int batteryUsageInTime;


    /**
     * Resets CleanerRobot
     */
    protected void reset() {
        super.reset();

        // Starts in base
        this.inBase = true;
        // Starts fully charged
        this.isCharging = false;
        // For the Start/Resume-Switch
        this.firstRun = true;

        // Resets room size
        this.roomSizeInTime = 0;

        // Hardcoded recharge time
        this.rechargeTime = 20;
        this.rechargeTimer = new FastTimer(this.rechargeTime);

        // Hardcoded battery usage
        this.batteryUsageInTime = 40;
        this.batteryTimer = new FastTimer(this.batteryUsageInTime);
    }

    // TODO: Check if suitable for super-class
    @Override
    public void setTime(int time) {
        super.setTime(time);
        // Override because uf this
        this.roomSizeInTime = time;
    }

    /**
     * Starts the vacuum cleaner
     */
    public void startVacuumCleaner() {
        // First time, start the timer
        this.timer.start();
        // Not first run anymore
        this.firstRun = false;

        this.cleanSetup();
    }

    /**
     * Resume cleaning
     */
    public void completeOutstandingCleaning() {
        // Resumes cleaning
        this.timer.resume();

        this.cleanSetup();
    }

    /**
     * Internal setup for cleaning
     */
    private void cleanSetup() {
        // Leaves Base
        this.inBase = false;

        // Battery starts to drain
        this.batteryTimer.start();

        // Cleans
        this.cleaning();

        this.batteryTimer.reset();

        // Comes back finished and charges
        this.inBase = true;
        this.charge();
    }

    /**
     * The charging routine
     */
    private void charge() {
        this.rechargeTimer.start();
        this.isCharging = true;
        // This could be a one-liner, but is better understandable right now
        while (true) {
            if (!this.rechargeTimer.isRunning()) {
                break;
            }
        }
        this.rechargeTimer.reset();
        this.isCharging = false;
    }

    /**
     * The cleaning-loop
     */
    private void cleaning() {
        // This could be a one-liner, but is better understandable right now
        while (this.timer.isRunning()) {
            if (!this.batteryTimer.isRunning()) {
                this.timer.stop();
            }
        }
    }

    /**
     * Gives the charge left percentage
     * @return The charge left percentage
     */
    public double getChargeLeftPercentage() {
        return ((double) this.batteryTimer.timeLeft() / (double) this.batteryUsageInTime) * 100;
    }

    /**
     * Gives the cleaning-percentage
     * @return The cleaning-percentage
     */
    public double getCleaningPercentage() {
        if (null == this.timer) {
            return 0;
        }
        return (1 - ((double) this.timer.timeLeft() / (double) this.roomSizeInTime)) * 100;
    }

    /**
     * Checks if CleanerRobot is inBase
     * @return The Base state
     */
    public boolean isInBase() {
        return this.inBase;
    }

    /**
     * Check if CleanerRobot is charging
     * @return The Charging state
     */
    public boolean isCharging() {
        return this.isCharging;
    }

    /**
     * Check if first run
     * @return first run or not
     */
    public boolean isFirstRun() {
        return this.firstRun;
    }

    public List<Command> listCommands() {
        return Arrays.asList(
                new SwitchOnCommand(this),
                new SetTimerCommand(this),
                new StartVacuumCleanerCommand(this),
                new ResumeVacuumCleanerCommand(this),
                new CheckCleaningPercentageCommand(this),
                new CheckBatteryStatusCommand(this),
                new CheckBatteryChargingCommand(this),
                new SwitchOffCommand(this)
        );
    }

    @Override
    public String toString() {
        return "Cleaner Robot";
    }
}

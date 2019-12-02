package ch.uzh.groupthirteen.smarthome.devices;

import ch.uzh.groupthirteen.smarthome.FastTimer;
import ch.uzh.groupthirteen.smarthome.commands.Command;

import java.util.List;

/**
 * Default behavior of all devices
 *
 * @author Layla and Dominik
 */
public abstract class Device {

    protected boolean on;
    protected FastTimer timer;

    /**
     * Creates a new Device
     */
    public Device() {
        // Just calls the corresponding reset-method
        this.reset();
    }

    /**
     * Resets the device
     */
    protected void reset() {
        // We have to be very verbose here, since we can't assume
        // that the object is "new"
        this.on = false;
        this.timer = null;
    }

    /**
     * Check if device is On
     *
     * @return On-State
     */
    public boolean isOn() {
        return this.on;
    }

    /**
     * Returns if timer got set
     */
    public boolean isTimerSet() {
        return null != this.timer;
    }

    /**
     * Turns the device on
     */
    public void turnOn() {
        this.on = true;
    }

    /**
     * Turns the device off
     */
    public void turnOff() {
        this.reset();
    }

    /**
     * Sets the timer
     * @param time Time in minutes
     */
    public void setTime(int time) {
        this.timer = new FastTimer(time);
    }

    /**
     * Time left
     * @return The time left in minutes
     */
    public int timeLeft() {
        return this.timer.timeLeft();
    };

    /**
     * Is the device running?
     * @return The running state
     */
    public boolean isRunning() {
        return this.timer.isRunning();
    }

    /**
     * Interrupts the current progress
     */
    public void interrupt() {
        this.timer.stop();
    }

    public abstract List<Command> listCommands();

    @Override
    public String toString() {
        return "Device";
    }

}

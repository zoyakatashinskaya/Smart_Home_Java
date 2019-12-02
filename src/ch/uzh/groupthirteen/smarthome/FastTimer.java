package ch.uzh.groupthirteen.smarthome;

/**
 * A class to measure time
 *
 * <p>Danger, not for real word application, here one minute corresponds to one second!</p>
 *
 * @author Dominik
 */
public class FastTimer {

    private long time;
    private long resume;

    private long start;
    private long end;

    /**
     * Measures time
     *
     * <p>
     * <b>Not for real-world-use!</b><br>
     * In this object one minute corresponds to one second
     * </p>
     *
     * @param time Time in minutes
     */
    public FastTimer(int time) {
        if (1 > time) {
            throw new IllegalArgumentException("Zero or negative times are not allowed!");
        }
        // Important, here time (in minutes) is handled like seconds
        this.time = time * 1000;
    }

    /**
     * Starts the timer
     */
    public void start() {
        if (0 != this.start) {
            throw new TimerAlreadyUsedException();
        }
        // Gets current time
        this.start = System.currentTimeMillis();
        // Calculates time to run
        this.end = this.start + this.time;
    }

    /**
     * Resumes the timer
     */
    public void resume() {
        if (0 == this.start) {
            throw new TimerNotUsedException();
        }
        this.time = this.resume;
        this.resume = 0;
        this.reset();
        this.start();
    }

    /**
     * Stops the timer
     */
    public void stop() {
        if (0 == this.start) {
            throw new TimerNotUsedException();
        }
        if (System.currentTimeMillis() >= this.end) {
            throw new TimerAlreadyStoppedException();
        }
        this.resume = this.timeLeftMilli();
        this.end = System.currentTimeMillis();
    }

    /**
     * Resets the timer
     */
    public void reset() {
        this.start = 0;
        this.end = 0;
    }

    /**
     * Returns the running state of the timer
     * @return True if runs, false if not running
     */
    public boolean isRunning() {
        return this.end > System.currentTimeMillis();
    }

    /**
     * Returns the time left in minutes
     * @return Time left in minutes
     */
    public int timeLeft() {
        return (int) Math.ceil((double) this.timeLeftMilli() / 1000);
    }

    /**
     * Returns the time left in Milliseconds
     * @return Time left in milliseconds
     */
    private long timeLeftMilli() {
        if (0 == this.start || 0 == this.end) {
            return this.time;
        }
        if (this.isRunning()) {
            return this.end - System.currentTimeMillis();
        }
        if (0 != this.resume) {
            return this.resume;
        }
        return 0;
    }


    public static class TimerAlreadyUsedException extends RuntimeException {
        TimerAlreadyUsedException() {
            super("Please create a new one or call the reset-method.");
        }
    }

    public static class TimerNotUsedException extends RuntimeException {
        TimerNotUsedException() {
            super("You can't stop a timer, when you didn't start it first.");
        }
    }

    public static class TimerAlreadyStoppedException extends RuntimeException {
        TimerAlreadyStoppedException() {
            super("The timer you try to stop, is already stopped.");
        }
    }

}

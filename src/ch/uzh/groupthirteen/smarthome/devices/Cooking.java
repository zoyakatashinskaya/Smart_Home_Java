package ch.uzh.groupthirteen.smarthome.devices;

import ch.uzh.groupthirteen.smarthome.commands.Command;

import java.util.List;

public abstract class Cooking extends Device {

    private int temperature;

    @Override
    protected void reset() {
        super.reset();
        this.temperature = 0;
    }

    /**
     * Gets the temperature in °C
     * @return The temperature in °C
     */
    public int getTemperature() {
        return this.temperature;
    }

    /**
     * Sets the temperature in °C
     * @param temperature The temperature in °C
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}

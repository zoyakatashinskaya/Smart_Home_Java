package ch.uzh.groupthirteen.smarthome.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenTest {

    private MicrowaveOven microwaveOven;

    @BeforeEach
    void setUp() {
        microwaveOven = new MicrowaveOven();
    }

    @Test
    void testBaking() {
        microwaveOven.turnOn();
        microwaveOven.setTime(1);
        microwaveOven.setTemperature(100);
        microwaveOven.startBaking();
    }

    @Test
    void testTurnOff() {
        microwaveOven.turnOn();
        microwaveOven.turnOff();
        assertFalse(microwaveOven.isOn());
        assertFalse(microwaveOven.isTimerSet());
        assertFalse(microwaveOven.isBaking());
        assertEquals(0, microwaveOven.getTemperature());
        assertEquals(0, microwaveOven.timeLeft());
    }

}
package ch.uzh.groupthirteen.smarthome.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OvenTest {

    private Oven oven;

    @BeforeEach
    void setUp() {
        oven = new Oven();
    }

    @Test
    void testProgram() {
        oven.turnOn();
        String[] programs = oven.listPrograms();
        //oven.setProgram(programs[0]);
        assertEquals(programs[0], oven.getProgram());
    }

    @Test
    void testCooking() {
        oven.turnOn();
        oven.setTime(1);
        oven.setTemperature(100);
        String[] programs = oven.listPrograms();
        //oven.setProgram(programs[0]);
        oven.startCooking();
    }

    @Test
    void testTurnOff() {
        oven.turnOn();
        oven.turnOff();
        assertFalse(oven.isOn());
        assertFalse(oven.isTimerSet());
        assertFalse(oven.isCooking());
        assertEquals("", oven.getProgram());
        assertEquals(0, oven.getTemperature());
        assertEquals(0, oven.timeLeft());
    }


}
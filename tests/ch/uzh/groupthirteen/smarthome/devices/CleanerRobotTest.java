package ch.uzh.groupthirteen.smarthome.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CleanerRobotTest {

    private CleanerRobot cleanerRobot;


    @BeforeEach
    void setUp() {
        cleanerRobot = new CleanerRobot();
    }

    @Test
    void testTurnOff() {
        cleanerRobot.turnOn();
        cleanerRobot.turnOff();
        assertFalse(cleanerRobot.isOn());
        assertFalse(cleanerRobot.isTimerSet());
        assertTrue(cleanerRobot.isInBase());
        assertFalse(cleanerRobot.isCharging());
        assertEquals(0, cleanerRobot.getCleaningPercentage());
        assertEquals(100, cleanerRobot.getChargeLeftPercentage());
    }

    @Test
    void testCleanSmallRoom() {
        cleanerRobot.turnOn();
        cleanerRobot.setTime(1);
        cleanerRobot.startVacuumCleaner();
        assertTrue(cleanerRobot.isOn());
        assertTrue(cleanerRobot.isTimerSet());
        assertTrue(cleanerRobot.isInBase());
        assertFalse(cleanerRobot.isCharging());
        assertEquals(100, cleanerRobot.getChargeLeftPercentage());
        assertEquals(100, cleanerRobot.getChargeLeftPercentage());
    }

}

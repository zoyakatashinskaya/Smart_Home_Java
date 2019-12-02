package ch.uzh.groupthirteen.smarthome.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    private Device[] devices;

    @BeforeEach
    void setUp() {
        // Important, add here your new Device!
        devices = new Device[] {
                new CleanerRobot(),
                new MicrowaveOven(),
                new Oven()
        };
    }

    @Test
    void testTurnOn() {
        for (Device device: devices) {
            device.turnOn();
            assertTrue(device.isOn());
        }
    }

    @Test
    void testTurnOff() {
        for (Device device: devices) {
            device.turnOn();
            device.turnOff();
            assertFalse(device.isOn());
        }
    }

    @Test
    void testIfTimeSet() {
        for (Device device: devices) {
            device.turnOn();
            assertFalse(device.isTimerSet());
            device.setTime(1);
            assertTrue(device.isTimerSet());
        }
    }

    @Test
    void testTimeLeft() {
        for (Device device: devices) {
            device.turnOn();
            device.setTime(1);
            assertEquals(1, device.timeLeft());
        }
    }

    /*
    @Test
    void testCantInterruptException() {
        for (Device device: devices) {
            device.turnOn();
            device.setTime(1);
            assertThrows(Device.CantInterruptException.class, device::interrupt);
        }
    }
     */


}

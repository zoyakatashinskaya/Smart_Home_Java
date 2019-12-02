package ch.uzh.groupthirteen.smarthome.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CookingTest {

    private Cooking[] cookingDevices;

    @BeforeEach
    void setUp() {
        // Important, add here your new Device!
        cookingDevices = new Cooking[] {
                new MicrowaveOven(),
                new Oven()
        };
    }

    @Test
    void temperatureTest() {
        for (Cooking device: cookingDevices) {
            device.turnOn();
            device.setTemperature(100);
            assertEquals(100, device.getTemperature());
        }
    }

}
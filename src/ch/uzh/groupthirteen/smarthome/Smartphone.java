package ch.uzh.groupthirteen.smarthome;

import ch.uzh.groupthirteen.smarthome.devices.*;
import ch.uzh.groupthirteen.smarthome.commands.*;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * The smartphone class
 *
 * @author Dominik
 */
public class Smartphone {

    List<Device> devices;

    /**
     * Creates a new Smartphone with the smart-home-application, which has predefined devices.
     */
    public Smartphone() {

        //Add all devices
        this.devices = Arrays.asList(
                new CleanerRobot(),
                new Oven(),
                new MicrowaveOven(),
                new WashingMachine(),
                new Dishwasher()
                // TODO: Add Dishwasher and WashingMachine
        );
    }

    /**
     * "Runs" the smartphone
     */
    public void run() {
        System.out.println(
                "# # # # # # # # # #\n" +
                "#                 #\n" +
                "#      Nokia      #\n" +
                "#      ©2001      #\n" +
                "#                 #\n" +
                "# # # # # # # # # #\n" +
                "\n>Imagine the Nokia startup sound" +
                "\n>You open the Smart-Home Application."
        );
        this.listAllDevices();
    }

    /**
     * Lists all devices (add them in the constructor)
     */
    private void listAllDevices() {

        while (true) {

            System.out.println("\nAvailable Devices");

            // Iterates all devices (with the IteratorPattern), is more efficient after java-documentation
            ListIterator<Device> deviceListIterator = this.devices.listIterator();
            while (deviceListIterator.hasNext()) {
                System.out.println(deviceListIterator.nextIndex() + 1 + ": " + deviceListIterator.next());
            }

            System.out.println("Select via the letters 1-9 on your keyboard.");
            System.out.println("Press 0 to exit the application.");

            // User Input
            int selected = Helpers.getPhoneInput();

            if (0 == selected) {
                this.exit();
                break;
            }

            if (0 < selected && this.devices.size() > selected-1) {
                this.listAllCommandsOfDevice(this.devices.get(selected-1));
            }
            if (this.devices.size() <= selected-1) {
                System.out.println("There is no device on the slot " + selected);
            }

        }



    }

    /**
     * Lists all available commands of the device.
     * @param device of which the commands should be listed.
     */
    private void listAllCommandsOfDevice(Device device) {

        while (true) {

            System.out.println("\nAvailable Commands for " + device);

            List<Command> commands = device.listCommands();
            ListIterator<Command> commandListIterator = commands.listIterator();

            while (commandListIterator.hasNext()) {
                Command next = commandListIterator.next();
                if (!next.isAvailable()) {
                    System.out.print("* ");
                }
                System.out.println(commandListIterator.previousIndex() + 1 + ": " + next);
            }

            System.out.println("* means, that an other operation is needed first!");
            System.out.println("Select via the letters 1-9 on your keyboard.");
            System.out.println("Press 0 to go back to main menu.");

            // User Input
            int selected = Helpers.getPhoneInput();

            // Go back to device menu
            if (0 == selected) {
                break;
            }

            if (0 < selected && commands.size() > selected-1) {
                Command command = commands.get(selected-1);
                if (command.isAvailable()) {
                    command.execute();
                } else {
                    System.out.println("This operation needs an additional step.");
                }
            }
            if (commands.size() <= selected-1) {
                System.out.println("There is no command on the slot " + selected);
            }

        }

    }

    /**
     * Exit lock-in
     */
    private void exit() {
        System.out.println("Are you sure? 1-9 = yes, 0 = no. #Lock-in");
        int selected = Helpers.getPhoneInput();

        if (0 == selected) {
            this.listAllDevices();
        } else {
            System.out.println(">You close the application.");
        }

    }

    /**
     * Entry-point
     * @param args Console arguments
     */
    public static void main(String[] args) {
        Smartphone smartphone = new Smartphone();
        smartphone.run();
    }

}

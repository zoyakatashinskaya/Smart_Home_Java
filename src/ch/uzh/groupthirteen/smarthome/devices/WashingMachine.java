package ch.uzh.groupthirteen.smarthome.devices;

import ch.uzh.groupthirteen.smarthome.FastTimer;
import ch.uzh.groupthirteen.smarthome.commands.*;

import java.util.Arrays;
import java.util.List;

/**
 * The washing machine
 *
 * @author Zoia
 */

public class WashingMachine extends Device implements Programmable {

    /*
     * Washing machine:
     * + Switch on
     * - Select degrees
     * + Select type of washing (Double Rinse, Intense, Quick, Spin)
     * - Automatic timer based on the type of program
     * - Turn off the washing machine (only if the program is finished)
     * + Switch off
     */

    private int currentDegree;
    private String[] allPrograms;
    private int[] allDegrees;
    private int currentProgram;


    @Override
    protected void reset() {
        super.reset();

        this.allPrograms = new String[]{"Double Rinse", "Intense", "Quick", "Spin"};
        this.allDegrees = new int[]{0, 30, 40, 50};

        this.currentDegree = 0;
        this.currentProgram = 0;
    }

    /**
     * Resets just a part of the WashingMachine
     */
    public void softReset() {
        this.currentDegree = 0;
        this.currentProgram = 0;
        this.timer = null;
    }

    public void startWorking(){
        this.timer.start();
        while (this.timer.isRunning()) {}
    }

    @Override
    public String getProgram() {
        return this.allPrograms[this.currentProgram];
    }

    @Override
    public void setProgram(int program) {
        this.currentProgram = program-1;

        switch (program) {
            case 1:
                this.timer = new FastTimer(30);
                break;
            case 2:
                this.timer = new FastTimer(20);
                break;
            case 3:
                this.timer = new FastTimer(10);
                break;
            case 4:
                this.timer = new FastTimer(40);
                break;
        }
    }

    @Override
    public String[] listPrograms() {
        return this.allPrograms;
    }

    public int getDegree() {
        return this.currentDegree;
    }

    public void setDegree(int degree) {
        this.currentDegree = degree;
    }

    public int[] listDegree() {
        return this.allDegrees;
    }

    @Override
    public List<Command> listCommands() {
        return Arrays.asList(
                new SwitchOnCommand(this),
                new SelectDegreeCommand(this),
                new SetProgramCommand(this),
                new StartWashingMachineCommand(this),
                new TurnOffCommand(this),
                new SwitchOffCommand(this)
        );
    }

    @Override
    public String toString() {
        return "Washing Machine";
    }
}

package ch.uzh.groupthirteen.smarthome.devices;

import ch.uzh.groupthirteen.smarthome.FastTimer;
import ch.uzh.groupthirteen.smarthome.commands.*;

import java.util.Arrays;
import java.util.List;

public class Oven extends Cooking implements Programmable {
    //has to do:
    //○ Switch on
    //○ Set a timer (in seconds)
    //○ Set a temperature
    //○ Set up a program (ventilated, grill, etc)
    //○ Start cooking (only if the other parameters are set)
    //○ Check timer (if the oven is running, check the active timer, otherwise the last one set)
    //○ Interrupt the program (only possible if the oven is in operation)
    //○ Switch off

    private boolean isCooking;

    private String[] programs;
    private String currentProgram;

    @Override
    protected void reset() {
        super.reset();

        this.isCooking = false;

        this.programs = new String[] {
                "grill",
                "ventilated",
                "conventional heater",
                "bottom heater",
                "ventilated grill"
        };

        this.currentProgram = "";

    }

    @Override
    public List<Command> listCommands() {
        return Arrays.asList(
                new SwitchOnCommand(this),
                new SetTimerCommand(this),
                new CheckTimerCommand(this),
                new SetTemperatureCommand(this),
                new SetProgramCommand(this),
                new StartCookingCommand(this),
                new InterruptCommand(this),
                new SwitchOffCommand(this)
        );
    }

    @Override
    public String getProgram() {
        return this.currentProgram;
    }

    @Override
    public void setProgram(int program) {
        this.currentProgram =  this.programs[program-1];
    }

    @Override
    public String[] listPrograms() {
        return this.programs;
    }
\
    
    public void startCooking(){
        try {
            this.timer.resume();
        } catch (FastTimer.TimerNotUsedException e) {
            this.timer.start();
        }
        this.isCooking = true;
        while (this.timer.isRunning()) {}
        this.isCooking = false;
    }

    public boolean isCooking() {
        return isCooking;
    }

    @Override
    public String toString() {
        return "Oven";
    }
}

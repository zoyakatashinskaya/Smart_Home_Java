package ch.uzh.groupthirteen.smarthome.devices;

import ch.uzh.groupthirteen.smarthome.FastTimer;
import ch.uzh.groupthirteen.smarthome.commands.*;

import java.util.Arrays;
import java.util.List;

/**
 * The dishwasher
 *
 * @author Zoia
 */
public class Dishwasher extends Device implements Programmable {

    /*
     + Switch on
     + Start dishwasher:
     - Choose program (glasses, plates, pans, mixed, etc; each program has a different timer)
     - Automatic timer based on the type of program
     - Check timer (if a program is set, it says the time required; if the dishwasher is running, it says the remaining time)
     - Stop dishwasher
     + Switch off
     */

    private String[] allPrograms;
    private int currentProgram;

    @Override
    protected void reset() {
        super.reset();

        this.allPrograms = new String[]{"Glasses", "Plates", "Pans", "Mixed"};
        this.currentProgram = 0;
    }

    public void startCleaning(){
        try {
            this.timer.resume();
        } catch (FastTimer.TimerNotUsedException e) {
            this.timer.start();
        }
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
                this.timer = new FastTimer(20);
                break;
            case 2:
                this.timer = new FastTimer(30);
                break;
            case 3:
                this.timer = new FastTimer(40);
                break;
            case 4:
                this.timer = new FastTimer(60);
                break;
        }
    }


    @Override
    public List<Command> listCommands() {
        return Arrays.asList(
                new SwitchOnCommand(this),
                new SetProgramCommand(this),
                new CheckTimerCommand(this),
                new StartCleaningCommand(this),
                new InterruptCommand(this),
                new SwitchOffCommand(this)
        );
    }

    @Override
    public String[] listPrograms() {
        return this.allPrograms;
    }

    @Override
    public String toString() {
        return "Dishwasher";
    }

}

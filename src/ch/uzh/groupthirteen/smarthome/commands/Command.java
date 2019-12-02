package ch.uzh.groupthirteen.smarthome.commands;

public interface Command {

    public void execute();
    public boolean isAvailable();

}

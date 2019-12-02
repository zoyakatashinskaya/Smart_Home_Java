package ch.uzh.groupthirteen.smarthome.devices;

public interface Programmable {

    public String getProgram();
    public void setProgram(int program);
    public String[] listPrograms();

}

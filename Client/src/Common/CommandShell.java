package Common;

import java.io.Serializable;

public class CommandShell implements Serializable{

    private String name;
    private Serializable firstArg;
    private int secondArg;
    private String thirdArg;

    CommandShell(String name, Serializable firstArg){
        this.name = name;
        this.firstArg = firstArg;
    }

    CommandShell(String name, int secondArg){
        this.name = name;
        this.secondArg = secondArg;
    }

    CommandShell(String name, String thirdArg){
        this.name = name;
        this.thirdArg = thirdArg;
    }

    CommandShell(String name){
        this.name = name;
    }

    public String getName(){return this.name;}
    public Serializable getFirstArg(){return this.firstArg;}
    public int getSecondArg(){return this.secondArg;}
    public String getThirdArg(){return this.thirdArg;}

    @Override
    public String toString() {
        return getName() + getFirstArg() + getSecondArg() + getThirdArg();
    }

    public void setName(String name) {this.name = name;}
    public void setFirstArg(Serializable firstArg) {this.firstArg = firstArg;}
    public void setSecondArg(int secondArg) {this.secondArg = secondArg;}
    public void setThirdArg(String thirdArg) {this.thirdArg = thirdArg;}
}
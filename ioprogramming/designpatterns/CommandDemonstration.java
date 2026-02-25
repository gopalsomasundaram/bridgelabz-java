/**
 * This program is a demonstration of the Command design pattern.
 * It encapsulates a request to turn a light on or off as an object.
 */

package ioprogramming.designpatterns;

interface Command { void execute(); }

class Light {
    public void on() { System.out.println("Light is ON"); }
    public void off() { System.out.println("Light is OFF"); }
}

class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
}

class RemoteControl {
    private Command command;
    public void setCommand(Command command) { this.command = command; }
    public void pressButton() { command.execute(); }
}

public class CommandDemonstration {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        RemoteControl remote = new RemoteControl();

        remote.setCommand(new LightOnCommand(livingRoomLight));
        remote.pressButton();

        remote.setCommand(new LightOffCommand(livingRoomLight));
        remote.pressButton();
    }
}
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Launcher;

public class LauncherElevator extends Command {
    private boolean goingUp = true;

    public LauncherElevator(boolean goingUp) {
        super();
        this.goingUp = goingUp;
    }

    @Override
    protected void initialize() {
        System.out.println("MOVING ELEVATOR");
    }

    @Override
    protected void execute() {
        if (goingUp) {
            Launcher.getInstance().up();
            System.out.println("launcherGoingUp");
        } else {
            Launcher.getInstance().down();
            System.out.println("launcherGoingDown");
        }
    }

    @Override
    protected void end() {
        Launcher.getInstance().stopElevator();
    }

    @Override
    protected boolean isFinished() {
        if (goingUp) {
            System.out.println("Ended up");        
            return Launcher.getInstance().readUpSwitch();
        } else if (!goingUp) {
            System.out.println("Ended down");
            return Launcher.getInstance().readDownSwitch();
            
        }
        return true;
    }
}
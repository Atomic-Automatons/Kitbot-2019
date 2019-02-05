package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;

/**
 * NOT RESPONSIBLE FOR ANKLEZ THAT MAY OR MAY NOT BE LOST DURING THE OPERATION
 * OF THIS PROGRAM! "Ankles are a privelage not a right."-NateD The Hip Hop Star
 */
public class Beyblade extends Command {
    public Beyblade() {
        super();
    }

    @Override
    protected void initialize() {
        DriveSystem.getInstance().startAuto();
    }

    @Override
    protected void execute() {
        DriveSystem.getInstance().arcadeDrive(0, 666);
    }

    @Override
    protected void end() {
        DriveSystem.getInstance().stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
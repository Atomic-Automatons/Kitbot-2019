package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveSystem;

public class CameraTurn extends Command {

    public CameraTurn() {
        super();
        requires(DriveSystem.getInstance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    SmartDashboard.putString("Camera turn", Camera.getInstance().center(Camera.getInstance().process()));
    }

    @Override
    protected void end() {
    }
}
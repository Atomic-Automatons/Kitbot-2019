package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CameraTurn;
import frc.robot.Robot;

public class CompleteCargo extends CommandGroup {
    boolean teleopRunning = false;

    public CompleteCargo() {
        addSequential(new CameraTurn());
        addSequential(new RaiseCargo());
        addSequential(new LaunchCargo());
        addSequential(new LowerCargo());
    }

    protected void initialize() {
        super.initialize();
        if (Robot.m_teleopCommand.isRunning()) {
            teleopRunning = true;
            Robot.m_teleopCommand.cancel();
        }
    }

    protected void interrupted() {
        super.interrupted();
        end();
    }

    protected void end() {
        super.end();
        if (teleopRunning) {
            Robot.m_teleopCommand.start();
        }
    }
}
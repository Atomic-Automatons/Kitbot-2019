package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;

public class StraightTurn extends CommandGroup {
    public StraightTurn() {
        addSequential(new DriveStraight(24.0, 0.5));
        addSequential(new Turn(45));
        addSequential(new StopDrive());
    }
}
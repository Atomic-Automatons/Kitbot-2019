package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CameraTurn;

public class CompleteCargo extends CommandGroup {
    public CompleteCargo() {
        addSequential(new CameraTurn());
        addSequential(new RaiseCargo());
        addSequential(new LaunchCargo());
        addSequential(new LowerCargo());
    }
}
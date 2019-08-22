package frc.robot.commands.HatchCommands.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.HatchCommands.MoveHatchDown;
import frc.robot.commands.HatchCommands.MoveMotorUpFAST;

public class MoveHatchFast extends CommandGroup {
    public MoveHatchFast(){
        addParallel(new MoveMotorUpFAST());
        addParallel(new MoveHatchDown());
    }
}
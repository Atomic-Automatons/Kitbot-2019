package frc.robot.commands.HatchCommands.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.HatchCommands.MoveHatchDown;
import frc.robot.commands.HatchCommands.MoveHatchUp;

public class MoveHatchElevator extends CommandGroup {
    public static boolean up = true;
    public MoveHatchElevator(){
        if(up){
        addParallel(new MoveHatchUp());
        }else{
        addParallel(new MoveHatchDown());
        }
        toggle();
    }
    public static void toggle(){
        if(up){
            up=false;
        }else{
            up=true;
        }
    }
}
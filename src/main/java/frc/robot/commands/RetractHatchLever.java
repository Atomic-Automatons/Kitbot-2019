
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchControls;

public class RetractHatchLever extends Command {
    int counter = 0;

    public void initialize() {
        counter = 0;
    }

    public void execute() {
        if(counter <= 11){
            //HatchControls.getInstance().moveLeverUp();  
        }else{
            HatchControls.getInstance().stopLever();
        }
        System.out.println(counter + "from hatch");
        counter++;
    }

    public boolean isFinished() {
        return false;// change later
    }
}
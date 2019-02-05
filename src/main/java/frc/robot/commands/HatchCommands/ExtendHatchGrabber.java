
package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchControls;

public class ExtendHatchGrabber extends Command {
    int counter = 0;

    public ExtendHatchGrabber() {
        requires(HatchControls.getInstance());
    }

    public void initialize() {
        counter = 0;
        

    }

    public void execute() {
        if(counter <= 10){
            HatchControls.getInstance().moveLeverDown();  
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
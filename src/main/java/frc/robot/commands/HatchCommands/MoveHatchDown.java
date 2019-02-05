
package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchControls;

public class MoveHatchDown extends Command {
    int counter = 0;

    public MoveHatchDown() {
        requires(HatchControls.getInstance());
    }

    public void initialize() {
        counter = 0;
    }

    public void execute() {
        if(!HatchControls.getInstance().readDownSwitch()){
        HatchControls.getInstance().moveMotorDown();
        }else{
            HatchControls.getInstance().stopUp();
        }
        

    }

    public boolean isFinished() {
        return HatchControls.getInstance().readDownSwitch();// change later
    }

}

package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchControls;

public class MoveHatchUp extends Command {
    int counter = 0;

    public MoveHatchUp() {
        requires(HatchControls.getInstance());
    }

    public void initialize() {
        counter = 0;
    }

    public void execute() {
        if(HatchControls.getInstance().readSwitch()){
            HatchControls.getInstance().chillUpMotor();
        }else{
        HatchControls.getInstance().moveMotorUp();
        }

    }

    public boolean isFinished() {
        return HatchControls.getInstance().readSwitch();// change later
    }

}
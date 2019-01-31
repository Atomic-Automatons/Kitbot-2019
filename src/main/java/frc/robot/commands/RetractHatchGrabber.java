
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchControls;

public class RetractHatchGrabber extends Command {
    int counter = 0;

    public RetractHatchGrabber() {
        requires(HatchControls.getInstance());
    }

    public void initialize() {
        HatchControls.getInstance().moveMotorUp();

    }

    public void execute() {
        if (HatchControls.getInstance().readSwitch()) {
            HatchControls.getInstance().chillUpMotor();
            HatchControls.getInstance().moveLeverOut();
        }
        if (counter > 20) {
            HatchControls.getInstance().chillLeverMotor();
        } else {
            counter++;
        }

    }

    public boolean isFinished() {
        return false;// change later
    }

}
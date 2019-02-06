package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchControls;

public class UpDown extends Command {
    /**
     * Add your docs here.
     */
    public boolean fast;
    static boolean goingUp;

    public UpDown(boolean fast) {
        super();
        this.fast = fast;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        if (HatchControls.getInstance().readDownSwitch() && fast) {
            HatchControls.getInstance().moveMotorUpFAST();
            goingUp = true;
        } else if (HatchControls.getInstance().readDownSwitch() && !fast) {
            HatchControls.getInstance().moveMotorUp();
            goingUp = true;
        } else if (HatchControls.getInstance().readSwitch()) {
            HatchControls.getInstance().moveMotorDown();
            goingUp = false;
        }

    }

    @Override
    protected void execute() {

    }

    protected void end() {
        if (HatchControls.getInstance().readSwitch()) {
            HatchControls.getInstance().chillUpMotor();
        } else if (HatchControls.getInstance().readDownSwitch()) {
            HatchControls.getInstance().stopUp();
        }
    }

    protected boolean isFinished() {
        if (goingUp) {
            return HatchControls.getInstance().readSwitch();
        } else if (!goingUp) {
            return HatchControls.getInstance().readDownSwitch();
        }
        return true;
    }

}
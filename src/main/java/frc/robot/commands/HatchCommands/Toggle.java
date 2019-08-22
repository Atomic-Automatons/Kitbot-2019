
package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.HatchControls;

public class Toggle extends InstantCommand {

    public Toggle() {
        super();
        requires(HatchControls.getInstance());
        
    }

    public void initialize() {
        HatchControls.getInstance().toggle();
    }

    public void execute() {
        

    }

    public boolean isFinished() {
        return false;
    }

}

package frc.robot.commands.HatchCommands.commandGroups;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.HatchControls;

public class ToggleUp extends InstantCommand {
    private static boolean fast;
    public ToggleUp(boolean fast) {
        super();
        this.fast = fast;
        requires(HatchControls.getInstance());
        
    }

    public void initialize() {
        MoveHatchElevator.toggle();
    }

    public void execute() {
        

    }

    public boolean isFinished() {
        return false;
    }

}

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.HatchControls;

public class ToggleHatchElevator extends InstantCommand {

    public ToggleHatchElevator() {
        requires(HatchControls.getInstance());
    }

    public void initialize() {
        HatchControls.getInstance().toggleUp();
    }
}
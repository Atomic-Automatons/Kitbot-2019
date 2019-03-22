
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.HatchControls;

public class ToggleHatchLever extends InstantCommand {
    public void initialize() {
        System.out.println("Toggling Lever");
        HatchControls.getInstance().toggleLever();
    }
}
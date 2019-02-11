
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Cargo;

public class ToggleCargoElevator extends InstantCommand {
    public void initialize() {
        Cargo.getInstance().toggleUp();
    }
}
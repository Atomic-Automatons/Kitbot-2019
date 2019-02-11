package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSystem;

public class ToggleInvertDriveSystem extends InstantCommand {
    @Override
    protected void initialize() {
        DriveSystem.getInstance().toggleInverted();
    }
}

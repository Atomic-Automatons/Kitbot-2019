package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSystem;

/**
 * Add your docs here.
 */
public class ShiftDown extends InstantCommand {
	@Override
	protected void initialize() {
		DriveSystem.getInstance().shiftDown();
	}
}
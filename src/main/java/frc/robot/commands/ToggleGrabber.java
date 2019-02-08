package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.HatchControls;

public class ToggleGrabber extends InstantCommand {
	@Override
	protected void initialize() {
		HatchControls.getInstance().toggleCylinder();
	}

}
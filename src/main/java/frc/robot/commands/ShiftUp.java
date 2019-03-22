package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSystem;

public class ShiftUp extends InstantCommand {
	@Override
	protected void initialize() {
		DriveSystem.getInstance().shiftUp();
		System.out.println("Shifting Up");
	}

}
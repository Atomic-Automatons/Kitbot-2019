package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSystem;

/**
 * Add your docs here.
 */
public class ShiftUp extends InstantCommand {
	/**
	 * Add your docs here.
	 */
	public ShiftUp() {
		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called once when the command executes
	@Override
	protected void initialize() {
		DriveSystem.getInstance().shiftUp();
	}

}
package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.HatchControls;

/**
 * Add your docs here.
 */
public class OpenGrabber extends InstantCommand {
	/**
	 * Add your docs here.
	 */
	public OpenGrabber() {
		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called once when the command executes
	@Override
	protected void initialize() {
		HatchControls.getInstance().open();
	}

}
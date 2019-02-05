package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.HatchControls;

/**
 * Add your docs here.
 */
public class CloseGrabber extends InstantCommand {
	/**
	 * Add your docs here.
	 */
	public CloseGrabber() {
		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called once when the command executes
	@Override
	protected void initialize() {
		HatchControls.getInstance().close();
	}

}
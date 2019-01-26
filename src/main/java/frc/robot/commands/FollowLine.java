package frc.robot.commands;

import frc.robot.subsystems.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Photoresistor;

public class FollowLine extends Command {
	double turnSpeed = 0; // 0.2
	double moveSpeed = 0.31; // 0.6
	double maxDistance = 0.35;// distance from ultrasonic sensor to wall in meters
	boolean[] active = { false, false, false };

	public FollowLine() {
		super();
		requires(DriveSystem.getInstance());
	}

	@Override
	protected boolean isFinished() {
		return Ultrasonic.getInstance().getDistance() < maxDistance;
	}

	@Override
	protected void initialize() {
		DriveSystem.getInstance().startAuto();
	}

	@Override
	protected void execute() {

		active = Photoresistor.getInstance().getVals();

		if (active[0]) {
			turnSpeed = -0.47;
		} else if (active[2]) {
			turnSpeed = 0.47;
		} else {
			turnSpeed = 0.0;
		}

		if (active[1]) {
			moveSpeed = 0.47;
		} else {
			moveSpeed = 0.35;
		}

		if (!active[0] && !active[1] && !active[2]) {
			// moveSpeed = 0.4;

		}
		if (active[0] && active[1] && active[2]) {
			turnSpeed = 0.45;
			moveSpeed = 0;
		}

		DriveSystem.getInstance().arcadeDrive(moveSpeed, turnSpeed);

	}

	@Override
	protected void end() {
		DriveSystem.getInstance().stop();
	}
}
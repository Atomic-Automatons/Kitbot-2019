package frc.robot.commands;

import frc.robot.subsystems.UltrasonicHatch;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Photoresistor;

public class FollowLine extends Command {
	double turnSpeed = 0; // 0.2
	double moveSpeed = 0.31; // 0.6
	double maxDistance = 0.35;// distance from ultrasonic sensor to wall in meters
	double turnNum = 0.43;// the magnitude of the turning value
	boolean[] active = { false, false, false };

	public FollowLine() {
		super();
		//requires(DriveSystem.getInstance());
	}

	@Override
	protected boolean isFinished() {
		return UltrasonicHatch.getInstance().getDistance() < maxDistance;
	}

	@Override
	protected void initialize() {
		DriveSystem.getInstance().startAuto();
	}

	@Override
	protected void execute() {
		if(isFinished()){
			return;
		}
		
		double speedNum = 0.5;

		active = Photoresistor.getInstance().getVals();
		// active[0] is the left most photoresistor and active[2] is the right most
		// photoresistor

		if (active[0]) {
			turnSpeed = -turnNum;
		} else if (active[2]) {
			turnSpeed = turnNum;
		} else {
			turnSpeed = 0.0;
		}

		if (active[1]) {
			moveSpeed = speedNum;
			speedNum -= 0.001;
		} else {
			moveSpeed = 0.3;
			speedNum -= 0.001;
		}

		if (!active[0] && !active[1] && !active[2]) {
			// moveSpeed = 0.4;

		}
		if (active[0] && active[1] && active[2]) {
			turnSpeed = turnNum;
			moveSpeed = 0;
		}

		DriveSystem.getInstance().arcadeDrive(moveSpeed, turnSpeed);

	}

	@Override
	protected void end() {
		DriveSystem.getInstance().stop();
	}
}
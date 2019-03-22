package frc.robot.commands;

import frc.robot.subsystems.UltrasonicHatch;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Photoresistor;

public class FollowLine extends Command {
	double turnSpeed = 0; // 0.2
	//double moveSpeed = 0.39; // 0.6
	public static double maxDistance = 293.8;// distance from ultrasonic sensor to wall in meters
	double turnNum = 0.55;// the magnitude of the turning value
	boolean[] active = { false, false, false };

	public FollowLine() {
		super();
		// requires(DriveSystem.getInstance());
	}

	@Override
	public boolean isFinished() {
		//return UltrasonicHatch.getInstance().getDistance() < maxDistance;
		return active[1] && !active[2] && !active[0]; 
	}

	@Override
	protected void initialize() {
		DriveSystem.getInstance().startAuto();
	}
	double speedNum = 0.5;
	@Override
	protected void execute() {
		if (isFinished()) {
			return;
		}
		

		active = Photoresistor.getInstance().getVals();
		// active[0] is the left most photoresistor and active[2] is the right most
		// photoresistor

		if (active[0]) {
			turnSpeed = -turnNum;//-turnNum;
		} else if (active[2]) {
			turnSpeed = turnNum;//turnNum;
		} else {
			turnSpeed = 0.0;
		}
		/*if((UltrasonicHatch.getInstance().getDistance() < maxDistance && active[0]) || (active[2] && UltrasonicHatch.getInstance().getDistance() < maxDistance)){
			moveSpeed=-speedNum;
		}
		if (active[1]) {
			moveSpeed = speedNum;
			speedNum -= 0.004;
		} else {
			moveSpeed = 0.32;
			speedNum -= 0.004;
		}*/
		

		if (!active[0] && !active[1] && !active[2]) {
			// moveSpeed = 0.4;

		}
		if (active[0] && active[1] && active[2]) {
			turnSpeed = turnNum;
			//moveSpeed = 0;
		}

		DriveSystem.getInstance().arcadeDrive(0.3, -turnSpeed);

	}

	@Override
	protected void end() {
		System.out.println("Follow line is finsihesd");
		DriveSystem.getInstance().stop();
	}
}
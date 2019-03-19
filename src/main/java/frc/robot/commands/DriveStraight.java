package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Encoders;

public class DriveStraight extends Command {
    private double speed = 0;
	private double time = 0;
	private double kP = 0.1;
	private double distance = 0;
	/**distance then speed */
	public DriveStraight(double distance, double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(DriveSystem.getInstance());
		DriveSystem.getInstance().startAuto();
		this.speed = speed;
		//this.time = time;
		this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		NavX.getInstance().reset();
		Encoders.getInstance().reset();
        DriveSystem.getInstance().startAuto();
	}

	double speedslower = 2.5;
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Tryignt to move");
		DriveSystem.getInstance().arcadeDrive(clamp(((this.distance-Encoders.getInstance().getRightDistance())/(this.distance*speedslower)*speed)),
		 -clamp(NavX.getInstance().getAngle() * kP));
	}
	double upper = 0.7;
	double lower = 0.0;
	protected double clamp(double val) {
        if (Math.abs(val) > upper) {
            return upper * Math.signum(val);
		} else if (Math.abs(val) < lower) {
            return lower * Math.signum(val);
        }

        return val;
    }
	// Make this return true when this Command no longer needs to run execute()
	/**This currently only takes in the right encoder because the left side wasnt working*/
	protected boolean isFinished() {
		return Math.abs(Encoders.getInstance().getRightDistance()) > this.distance;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Straight drive done");
		DriveSystem.getInstance().stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

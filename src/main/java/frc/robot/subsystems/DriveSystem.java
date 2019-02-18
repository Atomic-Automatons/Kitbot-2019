package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSystem extends Subsystem {

	static private DriveSystem instance = null;

	static public DriveSystem getInstance() {
		if (instance == null) {
			instance = new DriveSystem();
		}
		return instance;
	}

	private DifferentialDrive drive;

	private DoubleSolenoid gearShift = new DoubleSolenoid(0, 1);

	private double modifier = 1;
	private boolean inverted = false;

	WPI_VictorSPX frontRight = new WPI_VictorSPX(6);
	WPI_VictorSPX backRight = new WPI_VictorSPX(7);
	WPI_VictorSPX frontLeft = new WPI_VictorSPX(8);
	WPI_VictorSPX backLeft = new WPI_VictorSPX(9);

	private DriveSystem() {
		frontRight.setInverted(InvertType.None);
		backRight.follow(frontRight);
		backRight.setInverted(InvertType.FollowMaster);

		frontLeft.setInverted(InvertType.InvertMotorOutput);
		backLeft.follow(frontLeft);
		backLeft.setInverted(InvertType.FollowMaster);

		double rampTime = .7;
		frontRight.configOpenloopRamp(rampTime);
		frontLeft.configOpenloopRamp(rampTime);

		drive = new DifferentialDrive(frontLeft, frontRight);
		drive.setRightSideInverted(false);
		// drive.set
		drive.setSafetyEnabled(false);

		shiftUp();
	}

	public void shiftUp() {
		gearShift.set(Value.kReverse); // Changed in EST
	}

	public void shiftDown() {
		gearShift.set(Value.kForward); // Changed in EST
	}

	/**
	 * This Function controls driving using speed and turn speed
	 * 
	 * @param x = Speed
	 * @param y = Direction
	 */
	public void arcadeDrive(double x, double y) {
		drive.arcadeDrive(-1 * modifier * x, y);
	}

	public void startAuto() {
		drive.setSafetyEnabled(false);
	}

	public void stop() {
		drive.arcadeDrive(0, 0);
		drive.setSafetyEnabled(true);
	}

	public void enableInverted() {
		// left.setInverted(false);
		// right.setInverted(false);
		modifier = -1;
		inverted = true;
	}

	public void disableInverted() {
		// left.setInverted(true);
		// right.setInverted(true);
		modifier = 1;
		inverted = false;
	}

	public void toggleInverted() {
		if (inverted) {
			disableInverted();
		} else {
			enableInverted();
		}
	}

	@Override
	public void initDefaultCommand() {
	}
}
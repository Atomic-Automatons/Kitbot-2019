package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.RobotMap;

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

	private SpeedController right;
	private SpeedController left;

	private double modifier = 1;
	private boolean inverted = false;

	public DriveSystem() {
		PWMVictorSPX backLeft = new PWMVictorSPX(RobotMap.backLeftMotor);
		PWMVictorSPX frontLeft = new PWMVictorSPX(RobotMap.frontLeftMotor);
		PWMVictorSPX backRight = new PWMVictorSPX(RobotMap.backRightMotor);
		PWMVictorSPX frontRight = new PWMVictorSPX(RobotMap.frontRightMotor);

		right = new SpeedControllerGroup(backRight, frontRight);
		left = new SpeedControllerGroup(backLeft, frontLeft);

		left.setInverted(true);
		right.setInverted(true);

		drive = new DifferentialDrive(left, right);
		// drive.setRightSideInverted(true);
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
		drive.arcadeDrive(modifier * x, y);
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
		System.out.println("sduhsdh");
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
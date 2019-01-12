package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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

  public DriveSystem() {
    PWMVictorSPX backLeft = new PWMVictorSPX(RobotMap.backLeftMotor);
    PWMVictorSPX frontLeft = new PWMVictorSPX(RobotMap.frontLeftMotor);
    PWMVictorSPX backRight = new PWMVictorSPX(RobotMap.backRightMotor);
    PWMVictorSPX frontRight = new PWMVictorSPX(RobotMap.frontRightMotor);

    SpeedController right = new SpeedControllerGroup(backRight, frontRight);
    SpeedController left = new SpeedControllerGroup(backLeft, frontLeft);

    // left.setInverted(true);
    // right.setInverted(true);

    drive = new DifferentialDrive(left, right);
  }

  public void arcadeDrive(double x, double y) {
    drive.arcadeDrive(x, y);
  }

  public void startAuto() {
    drive.setSafetyEnabled(false);
  }

  public void stop() {
    drive.arcadeDrive(0, 0);
    drive.setSafetyEnabled(false);
  }

  @Override
  public void initDefaultCommand() {
  }
}
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.DriveSystem;
import frc.robot.OI;
//import frc.robot.subsystems.Encoders;

public class JoystickDrive extends Command {
  public JoystickDrive() {
    requires(DriveSystem.getInstance());
  }

  @Override
  protected void initialize() {
    // Encoders.getInstance().reset();
  }

  @Override
  protected void execute() {
    double sensitivity = (1 - OI.stick.getRawAxis(OI.joystickSensitivityAxis));
    // Joystick going forward is negative
    DriveSystem.getInstance().arcadeDrive(-1 * sensitivity * OI.stick.getY(), sensitivity * OI.stick.getX());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    DriveSystem.getInstance().stop();
  }

  @Override
  protected void interrupted() {
  }
}
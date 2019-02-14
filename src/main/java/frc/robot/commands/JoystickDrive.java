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
    double y = OI.stick.getY();
    double x = OI.stick.getX();
    // The ( -1 * -1 ) in arcade drive exists to explain that the joystick input
    // is backward, but the gearbox inverts the input, so it balances out in ( -1 *
    // -1 )
    DriveSystem.getInstance().arcadeDrive((-1) * sensitivity * y, sensitivity * x);
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
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Cargo;

public class EjectCargo extends Command {
  public EjectCargo() {
    super();
    requires(Cargo.getInstance());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void initialize() {
    Cargo.getInstance().ejectCargo();
  }

  @Override
  protected void end() {
    Cargo.getInstance().setSpeed(0);
  }
}
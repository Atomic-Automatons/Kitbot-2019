package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Cargo;

public class CargoExhale extends Command {
  public CargoExhale() {
    super();
    requires(Cargo.getInstance());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void initialize() {
    Cargo.getInstance().setSpeed(0.7);
  }

  @Override
  protected void end() {
    Cargo.getInstance().setSpeed(0);
  }
}
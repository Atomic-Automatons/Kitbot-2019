package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.Cargo;

public class CargoInhale extends Command {
    public CargoInhale() {
        super();
        requires(Cargo.getInstance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void initialize() {
        Cargo.getInstance().setSpeed(0.5);
    }

    @Override
    protected void end() {
        Cargo.getInstance().setSpeed(0);
    }
}
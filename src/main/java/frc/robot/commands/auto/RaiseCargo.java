package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Cargo;

public class RaiseCargo extends Command {
    public RaiseCargo() {
        super();
    }

    @Override
    protected boolean isFinished() {
        return Cargo.getInstance().isUp();
    }

    @Override
    protected void initialize() {
        Cargo.getInstance().setUp(true);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected void end() {

    }
}
package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Cargo;

public class LowerCargo extends Command {
    public LowerCargo() {
        super();
    }

    @Override
    protected boolean isFinished() {
        return Cargo.getInstance().isDown();
    }

    @Override
    protected void initialize() {
        Cargo.getInstance().setUp(false);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected void end() {

    }
}
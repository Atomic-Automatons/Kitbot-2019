package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Cargo;

public class LaunchCargo extends Command {
    public LaunchCargo() {
        setTimeout(0.3);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void initialize() {
        Cargo.getInstance().ejectCargo();
    }

    @Override
    protected void execute() {

    }

    @Override
    protected void end() {
        Cargo.getInstance().setSpeed(0);
    }
}
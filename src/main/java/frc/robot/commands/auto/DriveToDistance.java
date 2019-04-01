package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.UltrasonicHatch;

public class DriveToDistance extends Command {
    double tolerance = 10;

    public DriveToDistance() {

    }

    @Override
    protected void execute() {
        double distance = UltrasonicHatch.getInstance().getDistance();
        if (distance > 0) {
            DriveSystem.getInstance().arcadeDrive(0.4, 0);
        } else {
            DriveSystem.getInstance().arcadeDrive(-0.4, 0);
        }
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(UltrasonicHatch.getInstance().getDistance()) < tolerance;
    }
}
package frc.robot.commands;

import frc.robot.subsystems.UltrasonicCargo;
import frc.robot.subsystems.UltrasonicHatch;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.JeVoisCargo;
import frc.robot.subsystems.JeVoisHatch;

public class CameraTurn extends Command {
    public CameraTurn() {
        super();
    }

    @Override
    protected boolean isFinished() {
        return UltrasonicCargo.getInstance().getDistance() < 800;
    }

    @Override
    protected void initialize() {
        DriveSystem.getInstance().startAuto();
        DriveSystem.getInstance().enableInverted();
    }

    double deadband = 1.5;

    @Override
    protected void execute() {
        double center = JeVoisCargo.getInstance().getAngle();
        double moveSpeed = .5; // .41
        /*
         * if (Math.abs(center) > 0.07) { double val = clamp(center);
         * DriveSystem.getInstance().arcadeDrive(0, val); } else {
         * DriveSystem.getInstance().arcadeDrive(0, 0); }
         */
        if (Math.abs(center) >= deadband) {
            DriveSystem.getInstance().arcadeDrive(moveSpeed, clamp(center / 20));
        } else {
            DriveSystem.getInstance().arcadeDrive(moveSpeed, 0);
        }
    }

    double upper = 0.6;// .5
    double lower = 0.1;// .41

    protected double clamp(double val) {
        if (Math.abs(val) > upper) {
            return upper * Math.signum(val);
        } else if (Math.abs(val) < lower) {
            return lower * Math.signum(val);
        }

        return val;
    }

    @Override
    protected void end() {
        DriveSystem.getInstance().stop();
    }
}
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
        return false;
        // return JeVois.getInstance().getSize() > distance;
        //return UltrasonicHatch.getInstance().getDistance() < 320;
    }

    @Override
    protected void initialize() {
        DriveSystem.getInstance().startAuto();
    }

    int timer = 0;
    double speed = 0.5;// 4.1
    double deadband = 1.5;
    double distance = 4000;// 4300

    @Override
    protected void execute() {
        double center = JeVoisHatch.getInstance().getAngle();
        double moveSpeed = .41;
        /*
         * if (Math.abs(center) > 0.07) { double val = clamp(center);
         * DriveSystem.getInstance().arcadeDrive(0, val); } else {
         * DriveSystem.getInstance().arcadeDrive(0, 0); }
         */
        System.out.println(center);
        if (Math.abs(center) >= deadband) {
            DriveSystem.getInstance().arcadeDrive(moveSpeed, clamp((center / 20)));
        } else {
            DriveSystem.getInstance().arcadeDrive(moveSpeed, 0);
        }

    }

    double upper = 0.6;// .5
    double lower = 0.5;// .41

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
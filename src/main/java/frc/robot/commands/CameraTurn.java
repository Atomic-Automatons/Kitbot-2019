package frc.robot.commands;

import frc.robot.subsystems.UltrasonicHatch;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.JeVois;

public class CameraTurn extends Command {
    double maxDistance = 0.35;

    public CameraTurn() {
        super();
        //requires(DriveSystem.getInstance());
    }

    @Override
    protected boolean isFinished() {
        // return JeVois.getInstance().getSize() > distance;
        return UltrasonicHatch.getInstance().getDistance() < maxDistance;
    }

    @Override
    protected void initialize() {
        DriveSystem.getInstance().startAuto();
    }

    int timer = 0;
    double speed = 0.41;
    double deadband = 1.5;
    double distance = 4000;// 4300

    @Override
    protected void execute() {
        System.out.println("CAMERA TURN");
        double center = JeVois.getInstance().getAngle();
        double move = JeVois.getInstance().getSize();
        double moveSpeed = 0;
        /*
         * if (Math.abs(center) > 0.07) { double val = clamp(center);
         * DriveSystem.getInstance().arcadeDrive(0, val); } else {
         * DriveSystem.getInstance().arcadeDrive(0, 0); }
         */
        if (move <= distance && move != 0) {
            moveSpeed = speed;
        }

        if (Math.abs(center) >= deadband) {
            DriveSystem.getInstance().arcadeDrive(moveSpeed, clamp((center / 20)));
        } else {
            DriveSystem.getInstance().arcadeDrive(moveSpeed, 0);
        }

    }

    double upper = 0.5;
    double lower = 0.41;

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
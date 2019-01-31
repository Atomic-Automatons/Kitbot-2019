package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.JeVois;

public class CameraTurn extends Command {

    public CameraTurn() {
        super();
        requires(DriveSystem.getInstance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        DriveSystem.getInstance().startAuto();
        // Camera.getInstance().startThread();
    }

    int timer = 0;
    double speed = 0.5;
    double deadband = 2;

    @Override
    protected void execute() {
        double center = JeVois.getInstance().getAngle();
        /*
         * if (Math.abs(center) > 0.07) { double val = clamp(center);
         * DriveSystem.getInstance().arcadeDrive(0, val); } else {
         * DriveSystem.getInstance().arcadeDrive(0, 0); }
         */
        if (Math.abs(center) >= deadband) {
            DriveSystem.getInstance().arcadeDrive(0, clamp((center / 20)));
        } else {
            DriveSystem.getInstance().arcadeDrive(0, 0);
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
        // Camera.getInstance().stopThread();
    }
}
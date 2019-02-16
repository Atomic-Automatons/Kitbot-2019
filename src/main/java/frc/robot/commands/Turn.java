package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.NavX;

public class Turn extends Command {
    private double measuredAngle;
    private double targetAngle;
    private double speed = 0.5; // 0.55
    private double tolerance = 0.1;

    public Turn(double angle) {
        this.targetAngle = angle;
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(measuredAngle - targetAngle) < tolerance;
    }

    @Override
    protected void initialize() {
        NavX.getInstance().reset();
        DriveSystem.getInstance().startAuto();
    }

    @Override
    protected void execute() {
        measuredAngle = NavX.getInstance().getDegrees();
        double rotSpeed = clamp(speed * (measuredAngle - targetAngle) / targetAngle);
        System.out.println(rotSpeed);
        DriveSystem.getInstance().arcadeDrive(0, rotSpeed);
    }

    protected double clamp(double val) {
        double abs_val = Math.abs(val);
        double minSpeed = 0.45; //0.4
        if (abs_val < minSpeed) {
            return minSpeed * Math.signum(val);
        } else if (abs_val > 0.5) {
            return 0.5 * Math.signum(val);
        }

        return val;
    }

    @Override
    protected void end() {
        DriveSystem.getInstance().stop();
    }
}
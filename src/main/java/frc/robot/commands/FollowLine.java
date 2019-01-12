package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Photoresistor;

public class FollowLine extends Command {
    Photoresistor pr = Photoresistor.getInstance();
    double turnSpeed = 0.5;
    double moveSpeed = 0.5;
    public FollowLine() {
        super();
        requires(Photoresistor.getInstance());
        requires(DriveSystem.getInstance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if(pr.getLeft() > pr.getMiddle() && pr.getLeft() > pr.getRight()){
            DriveSystem.getInstance().arcadeDrive(moveSpeed, -turnSpeed);
        } else if(pr.getLeft() < pr.getMiddle() && pr.getMiddle() > pr.getRight()){
            DriveSystem.getInstance().arcadeDrive(moveSpeed, 0);
        } else if(pr.getRight() > pr.getMiddle() && pr.getMiddle() > pr.getRight()){
            DriveSystem.getInstance().arcadeDrive(moveSpeed, turnSpeed);
        }
    }

    @Override
    protected void end() {
    }
}
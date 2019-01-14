package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Photoresistor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;


public class FollowLine extends Command {
    double turnSpeed = 0.2;
    double moveSpeed = 0.6;
	int[] threshold = {1000,1000,1000};
	int[] vals;
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
		vals = Photoresistor.getInstance().getVals();
		
		if(vals[0] > vals[1] && vals[0] > vals[2] && vals[0] > threshold[0]){
			 DriveSystem.getInstance().arcadeDrive(moveSpeed, -turnSpeed);
		} else if(vals[1] > vals[0] && vals[1] > vals[2] && vals[1] > threshold[1]){
			DriveSystem.getInstance().arcadeDrive(moveSpeed, 0);
		}else if(vals[2] > vals[0] && vals[2] > vals[1] && vals[2] > threshold[2]){
			DriveSystem.getInstance().arcadeDrive(moveSpeed, turnSpeed);
		}
        /*if(pr.getLeft() > pr.getMiddle() && pr.getLeft() > pr.getRight()){
            DriveSystem.getInstance().arcadeDrive(moveSpeed, -turnSpeed);
        } else if(pr.getLeft() < pr.getMiddle() && pr.getMiddle() > pr.getRight()){
            DriveSystem.getInstance().arcadeDrive(moveSpeed, 0);
        } else if(pr.getRight() > pr.getMiddle() && pr.getMiddle() > pr.getRight()){
            DriveSystem.getInstance().arcadeDrive(moveSpeed, turnSpeed);
        }*/
    }

    @Override
    protected void end() {
    }
}
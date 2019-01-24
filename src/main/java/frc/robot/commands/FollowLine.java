package frc.robot.commands;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Photoresistor;

public class FollowLine extends Command {
    double turnSpeed = 0; // 0.2
    double moveSpeed = 0.4; // 0.6
    boolean[] active = { false, false, false };

    public FollowLine() {
        super();
        requires(DriveSystem.getInstance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void initialize() {
        DriveSystem.getInstance().startAuto();
    }

    @Override
    protected void execute() {
        active = Photoresistor.getInstance().getVals();
        //if(Ultrasonic.getInstance().read() <= 0){
            if (active[0]) {
                turnSpeed = -0.4;
            } else if (active[2]) {
                turnSpeed = 0.4;
            } else {
                turnSpeed = 0.0;
            }
                
         if (active[1]) {
            moveSpeed = 0.4;
          } else {
                moveSpeed = 0.0;
         }

        if (!active[0] && !active[1] && !active[2]) {
            //moveSpeed = 0.4;
            
        }

        DriveSystem.getInstance().arcadeDrive(moveSpeed, turnSpeed);
    }
    //}

    @Override
    protected void end() {
        DriveSystem.getInstance().stop();
    }
}
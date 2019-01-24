package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
//import frc.robot.OI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

public class UpdateSmartDashboard extends Command {
    public UpdateSmartDashboard() {
        super();
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
        SmartDashboard.putBoolean("PR left", Photoresistor.getInstance().getVals()[0]);
        SmartDashboard.putBoolean("PR middle", Photoresistor.getInstance().getVals()[1]);
        SmartDashboard.putBoolean("PR right", Photoresistor.getInstance().getVals()[2]);

        SmartDashboard.putNumber("Max Area 1", Camera.getInstance().getMaxArea1());
        SmartDashboard.putNumber("Max Area 2", Camera.getInstance().getMaxArea2());

        SmartDashboard.putNumber("Angle Degrees", Gyro.getInstance().getDegrees());
        SmartDashboard.putNumber("Total Angle", Gyro.getInstance().getDegreesTotal());

        SmartDashboard.putNumber("Ultrasonic voltage", (Ultrasonic.getInstance().read() / (0.00488 / 5) / 1000));
    }

    @Override
    protected void end() {
    }

}
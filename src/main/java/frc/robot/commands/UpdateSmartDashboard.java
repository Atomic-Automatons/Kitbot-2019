package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

public class UpdateSmartDashboard extends Command {
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

        // SmartDashboard.putNumber("Max Area 1", Camera.getInstance().getMaxArea1());
        // SmartDashboard.putNumber("Max Area 2", Camera.getInstance().getMaxArea2());

       //SmartDashboard.putNumber("Angle Degrees", Gyro.getInstance().getDegrees());
        //SmartDashboard.putNumber("Total Angle", Gyro.getInstance().getDegreesTotal());

        SmartDashboard.putNumber("Hatch Ultrasonic", (UltrasonicHatch.getInstance().getDistance()));
        SmartDashboard.putNumber("Cargo Ultrasonic", (UltrasonicCargo.getInstance().getDistance()));
        SmartDashboard.putNumber("Camera Angle", JeVois.getInstance().getAngle());

        SmartDashboard.putBoolean("Grabber bottom", Cargo.getInstance().isDown());
        SmartDashboard.putBoolean("Grabber top", Cargo.getInstance().isUp());
        SmartDashboard.putBoolean("Hatch Top", HatchControls.getInstance().isUp());
        SmartDashboard.putBoolean("Hatch Bottom", HatchControls.getInstance().isDown());
        
        SmartDashboard.putNumber("NavX_Angle", NavX.getInstance().getAngle());
        SmartDashboard.putBoolean("NavX_Connected", NavX.getInstance().isConnected());
        SmartDashboard.putNumber("NavX_Pitch", NavX.getInstance().getPitch());
        SmartDashboard.putNumber("NavX_Yaw", NavX.getInstance().getYaw());
    }

    @Override
    protected void end() {
    }

}
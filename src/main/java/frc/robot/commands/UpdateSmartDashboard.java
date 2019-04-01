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
        /*
         * SmartDashboard.putBoolean("PR left",
         * Photoresistor.getInstance().getVals()[0]);
         * SmartDashboard.putBoolean("PR middle",
         * Photoresistor.getInstance().getVals()[1]);
         * SmartDashboard.putBoolean("PR right",
         * Photoresistor.getInstance().getVals()[2]);
         */

        SmartDashboard.putBoolean("Grabber bottom", Cargo.getInstance().isDown());
        SmartDashboard.putBoolean("Grabber top", Cargo.getInstance().isUp());

        SmartDashboard.putBoolean("Hatch Top", HatchControls.getInstance().isUp());
        SmartDashboard.putBoolean("Hatch Bottom", HatchControls.getInstance().isDown());

        SmartDashboard.putNumber("NavX_Angle", NavX.getInstance().getAngle());

        SmartDashboard.putBoolean("Cargo Forward", !DriveSystem.getInstance().getInverted());
        SmartDashboard.putBoolean("Hatch Closed", HatchControls.getInstance().isCylinderOpen());
        SmartDashboard.putNumber("Encoder Left", Encoders.getInstance().getLeftDistance());
        SmartDashboard.putNumber("Encoder Right", Encoders.getInstance().getRightDistance());
        // .putBoolean("Line is done", UltrasonicHatch.getInstance().getDistance() <
        // FollowLine.maxDistance);
        // SmartDashboard.putNumber("NavX Distance X",
        // NavX.getInstance().getDisplacementX() * 39.37);
        // SmartDashboard.putNumber("NavX Distance Y",
        // NavX.getInstance().getDisplacementY() * 39.37);
        // SmartDashboard.putNumber("NavX Distance Z",
        // NavX.getInstance().getDisplacementZ() * 39.37);
        updateUltra();
        updateCameras();
    }

    double cameraRange = 0.0;
    double lowerVal = 0;
    double upperVal = 0;

    protected void updateCameras() {
        // SmartDashboard.putNumber("Jevois Hatch Angle",
        // JeVoisHatch.getInstance().getAngle());
        SmartDashboard.putNumber("Jevois Cargo Angle", JeVoisCargo.getInstance().getAngle());
        SmartDashboard.putBoolean("Hatch Aligned", Math.abs(JeVoisCargo.getInstance().getAngle()) < cameraRange);
        SmartDashboard.putBoolean("Hatch Distance", UltrasonicHatch.getInstance().getAverage() > lowerVal
                && UltrasonicHatch.getInstance().getAverage() < upperVal);
        // SmartDashboard.putNumber("JeVois Cargo Sample #",
        // JeVoisCargo.getInstance().getSamples());
        // SmartDashboard.putNumber("JeVois Hatch Sample #",
        // JeVoisHatch.getInstance().getSamples());
    }

    protected void updateUltra() {
        // SmartDashboard.putNumber("Hatch Ultra Raw",
        // UltrasonicHatch.getInstance().read());
        SmartDashboard.putNumber("Hatch Ultra Distance", UltrasonicHatch.getInstance().getDistance());
        SmartDashboard.putNumber("Average hatch ultra", UltrasonicHatch.getInstance().getAverage());
        // SmartDashboard.putNumber("Cargo Ultra Raw",
        // UltrasonicCargo.getInstance().read());
        SmartDashboard.putNumber("Cargo Ultra Distance", UltrasonicCargo.getInstance().getDistance());
    }

    @Override
    protected void end() {
    }
}
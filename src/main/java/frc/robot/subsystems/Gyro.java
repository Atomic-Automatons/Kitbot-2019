package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro extends Subsystem {
    private static Gyro instance = null;

    public static Gyro getInstance() {
        if (instance == null) {
            instance = new Gyro();
        }
        return instance;
    }

    private static ADXRS450_Gyro gyro;

    private Gyro() {
        gyro = new ADXRS450_Gyro();
    }

    public double getDegrees() {
        return gyro.getAngle() % 360;
    }

    public double getDegreesTotal() {
        return gyro.getAngle();
    }

    public void calibrate() {
        gyro.calibrate();

    }

    public boolean isConnected() {
        return gyro.isConnected();
    }

    public void reset() {
        gyro.reset();
    }

    @Override
    public void initDefaultCommand() {

    }
}
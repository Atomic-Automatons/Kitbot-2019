package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotMap;

public class Encoders extends Subsystem {
    private static Encoders instance = null;

    public static Encoders getInstance() {
        if (instance == null)
            instance = new Encoders();

        return instance;
    }

    private Encoder left = new Encoder(RobotMap.leftEncoder[0], RobotMap.leftEncoder[1], false,
            Encoder.EncodingType.k4X);
    private Encoder right = new Encoder(RobotMap.rightEncoder[0], RobotMap.rightEncoder[1], false,
            Encoder.EncodingType.k4X);

    private Encoders() {
        left.setReverseDirection(true);

        double wheelRadius = 3.0;
        double dpp = 3 * (2 * wheelRadius * Math.PI) / 1000; // 3: Gearbox Ratio, 1000: Encoder ppd
        left.setDistancePerPulse(dpp);
        right.setDistancePerPulse(dpp);
    }

    public void reset() {
        left.reset();
        right.reset();
    }

    public double getLeftDistance() {
        return left.getDistance();
    }

    public double getRightDistance() {
        return right.getDistance();
    }

    public double getDistance() {
        return (left.getDistance() + right.getDistance()) / 2;
    }

    @Override
    public void initDefaultCommand() {
    }
}
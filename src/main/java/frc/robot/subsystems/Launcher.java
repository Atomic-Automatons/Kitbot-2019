package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Launcher extends Subsystem {
    static private Launcher instance = null;

    static public Launcher getInstance() {
        if (instance == null) {
            instance = new Launcher();
        }
        return instance;
    }

    private Spark left;
    private Spark right;

    public Launcher() {
        left = new Spark(RobotMap.leftLauncherMotor);
        right = new Spark(RobotMap.rightLauncherMotor);
        // left.setInverted(true);

        left.setSafetyEnabled(false);
        right.setSafetyEnabled(false);
    }

    public void setSpeed(double speed) {
        left.set(speed);
        right.set(speed);
    }

    public void stop() {
        left.set(0);
        right.set(0);
    }

    @Override
    public void initDefaultCommand() {
    }
}
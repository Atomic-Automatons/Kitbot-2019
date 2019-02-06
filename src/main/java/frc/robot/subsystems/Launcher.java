package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
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
    private Spark upDown;
    private DigitalInput lowerSwitch;
    private DigitalInput upperSwitch;

    public Launcher() {
        left = new Spark(RobotMap.leftLauncherMotor);
        right = new Spark(RobotMap.rightLauncherMotor);
        // left.setInverted(true);
        upDown = new Spark(RobotMap.launcherElevator);
        upDown.setInverted(true);
        left.setSafetyEnabled(false);
        right.setSafetyEnabled(false);

        lowerSwitch = new DigitalInput(RobotMap.launcherElevatorSwitchDown);
        upperSwitch = new DigitalInput(RobotMap.launcherElevatorSwitch);
    }

    public void setSpeed(double speed) {
        left.set(speed);
        right.set(speed);
    }

    public void up() {
        setElevatorSpeed(0.4);
    }

    private void setElevatorSpeed(double speed) {
        upDown.set(speed);
    }

    private void freezeElevator() {
        upDown.set(0.1); // TODO: Fix
    }

    public void down() {
        setElevatorSpeed(0.1);
    }

    public void stop() {
        left.set(0);
        right.set(0);
    }

    public void stopElevator(){
        upDown.set(0);
    }

    public boolean readUpSwitch() {
        return !upperSwitch.get();
    }

    public boolean readDownSwitch() {
        return !lowerSwitch.get();
    }

    @Override
    public void initDefaultCommand() {
    }
}
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Cargo extends Subsystem {
    static private Cargo instance = null;

    static public Cargo getInstance() {
        if (instance == null) {
            instance = new Cargo();
        }
        return instance;
    }

    private Spark bottom;
    private Spark top;
    private Spark upDown;
    private DigitalInput lowerSwitch;
    private DigitalInput upperSwitch;
    private Timer timer;
    private float maxTime = 45;// seconds

    private boolean up = false;

    private Cargo() {
        timer = new Timer();
        timer.reset();
        
        bottom = new Spark(RobotMap.bottom);
        top = new Spark(RobotMap.top);

        upDown = new Spark(RobotMap.launcherElevator);
        upDown.setInverted(true);

        bottom.setSafetyEnabled(false);
        bottom.setInverted(true);

        top.setSafetyEnabled(false);

        lowerSwitch = new DigitalInput(RobotMap.cargoSwitchBottom);
        upperSwitch = new DigitalInput(RobotMap.cargoSwitchTop);
    }

    public void setSpeed(double speed) {
        bottom.set(speed);
        top.set(speed);
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void toggleUp() {
        up = !up;
    }

    public void stop() {
        bottom.set(0);
        top.set(0);
    }

    private double goingUpSpeed = 0.6;
    private double stall = 0.35;
    private double goingDownSpeed = 0.12;

    public void periodic() {
        if (timer.get() > maxTime) {
            up = false;
        }

        if (up) {
            if (isUp()) {
                upDown.set(stall);
            } else {
                upDown.set(goingUpSpeed);
            }
        } else {
            if (isDown()) {
                upDown.set(0);
                timer.reset();
            } else {
                upDown.set(goingDownSpeed);
            }
        }
    }

    public boolean isUp() {
        return !upperSwitch.get();
    }

    public boolean isDown() {
        return !lowerSwitch.get();
    }

    @Override
    public void initDefaultCommand() {
    }
}
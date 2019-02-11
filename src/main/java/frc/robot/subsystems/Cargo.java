package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Cargo extends Subsystem {
    static private Cargo instance = null;

    static public Cargo getInstance() {
        if (instance == null) 
            instance = new Cargo();
        
        return instance;
    }

    private Spark bottom = new Spark(RobotMap.bottom);
    private Spark top = new Spark(RobotMap.top);
    private Spark upDown = new Spark(RobotMap.launcherElevator);

    private DigitalInput lowerSwitch = new DigitalInput(RobotMap.cargoSwitchBottom);
    private DigitalInput upperSwitch = new DigitalInput(RobotMap.cargoSwitchTop);

    private Timer timer = new Timer();
    private float maxTime = 45;// seconds

    private boolean up = false;

    private Cargo() {
        timer.reset();

        upDown.setInverted(true);

        bottom.setSafetyEnabled(false);
        // bottom.setInverted(true);

        top.setSafetyEnabled(false);
    }

    public void setSpeed(double speed) {
        bottom.set(speed);
        top.set(speed);
    }

    public void ejectCargo() {
        bottom.set(-1);
        top.set(-1);
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

    private double goingUpSpeed = 0.8;
    private double stall = 0.2; // Find later
    private double goingDownSpeed = -0.6;

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
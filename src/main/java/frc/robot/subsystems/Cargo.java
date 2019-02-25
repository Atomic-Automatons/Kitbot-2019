package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
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

    // private WPI_VictorSPX bottom = new WPI_VictorSPX(RobotMap.cargoBottom);
    private WPI_VictorSPX top = new WPI_VictorSPX(RobotMap.cargoTop);
    private WPI_VictorSPX bottom = new WPI_VictorSPX(RobotMap.cargoBottom);
    private WPI_VictorSPX elevator = new WPI_VictorSPX(RobotMap.launcherElevator);

    private DigitalInput lowerSwitch = new DigitalInput(RobotMap.cargoSwitchBottom);
    private DigitalInput upperSwitch = new DigitalInput(RobotMap.cargoSwitchTop);

    private Timer timer = new Timer();
    private float maxTime = 45;// seconds

    private boolean up = false;

    private Cargo() {
        timer.reset();
        top.setInverted(true);
        bottom.setInverted(true);
        elevator.setInverted(InvertType.InvertMotorOutput);
        elevator.setNeutralMode(NeutralMode.Brake);
        // top.setInverted(InvertType.InvertMotorOutput);

        bottom.setSafetyEnabled(false);
        // bottom.setInverted(true);
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
    private double stall = 0.1; // Find later
    private double goingDownSpeed = -0.6;

    public void periodic() {
        if (timer.get() > maxTime) {
            up = false;
        }

        if (up) {
            if (isUp()) {
                elevator.set(stall);
            } else {
                elevator.set(goingUpSpeed);
            }
        } else {
            if (isDown()) {
                elevator.set(0);
                timer.reset();
            } else {
                elevator.set(goingDownSpeed);
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
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchControls extends Subsystem {
    private static HatchControls instance;

    public WPI_VictorSPX upMotor = new WPI_VictorSPX(RobotMap.upMotor);
    public WPI_VictorSPX leverMotor = new WPI_VictorSPX(RobotMap.leverMotor);;

    private DigitalInput limitSwitchUp = new DigitalInput(RobotMap.hatchSwitchTop);
    private DigitalInput limitSwitchDown = new DigitalInput(RobotMap.hatchSwitchBottom);

    private DoubleSolenoid cylinder = new DoubleSolenoid(RobotMap.hatchPneumaticsForward,
            RobotMap.hatchPneumaticsReverse);

    private boolean up = false;

    private double goingUpSpeed = 0.7;// was .7 1
    private double stall = 0.05;// was 0.2
    private double goingDownSpeed = -0.2; // was .6

    private double maxTime = 45;
    private Timer upTimer = new Timer();

    private Timer leverTimer = new Timer();
    private boolean moveLeverUp = true;
    private double leverTime = 0.6;

    public static HatchControls getInstance() {
        if (instance == null)
            instance = new HatchControls();
        return instance;
    }

    private HatchControls() {
        upMotor.setInverted(true);

        openCylinder();

        upTimer.reset();
    }

    public void closeCylinder() {
        cylinder.set(Value.kReverse);
    }

    public void openCylinder() {
        cylinder.set(Value.kForward);
    }

    public void toggleCylinder() {
        cylinder.set(cylinder.get() == Value.kForward ? Value.kReverse : Value.kForward);
    }

    public void periodic() {
        if (upTimer.get() > maxTime) {
            up = false;
        }

        if (up) {
            if (isUp()) {
                upMotor.set(stall);// Freeze
            } else {
                upMotor.set(goingUpSpeed); // Go up
            }
        } else {
            if (isDown()) {
                upMotor.set(0); // Stop
                upTimer.reset();
            } else {
                upMotor.set(goingDownSpeed); // Go down
            }
        }

        // System.out.println(leverTimer.get());

        double time = leverTimer.get();
        if (time < leverTime && time != 0) {
            if (moveLeverUp) {
                leverMotor.set(0.3);
            } else {
                leverMotor.set(-0.3);
            }
        } else {
            leverTimer.stop();
            leverTimer.reset();
            leverMotor.set(0);
        }
    }

    public boolean isGoingUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void toggleUp() {
        up = !up;
    }

    public boolean isUp() {
        return !limitSwitchUp.get();
    }

    public boolean isDown() {
        return !limitSwitchDown.get();
    }

    public void moveLeverUp() {
        leverTimer.reset();
        leverTimer.start();
        moveLeverUp = true;
    }

    public void moveLeverDown() {
        leverTimer.reset();
        leverTimer.start();
        moveLeverUp = false;
    }

    public void toggleLever() {
        double time = leverTimer.get();
        if (time > leverTime || time != 0) {
            return;
        }

        if (moveLeverUp) {
            moveLeverDown();
        } else {
            moveLeverUp();
        }
    }

    public void initDefaultCommand() {
    }
}
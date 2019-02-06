package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class HatchControls extends Subsystem {
    private static HatchControls instance;

    public Spark upMotor;
    public Spark leverMotor;
    private DigitalInput limitSwitch;
    private DigitalInput limitSwitchDown;
    private DoubleSolenoid boneSnapper;
    private boolean toggle = false;

    public static HatchControls getInstance() {
        if (instance == null)
            instance = new HatchControls();
        return instance;
    }

    private HatchControls() {
        upMotor = new Spark(RobotMap.upMotor);
        leverMotor = new Spark(RobotMap.leverMotor);
        limitSwitch = new DigitalInput(RobotMap.grabberUpSwitch);
        boneSnapper = new DoubleSolenoid(RobotMap.snap, RobotMap.snip);
        limitSwitchDown = new DigitalInput(RobotMap.grabberDownSwitch);
    }

    public void close() {
        boneSnapper.set(Value.kReverse);
    }

    public void open() {
        boneSnapper.set(Value.kForward);
    }

    public void toggle() {
        toggle = !toggle;
    }

    public boolean getToggle() {
        return toggle;
    }

    public void initDefaultCommand() {
    }

    public boolean readSwitch() {
        return !limitSwitch.get();
    }

    public boolean readDownSwitch() {
        return !limitSwitchDown.get();
    }

    public void moveMotorUp() {
        double speed;
        if (toggle) {
            speed = 0.55;
        } else {
            speed = 0.7;
        }
        upMotor.set(speed);
    }

    public void moveMotorUpFAST() {
        upMotor.set(0.7);
    }

    public void moveMotorDown() {
        upMotor.set(0.1);// this one is one too
    }

    public void moveLeverUp() {
        leverMotor.set(0.30);
    }

    public void moveLeverDown() {
        leverMotor.set(-0.3);
    }

    public void chillLeverMotor() {
        leverMotor.set(0.1);
    }

    public void chillUpMotor() {
        upMotor.set(0.35);
    }

    public void stopUp() {
        upMotor.set(0);
    }

    public void stopLever() {
        leverMotor.set(0);
    }
}
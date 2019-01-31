package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchControls extends Subsystem {
    private static HatchControls instance;
    public Spark upMotor;
    public Spark leverMotor;
    public DigitalInput limitSwitch;

    private HatchControls() {
        upMotor = new Spark(RobotMap.upMotor);
        leverMotor = new Spark(RobotMap.leverMotor);
        limitSwitch = new DigitalInput(RobotMap.GrabberSwitch);
    }

    public static HatchControls getInstance() {
        if (instance == null)
            instance = new HatchControls();
        return instance;
    }

    public void initDefaultCommand() {
        // Start Command that moves motor up and in
        moveMotorUp();
        moveLeverOut();
    }

    public boolean readSwitch() {
        return !limitSwitch.get();
    }

    public void moveMotorUp() {
        upMotor.set(0.4);// these values are first guesses
    }

    public void moveMotorDown() {
        upMotor.set(0.0);// this one is one too
    }

    public void moveLeverOut() {
        leverMotor.set(0.1);
    }

    public void moveLeverIn() {
        leverMotor.set(-0.1);
    }

    public void chillLeverMotor() {
        leverMotor.set(0.1);
    }

    public void chillUpMotor() {
        upMotor.set(0.4);
    }

    public void stopUp() {
        upMotor.set(0);
    }

    public void stopLever() {
        leverMotor.set(0);
    }
}
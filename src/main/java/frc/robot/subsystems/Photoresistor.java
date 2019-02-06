package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Photoresistor extends Subsystem {
    private static Photoresistor instance;

    /**
     * getInstance command for Photoresistors. It returns the instance of
     * Photoresistor, and if it has not been called before, it creates a new
     * instance.
     * 
     * @return instance
     */
    public static Photoresistor getInstance() {
        if (instance == null)
            instance = new Photoresistor();
        return instance;
    }

    private AnalogInput[] arrayPhotoresistors = { new AnalogInput(RobotMap.PRPorts[0]),
            new AnalogInput(RobotMap.PRPorts[1]), new AnalogInput(RobotMap.PRPorts[2]) };

    private Photoresistor() {

    }

    public boolean getLeft() {
        return arrayPhotoresistors[0].getVoltage() > 1000;
    }

    public boolean getMiddle() {
        return arrayPhotoresistors[1].getVoltage() > 1000;
    }

    public boolean getRight() {
        return arrayPhotoresistors[2].getVoltage() > 1000;
    }

    public AnalogInput[] getAnalogInputs() {
        return arrayPhotoresistors;
    }

    public boolean[] getVals() {
        return new boolean[] { getLeft(), getMiddle(), getRight() };
    }

    @Override
    protected void initDefaultCommand() {

    }
}
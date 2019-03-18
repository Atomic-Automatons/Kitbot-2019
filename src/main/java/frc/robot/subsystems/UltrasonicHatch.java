package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.devices.AnalogMaxSonar;

public class UltrasonicHatch extends Subsystem {
    private static UltrasonicHatch instance = null;

    public static UltrasonicHatch getInstance() {
        if (instance == null)
            instance = new UltrasonicHatch();
        return instance;
    }

    private AnalogMaxSonar ultra = new AnalogMaxSonar(RobotMap.UltraSonicHatch);
    /** distance millimeter */
    public double getDistance() {
        return ultra.getDistance();
    }

    private UltrasonicHatch() {
    }

    /**
     * read is a method that does stuff.
     * 
     * @return an analog voltage from the ultrasonic analog pin.
     */
    public double read() {
        return ultra.getVoltage();
    }

    @Override
    protected void initDefaultCommand() {

    }

}
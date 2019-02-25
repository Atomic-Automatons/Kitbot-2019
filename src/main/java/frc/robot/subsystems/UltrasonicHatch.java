package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class UltrasonicHatch extends Subsystem {
    private static UltrasonicHatch instance;

    /**
     * getInstance command for Ultrasonic. It returns the instance of Photoresistor,
     * and if it has not been called before, it creates a new instance.
     * 
     * @return instance
     */
    public static UltrasonicHatch getInstance() {
        if (instance == null)
            instance = new UltrasonicHatch();
        return instance;
    }

    public double getDistance() {
        return (read() / (0.00488 / 5) / 1000);
    }

    private static AnalogInput input = new AnalogInput(RobotMap.UltraSonicHatch);

    private UltrasonicHatch() {
    }

    /**
     * read is a method that does stuff.
     * 
     * @return an analog voltage from the ultrasonic analog pin.
     */
    public double read() {
        return input.getVoltage();
    }// hi :)
  

    @Override
    protected void initDefaultCommand() {

    }

}
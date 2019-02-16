package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class UltrasonicCargo extends Subsystem{
    private static UltrasonicCargo instance;

    /**
     * getInstance command for Ultrasonic. It returns the instance of
     * Photoresistor, and if it has not been called before, it creates a new
     * instance.
     * 
     * @return instance
     */
    public static UltrasonicCargo getInstance() {
        if (instance == null)
            instance = new UltrasonicCargo();
        return instance;
    }
    
    public double getDistance() {
    	return (read()/ (0.00488 / 5) / 1000);
    }

   private static AnalogInput input; 
    private UltrasonicCargo() {

        input = new AnalogInput(RobotMap.USPortCargo);
    }
    /**
     * read is a method that does stuff.
     * @return an analog voltage from the ultrasonic analog pin.
     */
    public double read(){
       return input.getVoltage();
    }//hi :)

  

    @Override
    protected void initDefaultCommand() {

    }

}
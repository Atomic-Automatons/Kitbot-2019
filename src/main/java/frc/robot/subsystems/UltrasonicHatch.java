package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class UltrasonicHatch extends Subsystem{
    private static UltrasonicHatch instance;

    /**
     * getInstance command for Ultrasonic. It returns the instance of
     * Photoresistor, and if it has not been called before, it creates a new
     * instance.
     * 
     * @return instance
     */
    public static UltrasonicHatch getInstance() {
        if (instance == null)
            instance = new UltrasonicHatch();
        return instance;
    }
    

   //private static Cereal input;
   private static AnalogInput input; 
    private UltrasonicHatch() {
        input = new AnalogInput(RobotMap.USPortHatch);
    }
   
    public double getDistance(){
        return (read()/ (0.00488 / 5) / 1000);
    }
    public double read(){
        return input.getVoltage();
     }
  

    @Override
    protected void initDefaultCommand() {

    }

}
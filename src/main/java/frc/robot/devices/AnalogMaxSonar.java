package frc.robot.devices;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;

public class AnalogMaxSonar {
    public static final double VOLTAGE_MULTIPLIER = 5 / 4.88; // 4.88 mV per 5 mm, mm/mV
    private AnalogInput sensor = null;

    public AnalogMaxSonar(int port) {
        sensor = new AnalogInput(port);
    }

    /**
     * @return Raw ADC value from 0 to 12 bits
     */
    public int getRaw() {
        
        return sensor.getValue();
    }

    /**
     * 
     * @return Voltage in volts
     */
    public double getVoltage() {
        return sensor.getVoltage();
    }
    /**
     * Averages 5 samples from the ultrasonic
     */
    public double getAverage(){
        /*int samples = 0;
        Timer time = new Timer();
        time.start();
        double voltages = 0;
        time.reset();
        while(samples < 5){
        if(time.get() > 0.2){
            time.reset();
            voltages += getVoltage();
            samples += 1;
        }
        }*/
        return (sensor.getAverageVoltage() * 1000) * VOLTAGE_MULTIPLIER;
    }

    /**
     * 
     * @return Distance in mm
     */
    public double getDistance() {
        return (getVoltage() * 1000) * VOLTAGE_MULTIPLIER; // V to mV, use multiplier
    }
}
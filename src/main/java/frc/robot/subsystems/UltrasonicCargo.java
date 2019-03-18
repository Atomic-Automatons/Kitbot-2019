package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.devices.AnalogMaxSonar;

public class UltrasonicCargo extends Subsystem {
    private static UltrasonicCargo instance;

    public static UltrasonicCargo getInstance() {
        if (instance == null)
            instance = new UltrasonicCargo();
        return instance;
    }

    private AnalogMaxSonar ultra = new AnalogMaxSonar(RobotMap.UltraSonicCargo);

    private UltrasonicCargo() {
    }

    public double read() {
        return ultra.getVoltage();
    }

    public double getDistance() {
        return ultra.getDistance();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
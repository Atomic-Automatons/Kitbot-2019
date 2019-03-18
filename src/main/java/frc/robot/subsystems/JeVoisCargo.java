package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.devices.JeVoisSerial;

public class JeVoisCargo extends Subsystem {
	private static JeVoisCargo instance = null;
	private static JeVoisSerial jeVois;

	public static JeVoisCargo getInstance() {
		if (instance == null)
			instance = new JeVoisCargo();
		return instance;
	}

	private JeVoisCargo() {
		jeVois = new JeVoisSerial(Port.kUSB2);
	}

	public boolean isConnected() {
		return jeVois.isConnected();
	}

	@Override
	public void periodic() {
		jeVois.updateData();
	}

	@Override
	public void initDefaultCommand() {

	}

	public double getAngle() {
		return jeVois.getAngle();
	}

	public double getSize() {
		return jeVois.getSize();
	}

	public int getSamples() {
		return jeVois.getSamples();
	}
}

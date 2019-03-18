package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.devices.JeVoisSerial;

public class JeVoisHatch extends Subsystem {
	private static JeVoisHatch instance = null;
	private static JeVoisSerial jeVois;

	public static JeVoisHatch getInstance() {
		if (instance == null) {
			instance = new JeVoisHatch();
		}
		return instance;
	}

	private JeVoisHatch() {
		jeVois = new JeVoisSerial(Port.kUSB1);
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

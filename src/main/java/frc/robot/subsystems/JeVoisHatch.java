package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
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
		jeVois = new JeVoisSerial(Port.kUSB1, 0);
		CameraStream.getInstance().addDevice(2, jeVois.getCamera());
	}

	public UsbCamera getCamera() {
		return jeVois.getCamera();
	}

	public boolean isConnected() {
		return jeVois.isConnected();
	}

	@Override
	public void periodic() {
		jeVois.updateData();
		//System.out.println(jeVois.getAngle());
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

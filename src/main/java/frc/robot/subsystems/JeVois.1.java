package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

public class JeVois extends Subsystem {
	private static JeVois instance = null;
	private static SerialPort port;

	private boolean connected = false;
	private double angle = 0.0;
	private double size = 0;

	public static JeVois getInstance() {
		if (instance == null) {
			instance = new JeVois();
		}
		return instance;
	}// end method getInstance()

	private JeVois() {
		try {
			for (int i = 0; i != 20; i++) {
				port = new SerialPort(115200, Port.kUSB1);
			}

			if (port != null) {
				this.connected = true;
				port.enableTermination();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		camera.setFPS(30);
		camera.setPixelFormat(PixelFormat.kYUYV);
	}

	/**
	 * @return the connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Proccess and do stuff
	 */
	@Override
	public void periodic() {
		if (!connected) {
			this.angle = 0;
			this.size = 0;
			return;
		}

		double angle = 0;
		double size = 0;
		int samples = 0;

		while (port.getBytesReceived() > 0) {
			String data = port.readString().trim();
			if (data.length() > 0) {
				// System.out.println("data: '" + data + "'");
				if (data.startsWith("TTM")) {
					samples++;
					//System.out.println(data);
					try {
						String[] split = data.split(" ");
						angle += Double.parseDouble(split[1]);
						size += Double.parseDouble(split[2]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (data.startsWith("INF")) {
					// Handle
				} else {
					// System.out.println("data: '" + data + "'");
				}

			} else {
				System.out.println("data: is no bueno, is null");
			}
		}

		if (samples != 0) {
			this.angle = angle / samples;
			this.size = size / samples;
		}
	}

	@Override
	public void initDefaultCommand() {

	}

	public double getAngle() {
		return angle;
	}

	public double getSize() {
		return size;
	}
}

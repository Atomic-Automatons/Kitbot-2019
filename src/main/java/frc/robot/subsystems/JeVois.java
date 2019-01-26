package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;


/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class JeVois extends Subsystem {
	private static JeVois instance = null;
	private static Thread t;
	private static SerialPort port = new SerialPort(9600, SerialPort.Port.kUSB);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static JeVois getInstance() {
		if (instance == null) {
			instance = new JeVois();
		}
		return instance;
	}// end method getInstance()
	
	private JeVois() {
	}
	
	public void startThread() {
		t = new Thread(() -> {
			while (!Thread.interrupted()) {
				if(port.getBytesReceived() > 10) {
					System.out.println(port.getBytesReceived());
				}
			}
		});

		t.start();
	}

	public void stopThread() {
		t.interrupt();
	}


	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}

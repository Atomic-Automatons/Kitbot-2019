package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JeVois extends Subsystem {
	private static JeVois instance = null;
	private static Thread t;
	private static SerialPort port;
	private double angle = 0.0;

	public static JeVois getInstance() {
		if (instance == null) {
			instance = new JeVois();
		}
		return instance;
	}// end method getInstance()

	private JeVois() {
		port = new SerialPort(115200, Port.kUSB);
		port.enableTermination();
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		camera.setFPS(30);
		camera.setPixelFormat(PixelFormat.kYUYV);
		//CameraServer.getInstance().addCamera(camera);
		
	}

	public void startThread() {
		t = new Thread(() -> {
			while (!Thread.interrupted()) {
				// System.out.println("Data: " + port.readString());

				//System.out.println("Wrote: " + port.writeString("ping"));
				//System.out.println("Bytes Received: " + port.getBytesReceived());
				while (port.getBytesReceived() > 2){
					String data = port.readString().trim();

					if (data.length() > 0){
						System.out.println("data: '" + data + "'");
						if(data.startsWith("INF")){
							//Handle
						}else{
							center(data);
						}
					}else {
						System.out.println("data: is no bueno, is null");
					}
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

	}
	public void center(String info){
		try{
			angle = Double.parseDouble(info);
		}catch(Exception e){
			System.out.println("it failed");
			e.printStackTrace();
		}
		
	}

	public double getAngle(){
		return angle;
	}
}

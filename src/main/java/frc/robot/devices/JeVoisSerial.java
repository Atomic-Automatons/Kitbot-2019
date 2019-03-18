package frc.robot.devices;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class JeVoisSerial {
    private static SerialPort port;
    private static UsbCamera camera;
    private static boolean connected = false;

    private double angle = 0.0;
    private double size = 0.0;
    private int samples = 0;

    public JeVoisSerial(Port type, int cameraNumber) {
        try {
            port = new SerialPort(115200, type);
            if (port != null) {
                connected = true;
                port.enableTermination();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        camera = new UsbCamera("Jevois " + cameraNumber, cameraNumber);
        // camera.setResolution(320, 240);
        camera.setFPS(5);
        camera.setPixelFormat(PixelFormat.kMJPEG);
    }

    public boolean isConnected() {
        return connected;
    }

    public UsbCamera getCamera() {
        return camera;
    }

    public void updateData() {
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
            System.out.println(camera.getName() + " " + data);
            if (data.length() > 0) {
                if (data.startsWith("TTM")) {
                    samples++;
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
            System.out.println("Angle: " + angle);
        }
    }

    public double getAngle() {
        return angle;
    }

    public double getSize() {
        return angle;
    }

    public int getSamples() {
        return samples;
    }
}
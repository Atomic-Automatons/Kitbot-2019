package frc.robot.devices;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class JeVoisSerial {
    private Port type;
    private static SerialPort port;
    private static boolean connected = false;

    private double angle = 0.0;
    private double size = 0.0;
    private int samples = 0;

    public JeVoisSerial(Port type) {
        try {
            port = new SerialPort(115200, type);
            if (port != null) {
                connected = true;
                port.enableTermination();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.type = type;
    }

    public boolean isConnected() {
        return connected;
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
            // System.out.println(type.name() + " " + data);
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
            // System.out.println("Angle: " + angle);
        }
    }

    public double getAngle() {
        return angle;
    }

    public double getSize() {
        return size;
    }

    public int getSamples() {
        return samples;
    }
}
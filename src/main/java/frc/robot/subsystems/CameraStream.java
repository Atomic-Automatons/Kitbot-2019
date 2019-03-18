package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraStream extends Subsystem {
    public static final int MAX_SOURCES = 10;
    private static CameraStream instance;
    private int current = 0;
    private VideoSource[] streams = new VideoSource[MAX_SOURCES];
    private MjpegServer server;

    public static CameraStream getInstance() {
        if (instance == null)
            instance = new CameraStream();
        return instance;
    }

    private CameraStream() {
        server = CameraServer.getInstance().addSwitchedCamera("Tape Tracker");
    }

    public MjpegServer getServer() {
        return server;
    }

    public int addDevice(int id, VideoSource source) {
        if (id > streams.length || streams[id] != null) {
            return -1;
        } else {
            streams[id] = source;
            return id;
        }
    }

    public int setActive(int id) {
        if (id > streams.length || streams[id] == null || id == current) {
            return -1;
        } else {
            current = id;
            server.setSource(streams[id]);
            return id;
        }
    }

    @Override
    protected void initDefaultCommand() {

    }
}
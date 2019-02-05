package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

class PCM extends Subsystem {
    private static PCM instance = null;

    /**
     * @return the instance
     */
    public static PCM getInstance() {
        if (instance == null) {
            instance = new PCM();
        }

        return instance;
    }

    private Compressor compressor;

    private PCM() {
        try {
            compressor = new Compressor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initDefaultCommand() {

    }
}
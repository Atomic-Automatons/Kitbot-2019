package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Photoresistor extends Subsystem {
    private static Photoresistor instance;

    /**
     * getInstance command for Photoresistors. It returns the instance of
     * Photoresistor, and if it has not been called before, it creates a new
     * instance.
     * 
     * @return instance
     */
    public static Photoresistor getInstance() {
        if (instance == null)
            instance = new Photoresistor();
        return instance;
    }

    private DigitalInput[] arrayPhotoresistors = { new DigitalInput(1), new DigitalInput(2), new DigitalInput(3) };

    private Photoresistor() {

    }

    public boolean getLeft() {
        return !arrayPhotoresistors[0].get();
    }

    public boolean getMiddle() {
        return !arrayPhotoresistors[1].get();
    }

    public boolean getRight() {
        return arrayPhotoresistors[2].get();
    }

    public DigitalInput[] getDigitalInputs() {
        return arrayPhotoresistors;
    }

    public boolean[] getVals() {
        return new boolean[] { !arrayPhotoresistors[0].get(), !arrayPhotoresistors[1].get(),
                !arrayPhotoresistors[2].get() };
    }

    @Override
    protected void initDefaultCommand() {

    }
}
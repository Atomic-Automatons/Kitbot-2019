package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Photoresistor extends Subsystem {

    private static Photoresistor instance;
    private static AnalogInput[] arrayPhotoresistors = { new AnalogInput(1), new AnalogInput(2), new AnalogInput(3) };

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

    public int getLeft() {
        return arrayPhotoresistors[0].getValue();
    }

    public int getMiddle() {
        return arrayPhotoresistors[1].getValue();
    }

    public int getRight() {
        return arrayPhotoresistors[2].getValue();
    }

    public AnalogInput[] getAnalogInputs(){
        return arrayPhotoresistors;
    } 
	public int[] getVals(){
		return new int[] {arrayPhotoresistors[0].getValue(), arrayPhotoresistors[1].getValue(), arrayPhotoresistors[2].getValue()};
	}
    private Photoresistor() {
    }

    @Override
    public void initDefaultCommand() {
    }
}
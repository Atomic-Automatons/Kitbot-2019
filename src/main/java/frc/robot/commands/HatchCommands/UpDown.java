package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchControls;

public class UpDown extends Command {
	/**
	 * Add your docs here.
	 */
    static boolean fast;
	public UpDown(boolean fast) {
        super();
        this.fast = fast;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called once when the command executes
	@Override
	protected void initialize() {
        
    }
    @Override 
    protected void execute(){
        if(HatchControls.getInstance().readDownSwitch() && fast){
            HatchControls.getInstance().moveMotorUpFAST();
        }else if(HatchControls.getInstance().readDownSwitch()&&!fast){
            HatchControls.getInstance().moveMotorUp();
        }else if(HatchControls.getInstance().readSwitch()){
            HatchControls.getInstance().moveMotorDown();
        }
    }
    protected void end(){
        if(HatchControls.getInstance().readSwitch()){
            HatchControls.getInstance().chillUpMotor();
        }else if(HatchControls.getInstance().readDownSwitch()){
            HatchControls.getInstance().stopUp();
        }
    }
    protected boolean isFinished(){
        return false;
    }

}
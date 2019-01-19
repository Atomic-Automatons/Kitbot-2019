package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
//import frc.robot.OI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

public class UpdateSmartDashboard extends Command {
    public UpdateSmartDashboard() {
        super();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("PR left", Photoresistor.getInstance().getVals()[0]);
        SmartDashboard.putNumber("PR middle", Photoresistor.getInstance().getVals()[1]);
        SmartDashboard.putNumber("PR right", Photoresistor.getInstance().getVals()[2]);
    }

    @Override
    protected void end() {
    }

}
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSystem;

public class ShiftUp extends InstantCommand {
    public ShiftUp(){
        super();
    }
    @Override
    protected void initialize(){
        DriveSystem.getInstance().shiftDown();
    }
}
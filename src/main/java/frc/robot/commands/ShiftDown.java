package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSystem;

public class ShiftDown extends InstantCommand {
    public ShiftDown(){
        super();
    }
    @Override
    protected void initialize(){
        DriveSystem.getInstance().shiftDown();
    }
}
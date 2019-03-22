package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSystem;

public class StopDrive extends InstantCommand{
    public StopDrive(){
        DriveSystem.getInstance().stop();
    }
}
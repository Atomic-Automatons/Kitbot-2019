package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.ShiftDown;
import frc.robot.commands.ToggleHatchLever;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.HatchControls;

public class AutoStart extends CommandGroup {
    public AutoStart() {
        addSequential(new ShiftUp());
        addSequential(new DriveStraight(20, 0.7));
    }

    protected void initialize() {
        DriveSystem.getInstance().disableInverted();
        HatchControls.getInstance().moveLeverDown();
    }

}
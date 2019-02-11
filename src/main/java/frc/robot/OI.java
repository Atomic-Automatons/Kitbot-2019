package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

public class OI {
	static int joystickPort = 0;
	public static int joystickSensitivityAxis = 3;
	public static Joystick stick = new Joystick(joystickPort);

	// Drivebase
	public static Button shiftUp = new JoystickButton(stick, 5);
	public static Button shiftDown = new JoystickButton(stick, 6);
	public static Button invertDriveBase = new JoystickButton(stick, 8);

	// Cargo
	public static Button inhale = new JoystickButton(stick, 9);
	public static Button exhale = new JoystickButton(stick, 7);
	public static Button cargoElevator = new JoystickButton(stick, 11);

	// Autonomous
	public static Button followLine = new JoystickButton(stick, 10);
	public static Button followCamera = new JoystickButton(stick, 12);
	/* public static Button beyblade = new JoystickButton(stick, 8); */

	// Hatch
	public static Button toggleHatchCylinder = new JoystickButton(stick, 1);
	public static Button toggleLever = new JoystickButton(stick, 2);
	public static Button toggleHatch = new JoystickButton(stick, 3);

	static {
		// Drivebase
		shiftUp.whenPressed(new ShiftUp());
		shiftDown.whenPressed(new ShiftDown());
		invertDriveBase.whenPressed(new ToggleInvertDriveSystem());

		// Cargo
		inhale.whileHeld(new CargoInhale());
		exhale.whileHeld(new EjectCargo());
		cargoElevator.whenPressed(new ToggleCargoElevator());

		// Autonomous
		followLine.whileHeld(new FollowLine());
		followCamera.whileHeld(new CameraTurn());
		/* beyblade.whileHeld(new Beyblade()); */

		// Hatch
		toggleHatchCylinder.whenPressed(new ToggleGrabber());
		toggleLever.whenPressed(new ToggleHatchLever());
		toggleHatch.whenPressed(new ToggleHatchElevator());

	}
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}

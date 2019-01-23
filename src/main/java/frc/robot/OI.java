package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

import frc.robot.commands.*;

public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	static int joystickPort = 0;
	public static int joystickSensitivityAxis = 3;
	public static Joystick stick = new Joystick(joystickPort);

	public static Button up = new JoystickButton(stick, 5);
	public static Button down = new JoystickButton(stick, 6);
	public static Button inhale = new JoystickButton(stick, 9);
	public static Button exhale = new JoystickButton(stick, 7);
	public static Button followLine = new JoystickButton(stick, 11);
	static {
		up.whenPressed(new ShiftUp());
		down.whenPressed(new ShiftDown());
		inhale.whileHeld(new Inhale());
		exhale.whileHeld(new Exhale());
		followLine.whenPressed(new FollowLine());
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

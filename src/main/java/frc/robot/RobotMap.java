package frc.robot;

public class RobotMap {
  // left motor ports
  public static int backLeftMotor = 9;
  public static int frontLeftMotor = 8;// 98 76

  // right motor ports
  public static int backRightMotor = 7;
  public static int frontRightMotor = 6;

  // Launcher Motor Ports
  public static int leftLauncherMotor = 3;
  public static int rightLauncherMotor = 4;
  public static int launcherElevator = 2;

  // Hatch Motor Ports
  public static int upMotor = 0;
  public static int leverMotor = 1;

  // analog ports
  public static int USPort = 3;
  public static int[] PRPorts = { 0, 1, 2 };

  // digital ports
  public static int launcherElevatorSwitch = 6;
  public static int launcherElevatorSwitchDown = 7;
  public static int grabberUpSwitch = 4;
  public static int grabberDownSwitch = 5;
  /** stored in form: [0]=A, [1]=B */
  public static int[] leftEncoder = { 0, 1 };
  /** stored in form: [0]=A, [1]=B */
  public static int[] rightEncoder = { 2, 3 };

  // P-numatics
  public static int driveGearForward = 0;
  public static int driveGearBackward = 1;
  /** hatch Grabber forward */
  public static int snap = 2;
  /** hatch Grabber reverse */
  public static int snip = 3;
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
package frc.robot;

public class RobotMap {
  // left motor ports
  public static int backLeftMotor = 9;
  public static int frontLeftMotor = 8;//98 76

  // right motor ports
  public static int backRightMotor = 7;
  public static int frontRightMotor = 6;

  // encoders A,B
  public static int[] leftEncoder = { 0, 1 };
  public static int[] rightEncoder = { 2, 3 };

  // Launcher Motor Ports
  public static int leftLauncherMotor = 4;
  public static int rightLauncherMotor = 5;

  //analog portss
  public static int USPort = 0;

  //digital ports
  public static int GrabberSwitch = 9;
  public static int DownSwitch = 8;
  public static int[] PRPorts = {1,2,3};

  //Grabber thingy motor ports(hatch)
 public static int upMotor = 0;
 public static int leverMotor = 1;

 //P-numatics
 public static int driveGearForward = 0;
 public static int driveGearBackward = 1;
 /**hatch Grabber forward */
 public static int snap = 2;
 /**hatch Grabber reverse */
 public static int snip = 3;
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
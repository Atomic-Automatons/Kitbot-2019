package frc.robot;

public class RobotMap {
  // left motor ports
  public static int backLeftMotor = 0;
  public static int frontLeftMotor = 2;

  // right motor ports
  public static int backRightMotor = 1;
  public static int frontRightMotor = 3;

  // encoders A,B
  public static int[] leftEncoder = { 0, 1 };
  public static int[] rightEncoder = { 2, 3 };

  // Launcher Motor Ports
  public static int leftLauncherMotor = 4;
  public static int rightLauncherMotor = 5;

  //analog portss
  public static int USPort = 0;

  //digital ports
  public static int GrabberSwitch = 0;
  public static int[] PRPorts = {1,2,3};

  //Grabber thingy motor ports(hatch)
 public static int upMotor = 6;
 public static int leverMotor = 7;


  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
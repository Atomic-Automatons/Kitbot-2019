package frc.robot;

public class RobotMap {
  // Drivebase
    // Left motor ports
    public static int backLeftMotor = 7;
    public static int frontLeftMotor = 6;// 98 76
    
    // Right motor ports 
    public static int backRightMotor = 9;
    public static int frontRightMotor = 8;

  // Launcher Motor Ports
  public static int cargoBottom = 3;
  public static int cargoTop = 4;
  public static int launcherElevator = 1;

  // Hatch Motor Ports
  public static int upMotor = 0;
  public static int leverMotor = 2;

  // Analog ports
  public static int UltraSonicHatch = 1;
  public static int UltraSonicCargo = 0;
  public static int[] PRPorts = { 12, 10, 11 }; //MXP 0, 1 ,2

  // Digital ports
    // Limit Switches
    public static int cargoSwitchTop = 6;
    public static int cargoSwitchBottom = 18; //MXP 4
    public static int hatchSwitchTop = 4;
    public static int hatchSwitchBottom = 5;

    // Sensors
    /** estored in form: [0]=A, [1]=B */
    public static int[] leftEncoder = { 0, 1 };
    /** stored in form: [0]=A, [1]=B */
    public static int[] rightEncoder = { 2, 3 };

  // Pneumatics
  public static int driveGearForward = 0;
  public static int driveGearBackward = 1;
  public static int hatchPneumaticsForward = 2;
  public static int hatchPneumaticsReverse = 3;
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class NavX extends Subsystem {
	private static NavX instance = null;
	AHRS ahrs = new AHRS(SPI.Port.kMXP);

	public static NavX getInstance() {
		if (instance == null) {
			instance = new NavX();
		}
		return instance;
	}// end method getInstance()

	private NavX() {
	}

	public boolean isConnected() {
		return ahrs.isConnected();
	}

	// Reset the Yaw gyro.
	public void reset() {
		ahrs.reset();
	}

	// Zeros the displacement integration variables.
	public void resetDisplacement() {
		ahrs.resetDisplacement();
	}

	// Returns the total accumulated yaw angle (Z Axis, in degrees) reported by the
	// sensor.
	public double getAngle() {
		return ahrs.getAngle() % 360;
	}

	public double getDegrees() {
		return ahrs.getAngle() % 360;
	}

	// Returns the current yaw value (in degrees, from -180 to 180) reported by the
	// sensor.
	public double getYaw() {
		return ahrs.getYaw();
	}

	// Returns the current pitch value (in degrees, from -180 to 180) reported by
	// the sensor.
	public double getPitch() {
		return ahrs.getPitch();
	}

	// Returns the current pitch value (in degrees, from -180 to 180) reported by
	// the sensor.
	public double getRoll() {
		return ahrs.getPitch();
	}

	/*
	 * //Returns the current tilt-compensated compass heading value (in degrees,
	 * from 0 to 360) //reported by the sensor.
	 */
	public double getCompassHeading() {
		return ahrs.getCompassHeading();
	}

	// Return the rate of rotation of the yaw (Z-axis) gyro, in degrees per second.
	public double getRate() {
		return ahrs.getCompassHeading();
	}

	// Returns the current linear acceleration in the X-axis (in G).
	public double getWorldLinearAccelX() {
		return ahrs.getWorldLinearAccelX();
	}

	// Returns the current linear acceleration in the Y-axis (in G).
	public double getWorldLinearAccelY() {
		return ahrs.getWorldLinearAccelY();
	}

	// Indicates if the sensor is currently detecting motion.
	public boolean isMoving() {
		return ahrs.isMoving();
	}

	// Indicates if the sensor is currently detecting yaw rotation.
	public boolean isRotating() {
		return ahrs.isRotating();
	}

	// Returns the velocity (in meters/sec) of the X axis
	public double getVelocityX() {
		return ahrs.getVelocityX();
	}

	// Returns the velocity (in meters/sec) of the y axis
	public double getVelocityY() {
		return ahrs.getVelocityY();
	}

	// Returns the displacement (in meters) of the Z axis since resetDisplacement()
	// was last invoked
	public double getDisplacementZ() {
		return ahrs.getDisplacementZ();
	}

	// Returns the displacement (in meters) of the X axis since resetDisplacement()
	// was last invoked
	public double getDisplacementX() {
		return ahrs.getDisplacementX();
	}

	// Returns the displacement (in meters) of the Y axis since resetDisplacement()
	// was last invoked
	public double getDisplacementY() {
		return ahrs.getDisplacementY();
	}

	// Returns the current temperature (in degrees centigrade) reported by the
	// sensor's gyro/accelerometer circuit.

	public double getTempC() {
		return ahrs.getTempC();
	}

	// Raw data methods:

	// Returns the current raw (unprocessed) X-axis acceleration rate (in G).
	public double getRawAccelX() {
		return ahrs.getRawAccelX();
	}

	// Returns the current raw (unprocessed) Y-axis acceleration rate (in G).
	public double getRawAccelY() {
		return ahrs.getRawAccelY();
	}

	// Returns the current raw (unprocessed) Z-axis acceleration rate (in G).
	public double getRawAccelZ() {
		return ahrs.getRawAccelZ();
	}

	// Returns the current raw (unprocessed) X-axis gyro rotation rate (in
	// degrees/sec).
	public double getRawGyroX() {
		return ahrs.getRawGyroX();
	}

	// Returns the current raw (unprocessed) Y-axis gyro rotation rate (in
	// degrees/sec).
	public double getRawGyroY() {
		return ahrs.getRawGyroY();
	}

	// Returns the current raw (unprocessed) Z-axis gyro rotation rate (in
	// degrees/sec).
	public double getRawGyroZ() {
		return ahrs.getRawGyroZ();
	}

	@Override
	protected void initDefaultCommand() {

	}
}
package frc.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.cameraserver.CameraServer;

public class Camera extends Subsystem {
	private static Camera instance = null;

	/**
	 * This getInstance makes it so there can only be one Camera at a time. This is
	 * done for multiple reasons, but i'll just say it's to keep processing power
	 * down i guess.
	 */
	public static Camera getInstance() {
		if (instance == null) {
			instance = new Camera();
		}
		return instance;
	}// end method getInstance()

	private final static int RESOLUTION_WIDTH = 160;
	private final static int RESOLUTION_HEIGHT = 120;
	private final static int FRAMERATE = 30; // 5 7 30

	private static UsbCamera camera;
	private static Thread t;
	private static CvSource imageSource = CameraServer.getInstance().putVideo("The Seeker of Truth", RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
	private static CvSink imageSink = new CvSink("Follow Sink");

	private static double contourMinArea = 500.0;

	/**
	 * This basically makes a camera and should only run once(first time you
	 * getInstance())
	 */
	private Camera() {
		camera = new UsbCamera("Camera", 0);
		camera.setResolution(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
		camera.setFPS(FRAMERATE);
		imageSink.setSource(camera);
	}

	public void startThread() {
		t = new Thread(() -> {
			while (!Thread.interrupted()) {
				process();
			}
		});

		t.start();
	}

	public void stopThread() {
		t.interrupt();
	}

	@Override
	public void initDefaultCommand() {
	}

	Mat input_image = new Mat();
	Mat output_image = new Mat();
	Mat empty = new Mat();
	Point emptyPoint = new Point(-1, -1);
	private ArrayList<MatOfPoint> contours = new ArrayList<>();

	ArrayList<MatOfPoint> contoursFinal = new ArrayList<>();
	int mode = Imgproc.RETR_LIST;
	int method = Imgproc.CHAIN_APPROX_SIMPLE;
	double[] hue = { 16.18705035971223, 26.41638225255974 };
	double[] sat = { 89.43345323741006, 255.0 };
	double[] val = { 82.55395683453237, 255.0 };
	Scalar scalar1 = new Scalar(hue[0], sat[0], val[0]);
	Scalar scalar2 = new Scalar(hue[1], sat[1], val[1]);
	double filterContoursMinPerimeter = 53.0;
	double filterContoursMinWidth = 0;
	double filterContoursMaxWidth = 1000;
	double filterContoursMinHeight = 0;
	double filterContoursMaxHeight = 1000;
	double[] filterContoursSolidity = { 0, 100 };
	double filterContoursMaxVertices = 1000000;
	double filterContoursMinVertices = 0.0;
	double filterContoursMinRatio = 0;
	double filterContoursMaxRatio = 1000;
	final MatOfInt hull = new MatOfInt();

	double maxFinalArea1 = 0;
	double maxFinalArea2 = 0;
	double mid1 = 0;
	double mid2 = 0;
	int index1 = 0;
	int index2 = 0;
	Rect bb = new Rect();
	Rect bb1 = new Rect();
	int valid = 0;

	public void process() {
		if (imageSink.grabFrame(input_image) != 0) {
			// HSV
			Imgproc.cvtColor(input_image, output_image, Imgproc.COLOR_BGR2HSV);
			Core.inRange(output_image, scalar1, scalar2, output_image);

			// Contour
			contours.clear();
			Imgproc.findContours(output_image, contours, empty, mode, method);
			imageSource.putFrame(output_image);
			// Imgproc.drawContours(output_image, contours, -1, colors);
			// Filter Contours
			valid = 0;
			index1 = 0;
			index2 = 0;
			maxFinalArea1 = 0;
			maxFinalArea2 = 0;
			bb = new Rect(0, 0, RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
			bb1 = new Rect(0, 0, RESOLUTION_WIDTH, RESOLUTION_HEIGHT);

			for (int i = 0; i < contours.size(); i++) {
				final MatOfPoint contour = contours.get(i);
				final double area = Imgproc.contourArea(contour);
				if (area > contourMinArea || true) { // && Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) <
													// filterContoursMinPerimeter
					if (area > maxFinalArea1) {
						index2 = index1;
						index1 = i;
						maxFinalArea2 = maxFinalArea1;
						maxFinalArea1 = area;
					} else if (area > maxFinalArea2) {
						index2 = i;
						maxFinalArea2 = area;
					}
					valid++;
				}
			}

			if (contours.size() > 0) {
				bb = Imgproc.boundingRect(contours.get(index1));
				bb1 = Imgproc.boundingRect(contours.get(index2));
				System.out.println(maxFinalArea1);
			}
		}
	}

	public int getContourNum() {
		return contours.size();
	}

	public double getMaxArea1() {
		return maxFinalArea1;
	}

	public double getMaxArea2() {
		return maxFinalArea2;
	}

	public double getCenterbb1() {
		return bb.x + bb.width / 2;
	}

	public double getCenterbb2() {
		return bb1.x + bb1.width / 2;
	}

	public double getCenterRaw() {
		return ((bb1.x + bb1.width / 2) + (bb.x + bb.width / 2)) / 2;
	}

	public double getCenter() {
		return (getCenterRaw() / RESOLUTION_WIDTH) - 0.5;
	}
}

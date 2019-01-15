package frc.robot.subsystems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	static int resWidth = 640;
	static int resHeight = 360;
	static UsbCamera camera;
	static private Camera instance = null;
	static CvSink imageSink = new CvSink("CV Image Grabber");
	static CvSource imageSource = new CvSource("CV Image Source", VideoMode.PixelFormat.kMJPEG, 640, 480, 30);
	// static MjpegServer cvStream = new MjpegServer("CV Image Stream", 1186);

	/**
	 * This getInstance makes it so there can only be one Camera at a time. This is
	 * done for multiple reasons, but i'll just say it's to keep processing power
	 * down i guess.
	 */
	static public Camera getInstance() {
		if (instance == null) {
			instance = new Camera();
		}
		return instance;
	}

	/**
	 * This basically makes a camera and should only run once(first time you
	 * getInstance())
	 */
	private Camera() {

		camera = new UsbCamera("Camera", 0);
		camera.setResolution(resWidth, resHeight);
		camera.setFPS(7);
		imageSink.setSource(camera);
		// cvStream.setSource(imageSource);
		// Loads our OpenCV library. This MUST be included
		// System.loadLibrary("opencv_java310");
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	double[] hue = { 0.0, 77 };
	double[] sat = { 119, 255.0 };
	double[] val = { 96, 255};
	int erode1Iterations = 6;
	int dilateIterations = 25;
	int erode2Iterations = 9;
	double findBlobsMinArea = 1.0;
	double[] findBlobsCircularity = { 0.0, 1.0 };
	boolean findBlobsDarkBlobs = false;
	MatOfKeyPoint findBlobsOutput = new MatOfKeyPoint();
	Mat resizeInput = new Mat();
	Mat empty = new Mat();
	Point emptyPoint = new Point(-1, -1);


	public MatOfKeyPoint process() {
		String return_value;
		
		if (imageSink.grabFrame(resizeInput) != 0) {
			Imgproc.resize(resizeInput, resizeInput, new Size(resWidth, resHeight), 0.0, 0.0, Imgproc.INTER_CUBIC);

			// RGB Threshold

			Imgproc.cvtColor(resizeInput, resizeInput, Imgproc.COLOR_BGR2HSV);
			Core.inRange(resizeInput, new Scalar(hue[0], sat[0], val[0]), new Scalar(hue[1], sat[1], val[1]),
					resizeInput);

			// CV Erode

			erode(resizeInput, resizeInput, erode1Iterations);
			// CV Dilate

			Imgproc.dilate(resizeInput, resizeInput, empty, emptyPoint, dilateIterations,
					Core.BORDER_CONSTANT, new Scalar(-1));
			// CV Erode2

			erode(resizeInput, resizeInput, erode2Iterations);
			// Find Blobs

			findBlobs(resizeInput, findBlobsMinArea, findBlobsCircularity, findBlobsDarkBlobs, findBlobsOutput);
		}
		return findBlobsOutput;
	}

	/**
	 * This exists just because i used erode twice and didn't want to type it twice
	 * 
	 * @param src
	 * @param output
	 * @param iterations
	 */
	private void erode(Mat src, Mat output, int iterations) {
		Imgproc.erode(src, output, empty, emptyPoint, iterations, Core.BORDER_CONSTANT, new Scalar(-1));
	}

	/**
	 * MADE BY GRIP
	 * 
	 * @param input
	 * @param minArea
	 * @param circularity
	 * @param darkBlobs
	 * @param blobList
	 */
	private void findBlobs(Mat input, double minArea, double[] circularity, Boolean darkBlobs, MatOfKeyPoint blobList) {
		FeatureDetector blobDet = FeatureDetector.create(FeatureDetector.SIMPLEBLOB);
		try {
			File tempFile = File.createTempFile("config", ".xml");

			StringBuilder config = new StringBuilder();

			config.append("<?xml version=\"1.0\"?>\n");
			config.append("<opencv_storage>\n");
			config.append("<thresholdStep>10.</thresholdStep>\n");
			config.append("<minThreshold>50.</minThreshold>\n");
			config.append("<maxThreshold>220.</maxThreshold>\n");
			config.append("<minRepeatability>2</minRepeatability>\n");
			config.append("<minDistBetweenBlobs>10.</minDistBetweenBlobs>\n");
			config.append("<filterByColor>1</filterByColor>\n");
			config.append("<blobColor>");
			config.append((darkBlobs ? 0 : 255));
			config.append("</blobColor>\n");
			config.append("<filterByArea>1</filterByArea>\n");
			config.append("<minArea>");
			config.append(minArea);
			config.append("</minArea>\n");
			config.append("<maxArea>");
			config.append(Integer.MAX_VALUE);
			config.append("</maxArea>\n");
			config.append("<filterByCircularity>1</filterByCircularity>\n");
			config.append("<minCircularity>");
			config.append(circularity[0]);
			config.append("</minCircularity>\n");
			config.append("<maxCircularity>");
			config.append(circularity[1]);
			config.append("</maxCircularity>\n");
			config.append("<filterByInertia>1</filterByInertia>\n");
			config.append("<minInertiaRatio>0.1</minInertiaRatio>\n");
			config.append("<maxInertiaRatio>" + Integer.MAX_VALUE + "</maxInertiaRatio>\n");
			config.append("<filterByConvexity>1</filterByConvexity>\n");
			config.append("<minConvexity>0.95</minConvexity>\n");
			config.append("<maxConvexity>" + Integer.MAX_VALUE + "</maxConvexity>\n");
			config.append("</opencv_storage>\n");
			FileWriter writer;
			writer = new FileWriter(tempFile, false);
			writer.write(config.toString());
			writer.close();
			blobDet.read(tempFile.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		blobDet.detect(input, blobList);
	}

	public static String center(MatOfKeyPoint blobsMat) {
		List<KeyPoint> list = blobsMat.toList();
		int largest = 0;// index of largest blob
		int secondLargest = 0;
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).size > list.get(largest).size) {
					secondLargest = largest;
					largest = i;
				}
			}
		} else {
			return "No Blob";// No point found
		}

		largest = (int) list.get(largest).pt.x;
		secondLargest = (int) list.get(secondLargest).pt.x;

		if ((largest + secondLargest) / 2 >= resWidth / 2) {
			return "-> -> TURN RIGHT -> ->";
		} else if ((largest + secondLargest) / 2 <= resWidth / 2) {
			return "<- <- TURN LEFT <- <-";
		} else {
			return "OH NOES Something went wrong";
		}
	}
}

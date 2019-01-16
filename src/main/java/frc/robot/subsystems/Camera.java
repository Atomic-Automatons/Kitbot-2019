package frc.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.networktables.*;

public class Camera extends Subsystem {
	static int resWidth = 320;
	static int resHeight = 240;
	static UsbCamera camera;
	static private Camera instance = null;
	static CvSink imageSink = new CvSink("CV Image Grabber");
	static CvSource imageSource = new CvSource("CV Image Source", VideoMode.PixelFormat.kMJPEG, resWidth, resHeight,
			15);
	static CvSource imageSourceb = new CvSource("CV Image Source blue", VideoMode.PixelFormat.kMJPEG, resWidth, resHeight, 15);
	static  MjpegServer cvStreamb = new MjpegServer("CV Image Stream blue", 1186);
	//static MjpegServer cvStream = new MjpegServer("CV Image Stream", 1186);
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
	}// end method getInstance()

	/**
	 * This basically makes a camera and should only run once(first time you
	 * getInstance())
	 */
	private Camera() {

		camera = new UsbCamera("Camera", 0);
		camera.setResolution(resWidth, resHeight);
		imageSink.setSource(camera);

		cvStreamb.setSource(imageSourceb);
		//cvStreamb.setSource(camera);
		NetworkTable.getTable("CameraPublisher").getSubTable("OpenCV Blue").putStringArray("streams", new String[]{"mjpeg:http://" + "10.66.44.41" + ":" + 1186 + "/stream.mjpg"});
	
		// cvStream.setSource(imageSource);
	}

	@Override
	public void initDefaultCommand() {
	}

	Mat input_image = new Mat();
	Mat output_image = new Mat();
	Mat empty = new Mat();
	Point emptyPoint = new Point(-1, -1);
	ArrayList<MatOfPoint> contours = new ArrayList<>();
	
	ArrayList<MatOfPoint> contoursFinal = new ArrayList<>();
	int mode = Imgproc.RETR_LIST;
	int method = Imgproc.CHAIN_APPROX_SIMPLE;
	double[] hue = { 16.18705035971223, 26.41638225255974 };
	double[] sat = { 89.43345323741006, 255.0 };
	double[] val = { 82.55395683453237, 255.0 };
	Scalar scalar1 = new Scalar(hue[0], sat[0], val[0]);
	Scalar scalar2 = new Scalar(hue[1], sat[1], val[1]);
	double filterContoursMinArea = 20.0;
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
	double maxFinalArea1=0;
	double maxFinalArea2=0;
	double mid1 = 0;
	double mid2 = 0;
	public void process() {
		if (imageSink.grabFrame(input_image) != 0) {
			// HSV
			
			Imgproc.cvtColor(input_image, output_image, Imgproc.COLOR_BGR2HSV);
			Core.inRange(output_image, scalar1, scalar2, output_image);
			

			// Contour
			contours.clear();
			Imgproc.findContours(output_image, contours, empty, mode, method);
			imageSourceb.putFrame(output_image);
			// Filter Contours
			maxFinalArea1 = 0;
			maxFinalArea2 = 0;
			for(int i = 0; i<contours.size(); i++){
				final MatOfPoint contour = contours.get(i);
				final double area = Imgproc.contourArea(contour);
				if(area > 20 && Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) < filterContoursMinPerimeter){
					final Rect bb = Imgproc.boundingRect(contour);
				}
			}
			for(int i = 0 ; i < contoursFinal.size(); i++){
				
				
			}
			/*for (int i = 0; i < contours.size(); i++) {
				final MatOfPoint contour = contours.get(i);
				final Rect bb = Imgproc.boundingRect(contour);
				if (bb.width < filterContoursMinWidth || bb.width > filterContoursMaxWidth)
					continue;
				if (bb.height < filterContoursMinHeight || bb.height > filterContoursMaxHeight)
					continue;
				final double area = Imgproc.contourArea(contour);
				if (area < filterContoursMinArea)
					continue;
				if (Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) < filterContoursMinPerimeter)
					continue;
				Imgproc.convexHull(contour, hull);
				MatOfPoint mopHull = new MatOfPoint();
				mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
				for (int j = 0; j < hull.size().height; j++) {
					int index = (int) hull.get(j, 0)[0];
					double[] point = new double[] { contour.get(index, 0)[0], contour.get(index, 0)[1] };
					mopHull.put(j, 0, point);
				}
				final double solid = 100 * area / Imgproc.contourArea(mopHull);
				if (solid < filterContoursSolidity[0] || solid > filterContoursSolidity[1])
					continue;
				if (contour.rows() < filterContoursMinVertices || contour.rows() > filterContoursMaxVertices)
					continue;
				final double ratio = bb.width / (double) bb.height;
				if (ratio < filterContoursMinRatio || ratio > filterContoursMaxRatio)
					continue;
				contours.add(contour);
			}*/
		}
	}

}

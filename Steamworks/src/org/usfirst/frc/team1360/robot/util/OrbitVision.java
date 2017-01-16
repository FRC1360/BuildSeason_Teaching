package org.usfirst.frc.team1360.robot.util;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.*;
import org.opencv.videoio.*;


import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;

public class OrbitVision 
{
	private int lowThreshold = 125;
	Mat src = Imgcodecs.imread("Filtered_Goal.png");
	VideoCapture input = new VideoCapture("http://10.13.60.3/mjpg/video.mjpg");
	Imshow im = new Imshow("LOL");

	
	//HSV values
	private double HSub = 170;
	private double HAdd = 255;
	private double SSub = 235;
	private double SAdd = 255;
	private double VSub = 29;
	private double VAdd = 222;
	
	public OrbitVision()
	{
		
	}
	
	//Put this in the robot init
	public void CreateCamera()
	{
		new Thread(() ->
		{
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(680, 480);
			
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 680, 480);
			
			Mat source = new Mat();
			Mat output = new Mat();
			
			//If this doesnt work, try while(!Thread.interuppted)
			while(true)
			{
				cvSink.grabFrame(source);
				Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
				
				outputStream.putFrame(output);
			}
			
			
		}).start();
	}
	
	public void Calculate()
	{
		input.read(src);
		Mat dst = new Mat();
		
		Scalar hsvLow = new Scalar(HSub, SSub, VSub);
		Scalar hsvHigh = new Scalar(HAdd, SAdd, VAdd);
		
		Core.inRange(src, hsvLow, hsvHigh, dst);
		
		List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
		List<MatOfPoint> contours2 = new ArrayList<MatOfPoint>();
		
		Mat mHierarchy = new Mat();
		Mat goalFiltered = Imgcodecs.imread("file you compare to");
		Imgproc.cvtColor(goalFiltered, goalFiltered, Imgproc.COLOR_BGR2GRAY);
		Imgproc.findContours(goalFiltered, contours1, mHierarchy, 2, 1);
		Imgproc.findContours(dst, contours2, mHierarchy, 2, 1);
		
		Mat out2 = new Mat();
		
		for (int i = 0; i < contours2.size(); i++)
		{
			Imgproc.drawContours(out2, contours1, i, new Scalar(255.0, 0.0, 255.0));
		}
		
		Imgproc.findContours(goalFiltered, contours1, mHierarchy, 2, 1);
		Imgproc.findContours(dst, contours2, mHierarchy, 2, 1);
		
		
		int largest = 0;
		int index = -1;
		
		for (int i = 0; i < contours2.size(); i++)
		{
			double val = Imgproc.contourArea((Mat) contours2.get(i), true);
			double size = Imgproc.boundingRect(contours2.get(i)).width;
			
			if (size > largest)
			{
				index = i;
				largest = (int) size;
			}
		}
		
		Mat out = new Mat();
		src.copyTo(out);
		
		Imgproc.drawContours(out, contours2, index, new Scalar(255, 0, 255));
		
		if (index > -1)
		{
			System.out.printf("size %d index %d", largest, index);
			int x = Imgproc.boundingRect(contours2.get(index)).x;
			int y = Imgproc.boundingRect(contours2.get(index)).y;
			
			int width = Imgproc.boundingRect(contours2.get(index)).width;
			int height = Imgproc.boundingRect(contours2.get(index)).height;
			
			Imgproc.rectangle(out, new Point(x, y), new Point(x + width, y + height), new Scalar(255, 0, 255));
			
			
			/* Because Java is stupid, I have to take contours2, and put it into a
			* new 32 bit Mat because reasons an f*ck java - opencv
			*/
			List<MatOfPoint2f> newMat = new ArrayList<MatOfPoint2f>();
			((Mat) contours2).convertTo((Mat) newMat, CvType.CV_32F);
			
			RotatedRect rect = Imgproc.minAreaRect((MatOfPoint2f) newMat.get(index));
			
			Point[] vtx = new Point[4];
			
			rect.points(vtx);
			
			for (int i = 0; i < 4; i++)
			{
				Imgproc.line(out, vtx[i], vtx[(i+1)%4], new Scalar(0, 128, 255), 3);
			}
			
			//Wil this work?  Idk.  Hopefully. 
			im.showImage(out);
		}
	}
	
	
	
}

package org.usfirst.frc.team1360.robot.util;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.*;
import org.opencv.videoio.*;


import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;

public class OrbitVision 
{
	private int lowThreshold = 125;
	private String filename;
	//Mat src = Imgcodecs.imread("Filtered_Goal.png");
	CvSink src;
	private static CvSource outputStream;
	private Mat frame = new Mat();
	private Mat dst = new Mat();
	
	
	private List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
	private List<MatOfPoint> contours2 = new ArrayList<MatOfPoint>();
	private Mat mHierarchy = new Mat();
	private Mat otherMat = new Mat(); 
	private double offset = 3000;
	private int index = 0;
	private int index2 = 0;
	
	List<MatOfPoint2f> newMat = new ArrayList<MatOfPoint2f>(); //this is new
	
	//VideoCapture input = new VideoCapture("http://10.13.60.3/mjpg/video.mjpg");
	
	
	//Imshow im = new Imshow("LOL");

	
	//HSV values
	private double HSub = 110;
	private double HAdd = 250;
	private double SSub = 190;
	private double SAdd = 255;
	private double VSub = 0;
	private double VAdd = 25;
	
	/*private double HSub;
	private double HAdd;
	private double SSub;
	private double SAdd;
	private double VSub;
	private double VAdd;*/
	
	public OrbitVision()
	{
		//CameraServer.getInstance().addAxisCamera("http://10.13.60.3/mjpg/video.mjpg");
		CameraServer.getInstance().addAxisCamera("10.13.60.3");
		outputStream = CameraServer.getInstance().putVideo("Awesome Video", 320, 240);
		//goalFiltered = Imgcodecs.imread("Filtered_Goal.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		//SmartDashboard.putString("Camera Server", "http://10.13.60.3/mjpg/video.mjpg");
	}

	
	public void Calculate()
	{
		
			contours2.clear();
		
			src = CameraServer.getInstance().getVideo();
			

			 Scalar hsvLow = new Scalar(HSub, SSub, VSub);
			 Scalar hsvHigh = new Scalar(HAdd, SAdd, VAdd);
			
			src.grabFrame(frame);
			
			Core.inRange(frame, hsvLow, hsvHigh, dst);
			
			dst.copyTo(otherMat);
			
			//Imgproc.findContours(goalFiltered, contours1, mHierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
			Imgproc.findContours(otherMat, contours2, mHierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
			
			
			for(int i = 0; i < contours2.size(); i++)
			{
				double width = Imgproc.boundingRect(contours2.get(i)).width;
				double height = Imgproc.boundingRect(contours2.get(i)).height;
				
				if(width * height < 2822.0)
				{
					System.out.println(width*height);
					contours2.remove(i);
					
				}
			}
			
			
			for(int i = 0; i < contours2.size(); i++)
			{
				double width = Imgproc.boundingRect(contours2.get(i)).width;
				double height = Imgproc.boundingRect(contours2.get(i)).height;
				
				if((Math.abs(width / height) - 2.5) < offset)
				{
					index = i;
					//offset = width / height;
					offset = height / width;
				}
				
				if((Math.abs(width / height) - 2.5) < offset && i != index)
				{
					index2 = i;
				}
				
				/*this stuff is new*/
			}
			
			if (index > -1)
			{
				int x = Imgproc.boundingRect(contours2.get(index)).x;
				int y = Imgproc.boundingRect(contours2.get(index)).y;
				
				int width = Imgproc.boundingRect(contours2.get(index)).width;
				int height = Imgproc.boundingRect(contours2.get(index)).height;
				
				int x2 = Imgproc.boundingRect(contours2.get(index2)).x;
				int y2 = Imgproc.boundingRect(contours2.get(index2)).y;
				
				int width2 = Imgproc.boundingRect(contours2.get(index2)).width;
				int height2 = Imgproc.boundingRect(contours2.get(index2)).height;
				
				
				Imgproc.rectangle(frame, new Point(x, y), new Point(x + width, y + height), new Scalar(0, 100, 100), 5);
				Imgproc.rectangle(frame, new Point(x2, y2), new Point(x2 + width2, y2 + height2), new Scalar(100, 100, 100), 5);
				
				/*
				((Mat) contours2).convertTo((Mat) newMat, CvType.CV_32F);
				ArrayList<MatOfPoint> secondMat = new ArrayList<MatOfPoint>(CvType.CV_32F);
				
				RotatedRect rect = Imgproc.minAreaRect((MatOfPoint2f) newMat.get(index));
				
				Point[] vtx = new Point[4];
				
				rect.points(vtx);
				
				for (int j = 0; i < 4; i++)
				{
					Imgproc.line(frame, vtx[i], vtx[(j+1)%4], new Scalar(0, 128, 255), 3);
				}
				*/
			}
			
			System.out.println(index);
			

			
			//Imgproc.drawContours(frame, contours2, index, new Scalar(50, 100, 255), 5);
			
			
			//outputStream.putFrame(dst);
			outputStream.putFrame(frame);
			
			//System.out.println("Running Vision");
			
			
		
		/*
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
			
			System.out.print("1");
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
			// new 32 bit Mat because reasons an f*ck java - opencv
			
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
			//im.showImage(out);
		}
			*/
	}
	
	
	
}

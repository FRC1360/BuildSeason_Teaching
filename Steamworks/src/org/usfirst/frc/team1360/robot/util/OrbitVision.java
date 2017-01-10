package org.usfirst.frc.team1360.robot.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.video.*;
import org.opencv.videoio.*;

import com.atul.JavaOpenCV.Imshow;

import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;

public class OrbitVision 
{
	private int lowThreshold = 125;
	Mat src = Imgcodecs.imread("ggggg");
	VideoCapture input = new VideoCapture("I think this is where you put the IP address?");
	Imshow im = new Imshow("LOL");

	
	//HSV values
	private double HSub;
	private double HAdd;
	private double SSub;
	private double SAdd;
	private double VSub;
	private double VAdd;
	
	public OrbitVision(double HS, double HA, double SS, double SA, double VS, double VA)
	{
		this.HSub = HS;
		this.HAdd = HA;
		this.SSub = SS;
		this.SAdd = SA;
		this.VSub = VS;
		this.VAdd = VA;
	}
	
	public void Calculate()
	{
		input.read(src);
		Mat dst = new Mat();
		int BlockSize = 1;
		int kSize = 1;
		
		Scalar hsvLow = new Scalar(HSub, SSub, VSub);
		Scalar hsvHigh = new Scalar(HAdd, SAdd, VAdd);
		
		Core.inRange(src, hsvLow, hsvHigh, dst);
		
		List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
		List<MatOfPoint> contours2 = new ArrayList<MatOfPoint>();
		
		Mat mHierarchy = new Mat();
		Vector<MatOfInt4> hierarchy;
		Mat goalFiltered = Imgcodecs.imread("generic file name here");
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
			
			im.showImage(out);
		}
	}
	
	
	
}

package org.usfirst.frc.team1360.robot.IO;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class RobotOutput {
	
	private static  RobotOutput instance;
	
	public Victor driveLeftBack;
	public Victor driveRightBack;
	public Victor driveLeftFront;
	public Victor driveRightFront;
	public Victor intakeRight;
	public Victor intakeLeft;
	public Solenoid Valve;
	private boolean ejected = false;
	
	private RobotOutput()
	{
		driveLeftFront = new Victor(0);
		driveLeftBack = new Victor(1);
		driveRightFront = new Victor(2);
		driveRightBack = new Victor(3);
		intakeRight = new Victor(4);
		intakeLeft = new Victor(5);
		Valve = new Solenoid(7);
	}
	
	public static RobotOutput getInstance()
	{
		if (instance == null)
			instance = new RobotOutput();
		
		return instance;
	}
	
	public void tankDrive(double left, double right)
	{
		driveLeftFront.set(-left);
		driveLeftBack.set(-left);
		driveRightFront.set(right);
		driveRightBack.set(right);
	}
	
	public void intake(double zoom)
	{
		intakeRight.set(zoom);
		intakeLeft.set(zoom);
	}
	
	public void switchSolenoidState()
	{
		Valve.set(!ejected);
		ejected = !ejected;
	}
	
	public void stopAll()
	{
		
	}
}

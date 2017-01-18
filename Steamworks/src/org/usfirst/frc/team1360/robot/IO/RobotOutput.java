package org.usfirst.frc.team1360.robot.IO;

import edu.wpi.first.wpilibj.Victor;

public class RobotOutput {
	
	private static  RobotOutput instance;
	private Victor driveLeft;
	private Victor driveRight;
	private Victor shoot;
	
	private RobotOutput()
	{
		this.driveLeft = new Victor(0);
		this.driveRight = new Victor(1);
		this.shoot = new Victor(2);
	}
	
	public static RobotOutput getInstance()
	{
		if (instance == null)
		{
			instance = new RobotOutput();
		}
		
		return instance;
	}
	
	public void tankDrive(double left, double right)
	{
		this.driveLeft.set(-left);
		this.driveRight.set(right);
	}
	
	public void shoot(double speed)
	{
		this.shoot.set(speed);
	}
	
	public void stopAll()
	{
		this.driveLeft.set(0);
		this.driveRight.set(0);
		this.shoot.set(0);
		
	}
}

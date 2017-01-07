package org.usfirst.frc.team1360.robot.IO;

public class RobotOutput {
	
	private static  RobotOutput instance;
	
	private RobotOutput()
	{
		
	}
	
	public static RobotOutput getInstance()
	{
		if (instance == null)
		{
			instance = new RobotOutput();
		}
		
		return instance;
	}
	
	public void stopAll()
	{
		
	}
}

package org.usfirst.frc.team1360.robot.IO;

public class SensorInput {

	private static SensorInput instance;
	
	private SensorInput()
	{
		
	}
	
	public static SensorInput getInstance()
	{
		if (instance == null)
		{
			instance = new SensorInput();
		}
		
		return instance;
	}
	
	public void calculate()
	{
		
	}
	
	public void reset()
	{
		
	}
}

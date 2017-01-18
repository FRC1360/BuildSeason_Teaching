package org.usfirst.frc.team1360.robot.teleop;

import org.usfirst.frc.team1360.robot.IO.HumanInput;
import org.usfirst.frc.team1360.robot.IO.RobotOutput;

public class TeleopDrive implements TeleopComponent {

	private static TeleopDrive instance;
	private RobotOutput robotOutput;
	private HumanInput humanInput;
	
	public static TeleopDrive getInstance()
	{
		if (instance == null)
			instance = new TeleopDrive();
		
		return instance;
	}
	
	@Override
	public void calculate() 
	{
		double left = humanInput.getDriveLeft() < -0.1 || humanInput.getDriveLeft() > 0.1 ? humanInput.getDriveLeft() : 0;
		double right = humanInput.getDriveRight() < -0.1 || humanInput.getDriveRight() > 0.1 ? humanInput.getDriveRight() : 0;
		
		robotOutput.tankDrive(left, right);
	}
	
	private TeleopDrive()
	{
		this.humanInput = HumanInput.getInstance();
		this.robotOutput = RobotOutput.getInstance();
	}

	@Override
	public void disable() 
	{
		robotOutput.tankDrive(0, 0);

	}

}

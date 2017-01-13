package org.usfirst.frc.team1360.robot.IO;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class RobotOutput {
	
	private static  RobotOutput instance;
	private Victor  driveLeftForward;
	private Victor driveLeftBackward;
	private Victor driveRightBack;
	private Victor driveRightForward;
	private RobotOutput()
	{
		driveLeftForward = new Victor(0);
		driveLeftBackward = new Victor(1);
		driveRightForward = new Victor(2);
		driveRightBack = new Victor(3);
	}
	
	public static RobotOutput getInstance()
	{
		if (instance == null)
		{
			instance = new RobotOutput();
		}
		return instance;
	}
	
	public void tankDrive(double left, double right){
		driveLeftForward.set(-left);
		driveLeftBackward.set(-left);
		driveRightForward.set(right);
		driveRightBack.set(right);
	}
	public void arcadeDrive(double speed,double turn){
		double left = -(speed) - turn;
		double right = -(speed) + turn;
		tankDrive(left,right);
	}
	//public void pushSolenoid (boolean input){
		//Solenoid1.set(input);
	//}
	//public void InorOut(double input){
		//InOut.set(input);
	//}
	public void stopAll()
	{
		driveLeftForward.set(0);
		driveRightBack.set(0);
		driveLeftBackward.set(0);
		driveRightForward.set(0);
	}
}

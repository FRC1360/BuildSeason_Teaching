package org.usfirst.frc.team1360.robot.IO;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class RobotOutput {
	
	private static  RobotOutput instance;
	//private Victor  driveLeftForward;
	private Victor InOut;
	//private Victor driveRightBack;
	//private Solenoid Solenoid1;
	private RobotOutput()
	{
		//driveLeftForward = new Victor(0);
	    InOut = new Victor(1);
		//driveRightBack = new Victor(3);
		//Solenoid1 = new Solenoid(0);
	}
	
	public static RobotOutput getInstance()
	{
		if (instance == null)
		{
			instance = new RobotOutput();
		}
		return instance;
	}
	
	/*public void tankDrive(double left, double right){
		driveLeftForward.set(-left);
		driveLeftBack.set(-left);
		driveRightForward.set(right);
		driveRightBack.set(right);
	}*/
	/*public void arcadeDrive(double speed,double turn){
		double left = -(speed) - turn;
		double right = -(speed) + turn;
		tankDrive(left,right);
	}*/
	//public void pushSolenoid (boolean input){
		//Solenoid1.set(input);
	//}
	public void InorOut(double input){
		InOut.set(input);
	}
	public void stopAll()
	{
		//driveLeftForward.set(0);
		InOut.set(0);
		//driveRightBack.set(0);
		//Solenoid1.set(0);
	}
}

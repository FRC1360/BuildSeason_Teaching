package org.usfirst.frc.team1360.robot.teleop;

import org.usfirst.frc.team1360.robot.IO.HumanInput;
import org.usfirst.frc.team1360.robot.IO.RobotOutput;

public class TeleopDrive implements TeleopComponent {
	
	private static TeleopDrive instance;
	private RobotOutput robotOutput;
	private HumanInput humanInput;
	
	public static TeleopDrive getInstance(){
		if (instance==null){
			instance = new TeleopDrive();
		}
		return instance;
	}
	private TeleopDrive(){
		this.humanInput = HumanInput.getInstance();
		this.robotOutput = RobotOutput.getInstance();
	}
	@Override
	public void calculate() {
		double left = this.humanInput.getDriverLeftMove();
		double right = this.humanInput.getDriverRightMove();
		if (Math.abs(left)<0.1){
			left=0;
		}
		if(Math.abs(right)<0.1){
			right=0;
		}
		this.robotOutput.tankDrive(left,right);
	}

	@Override
	public void disable() {
		this.robotOutput.tankDrive(0, 0);
	}

}

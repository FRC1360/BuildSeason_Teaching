package org.usfirst.frc.team1360.robot.teleop;

import org.usfirst.frc.team1360.robot.IO.HumanInput;
import org.usfirst.frc.team1360.robot.IO.RobotOutput;

public class TeleopPneumatics implements TeleopComponent {

	private static TeleopPneumatics instance;
	private RobotOutput robotOutput;
	private HumanInput humanInput;
	
	public static TeleopPneumatics getInstance()
	{
		if (instance == null)
			instance = new TeleopPneumatics();
		
		return instance;
	}
	
	private TeleopPneumatics()
	{
		this.humanInput = HumanInput.getInstance();
		this.robotOutput = RobotOutput.getInstance();
	}
	
	@Override
	public void calculate() {
		if (humanInput.getValvePushed())
			robotOutput.switchSolenoidState();

	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub

	}

}

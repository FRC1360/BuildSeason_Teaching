package org.usfirst.frc.team1360.robot.teleop;

import org.usfirst.frc.team1360.robot.IO.HumanInput;
import org.usfirst.frc.team1360.robot.IO.RobotOutput;

public class TeleopShoot implements TeleopComponent{
	private static TeleopShoot instance;
	private RobotOutput robotOutput;
	private HumanInput humanInput;
	public static TeleopShoot getInstance(){
		if (instance==null){
			instance = new TeleopShoot();
		}
		return instance;
	}
	private TeleopShoot(){
		this.humanInput = HumanInput.getInstance();
		this.robotOutput = RobotOutput.getInstance();
	}
	@Override
	public void calculate() {
		double intake2 = this.humanInput.getOperatorIn();
		double outtake2 = this.humanInput.getOperatorOut();
		if (Math.abs(intake2)<0.1){
			intake2 = 0;
		}
		if (Math.abs(outtake2)<0.1){
			outtake2 = 0;
		}
		double difference = intake2-outtake2;
		this.robotOutput.InorOut(difference);
		//this.robotOutput.InorOut(outtake2);
	}
	@Override
	public void disable() {
		this.robotOutput.InorOut(0);
	}
}

package org.usfirst.frc.team1360.auto.shoot;

import org.usfirst.frc.team1360.auto.AutonCommand;
import org.usfirst.frc.team1360.auto.RobotSubsystems;
import org.usfirst.frc.team1360.robot.IO.RobotOutput;

public class AutonShoot extends AutonCommand {

	private RobotOutput robotOuput;
	private double speed;
	
	public AutonShoot(long timeout, double speed) {
		super(RobotSubsystems.INTAKE, timeout);
		
		this.robotOuput = RobotOutput.getInstance();
		this.speed = speed;
	}

	@Override
	public boolean calculate() {
		this.robotOuput.shoot(speed);
		return false;
	}

	@Override
	public void override() {
		this.robotOuput.shoot(0);
	}

}

package org.usfirst.frc.team1360.auto.drive;

import org.usfirst.frc.team1360.auto.AutonCommand;
import org.usfirst.frc.team1360.auto.RobotSubsystems;
import org.usfirst.frc.team1360.robot.IO.RobotOutput;

public class AutonIntakeOut extends AutonCommand {

	RobotOutput robotOutput;
	double speed;
	
	public AutonIntakeOut(long time, double speed) {
		super(RobotSubsystems.INTAKE, time);
		robotOutput = RobotOutput.getInstance();
		this.speed = speed;
	}

	@Override
	public boolean calculate() {
		robotOutput.intake(-speed);
		return false;
	}

	@Override
	public void override() {
		robotOutput.intake(0);		
	}

}

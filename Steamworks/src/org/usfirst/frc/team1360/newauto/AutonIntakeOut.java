package org.usfirst.frc.team1360.newauto;

import org.usfirst.frc.team1360.robot.IO.RobotOutput;

public class AutonIntakeOut extends AutonBaseClass {

	RobotOutput robotOutput;
	long time;
	double speed;
	
	AutonIntakeOut(long time, double speed) {
		robotOutput = RobotOutput.getInstance();
		this.time = time;
		this.speed = speed;
	}
	
	@Override
	public void run() {
		robotOutput.intake(-speed);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
		StopNow();
	}

	@Override
	public void StopNow() {
		robotOutput.intake(0);
	}

}

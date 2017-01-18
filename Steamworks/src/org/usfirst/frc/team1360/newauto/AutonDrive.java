package org.usfirst.frc.team1360.newauto;

import org.usfirst.frc.team1360.robot.IO.RobotOutput;

public class AutonDrive extends AutonBaseClass {
	RobotOutput robotOutput;
	double speed;
	long time;
	
	AutonDrive(long time, double speed) {
		robotOutput = RobotOutput.getInstance();
		this.time = time;
		this.speed = speed;
	}

	@Override
	public void run() {
		robotOutput.tankDrive(speed, speed);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
		StopNow();
	}

	@Override
	public void StopNow() {
		robotOutput.tankDrive(0, 0);
	}
}

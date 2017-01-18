package org.usfirst.frc.team1360.auto.mode.step1;

import org.usfirst.frc.team1360.auto.drive.AutonDrive;
import org.usfirst.frc.team1360.auto.mode.AutonBuilder;
import org.usfirst.frc.team1360.auto.mode.AutonMode;
import org.usfirst.frc.team1360.auto.shoot.AutonShoot;
import org.usfirst.frc.team1360.auto.util.AutonWait;

public class DriveWhileShoot implements AutonMode {

	@Override
	public void addToMode(AutonBuilder ab) {
		// TODO Auto-generated method stub
		ab.addCommand(new AutonDrive(5000, 1));
		ab.addCommand(new AutonShoot(5000, 1));
		ab.addCommand(new AutonWait(10000));
	}

}
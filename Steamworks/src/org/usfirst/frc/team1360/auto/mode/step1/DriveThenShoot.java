package org.usfirst.frc.team1360.auto.mode.step1;

import org.usfirst.frc.team1360.auto.drive.AutonDrive;
import org.usfirst.frc.team1360.auto.drive.DriveWait;
import org.usfirst.frc.team1360.auto.mode.AutonBuilder;
import org.usfirst.frc.team1360.auto.mode.AutonMode;
import org.usfirst.frc.team1360.auto.shoot.AutonShoot;
import org.usfirst.frc.team1360.auto.shoot.ShootWait;
import org.usfirst.frc.team1360.auto.util.AutonWait;

public class DriveThenShoot implements AutonMode {

	@Override
	public void addToMode(AutonBuilder ab) {
		ab.addCommand(new AutonDrive(5000, 1));
		ab.addCommand(new DriveWait());
		ab.addCommand(new AutonShoot(2000, 1));
		ab.addCommand(new ShootWait());
		ab.addCommand(new AutonWait(1000));
	}

}

package org.usfirst.frc.team1360.auto.step1;

import org.usfirst.frc.team1360.auto.drive.AutonDriveForward;
import org.usfirst.frc.team1360.auto.drive.AutonIntakeOut;
import org.usfirst.frc.team1360.auto.drive.DriveWait;
import org.usfirst.frc.team1360.auto.mode.AutonBuilder;
import org.usfirst.frc.team1360.auto.mode.AutonMode;
import org.usfirst.frc.team1360.auto.util.AutonWait;

public class DriveShoot implements AutonMode {

	@Override
	public void addToMode(AutonBuilder ab) {
		ab.addCommand(new AutonDriveForward(5000, 1));
		ab.addCommand(new DriveWait());
		ab.addCommand(new AutonIntakeOut(2000, 1));
		ab.addCommand(new AutonWait(10000));
	}

}

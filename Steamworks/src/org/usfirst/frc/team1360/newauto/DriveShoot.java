package org.usfirst.frc.team1360.newauto;

public class DriveShoot extends AutonMode {

	@Override
	public void run() {
		AutonDrive drive = new AutonDrive(5000, 1);
		drive.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		AutonIntakeOut intake = new AutonIntakeOut(3000, 1);
		intake.start();
		try {
			drive.join();
			intake.join();
		} catch (InterruptedException e) {
		}
	}

}

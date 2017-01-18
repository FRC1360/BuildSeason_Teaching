package org.usfirst.frc.team1360.newauto;

public class JustDrive extends AutonMode {

	@Override
	public void run() {
		AutonDrive drive = new AutonDrive(5000, 1);
		drive.start();
		try {
			drive.join();
		} catch (InterruptedException e) {
		}
		
	}

}

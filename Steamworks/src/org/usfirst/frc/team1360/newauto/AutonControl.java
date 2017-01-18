package org.usfirst.frc.team1360.newauto;

import java.util.ArrayList;

public class AutonControl {
	ArrayList<AutonMode> modes = new ArrayList<>();
	
	public AutonControl() {
		modes.add(new JustDrive());
	}
	
	public void startAuton(int mode) {
		modes.get(mode).run();
	}
}

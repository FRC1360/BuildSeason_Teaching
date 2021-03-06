package org.usfirst.frc.team1360.robot.teleop;

import java.util.ArrayList;

public class TeleopControl {
	
	private ArrayList<TeleopComponent> components;
	private static TeleopControl instance;
	
	private TeleopControl()
	{
		this.components = new ArrayList<>();
	}
	
	public static TeleopControl getInstance()
	{
		if (instance == null)
		{
			instance = new TeleopControl();
		}
		
		return instance;
	}
	
	public void runCycle()
	{
		for (TeleopComponent t: this.components)
		{
			t.calculate();
		}
	}
	
	public void disable()
	{
		for (TeleopComponent t: this.components)
		{
			t.disable();
		}
	}
}

package org.usfirst.frc.team3414.config;

import org.usfirst.frc.team3414.diagnostic.Diagnostic;

import edu.wpi.first.wpilibj.DriverStation;

public class Config {
	public static final int LEFT_FRONT = 1;
	public static final int LEFT_MIDDLE = 2;
	public static final int LEFT_REAR = 3;
	public static final int RIGHT_FRONT = 4;
	public static final int RIGHT_MIDDLE = 5;
	public static final int RIGHT_REAR = 6;
	
	public static final int LEFT_STICK = 0;
	public static final int RIGHT_STICK = 0;
	public static final int CONTROLLER_CHANNEL = 0;
	public static String autoFile = (Diagnostic.position+"-"+DriverStation.getInstance().getGameSpecificMessage());
}

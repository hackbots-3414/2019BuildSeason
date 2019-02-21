package org.usfirst.frc.team3414.config;

import org.usfirst.frc.team3414.diagnostic.Diagnostic;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Config {
	//public static final double INTAKE_THROTTLE = .65;
	public static double intake_throttle(){
		return SmartDashboard.getNumber("Throttle %", .65);
	}
	public static final String REPLAY_MODE = "disabled";
	public static boolean PIT_MODE = true; //0-Comp mode 1-Pit mode

	public static final int LEFT_STICK = 0;
	public static final int RIGHT_STICK = 1;
	public static final int CONTROLLER_CHANNEL = 2;

	public static final int LEFT_FRONT = 1;
	public static final int LEFT_REAR = 2;
	public static final int RIGHT_FRONT = 4;
	public static final int RIGHT_REAR = 5;

	public static String getAutoFile() {
		return "/home/lvuser/Output.txt";
	}

	public static final int TUNNEL_TALON_TOP = 25;

	public static final int INTAKE_TALON = 41;
	public static final int INTAKE_PISTON = 0;
	public static final int INTAKE_PISTON_TWO = 1;
	// TODO implement bottom tunnel is 51
	// TODO SHould be 41
	public static final int HORIZONTAL_MANIPULATOR_IN = 2;
	public static final int HORIZONTAL_MANIPULATOR_OUT = 3;
	public static final int VERTICAL_MANIPULATOR_DOWN = 5;
	public static final int VERTICAL_MANIPULATOR_UP = 4;

	public static final int CARGO_MOTOR_ONE = 51;
	public static final int CARGO_MOTOR_TWO = 45;

	public static final int CLIMBER_FRONT = 21;
	public static final int CLIMBER_REAR = 31;
	public static final int CLIMBER_MOTOR_THREE = 11;

	public static final int BALL_SENSOR_TOP = 1;
	public static final int BALL_SENSOR_BOTTOM = 0;
}

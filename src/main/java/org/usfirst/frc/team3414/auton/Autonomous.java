/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3414.auton;

import org.usfirst.frc.team3414.actuators.DriveTrain;
import org.usfirst.frc.team3414.sensors.LimeLightUtil;
import org.usfirst.frc.team3414.sensors.Limelight;
import org.usfirst.frc.team3414.teleop.Teleop;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class Autonomous {
    Joystick left = Teleop.getInstance().getLeftJoystick();
    Joystick right = Teleop.getInstance().getRightJoystick();
    AnalogInput irLeft = Teleop.getInstance().getLeftIR();
    AnalogInput irRight = Teleop.getInstance().getRightIR();
    AnalogInput lineSensor = Teleop.getInstance().getLineSensor();
    public void align() {
		if (right.getRawButton(3)) {
			// while(!left.getRawButton(Config.ESCAPE_BUTTON)){
			Limelight.compMode();
			LimeLightUtil.driveToTarget(DriveTrain.getInstance().getLeftMotor(),
					DriveTrain.getInstance().getRightMotor(), irLeft, irRight, Teleop.getInstance().getRightJoystick(),
					lineSensor);
			// }
		} else {
			DriveTrain.getInstance().setBlock(false);
			Limelight.pitMode();
		}
		if (right.getRawButton(4)) {
			LimeLightUtil.shiftRobotLeft(DriveTrain.getInstance().getLeftMotor(),
					DriveTrain.getInstance().getRightMotor());
		}
		if (right.getRawButton(5)) {
			LimeLightUtil.shiftRobotRight(DriveTrain.getInstance().getLeftMotor(),
					DriveTrain.getInstance().getRightMotor());
		}
		if (right.getRawButton(2)) {
			LimeLightUtil.straightenRobotToTarget(DriveTrain.getInstance().getLeftMotor(),
					DriveTrain.getInstance().getRightMotor(), irLeft, irRight, Teleop.getInstance().getRightJoystick());
		}

	}
}

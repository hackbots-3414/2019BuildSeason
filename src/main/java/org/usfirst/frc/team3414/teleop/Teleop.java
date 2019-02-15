package org.usfirst.frc.team3414.teleop;

import java.awt.SystemTray;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team3414.actuators.Climber;
import org.usfirst.frc.team3414.actuators.DriveTrain;
import org.usfirst.frc.team3414.actuators.HatchPanelManipulator;
import org.usfirst.frc.team3414.actuators.Intake;
import org.usfirst.frc.team3414.actuators.Tunnel;
import org.usfirst.frc.team3414.config.Config;
import org.usfirst.frc.team3414.diagnostic.Diagnostic;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop {
	private static Teleop instance;

	public static Teleop getInstance() {
		if (instance == null) {
			instance = new Teleop();
		}

		return instance;

	}

	Joystick left = new Joystick(Config.LEFT_STICK);
	Joystick right = new Joystick(Config.RIGHT_STICK);
	Controller pad = new Controller(Config.CONTROLLER_CHANNEL);
	public void drive() {
		DriveTrain.getInstance().teleop(left.getY(), right.getY());
	}
	public void ball(){
		Intake.getInstance().intakeMotor.set(ControlMode.PercentOutput, 5);
		if(pad.getAButton()){ //Turn on Intake, run tunnel
		System.out.println("Intake running");
		//Intake.getInstance().on();
		Tunnel.getInstance().on();
		HatchPanelManipulator.getInstance().setOut();
		System.out.println("A Button");
		}
		/*
		else if(pad.getBButton()){
			System.out.println("Get B button");
			Tunnel.getInstance().on();
		}
		*/
		else{
			System.out.println("This is the else code");
			Tunnel.getInstance().off();
			//Intake.getInstance().off();
		}
	

	
		if(pad.getYButton())	{
			System.out.println("I should be going up");
			Intake.getInstance().goUp();
		}
		else if(pad.getXButton()){
			System.out.println("I should be going down");
			Intake.getInstance().goDown();
		}

		else{
			Intake.getInstance().stop();
		}
	}
	
	}
	
	/*	BEGIN LEGACY CODE

	public void legacyIntake() {
		if (pad.getYButton()) {
			Intake.getInstance().on();

		} else {
			Intake.getInstance().off();
		}
	}

	

	public void legacyClimber() { 
		if (pad.getRBButton()) {
			Climber.getInstance().up();
		} else if (pad.getLBButton()) {
			Climber.getInstance().down();
		} else {
			Climber.getInstance().stop();
		}

	}

	public void legacyManipulator() {
		if (pad.getLT() && pad.getRT()) {
			HatchPanelManipulator.getInstance().outandup();
			}
			else if (pad.getLT()) {
				HatchPanelManipulator.getInstance().outanddown();
			}
			else{
				HatchPanelManipulator.getInstance().inanddown();
			}

	}
	
	*/
	

package org.usfirst.frc.team3414.teleop;

import org.usfirst.frc.team3414.config.Config;

import edu.wpi.first.wpilibj.Joystick;

public class F310 {
	//Normal Controllers with Normal Mappings. There is a difference.
	public F310(int channel){
		 pad = new Joystick(channel);

	}
	Joystick pad;
		public void closeOut() {
			pad = null;
		}
	public boolean getAButton() {
		return pad.getRawButton(2);

	}

	public boolean getBButton() {
		return pad.getRawButton(3);

	}

	public boolean getXButton() {
		return pad.getRawButton(1);

	}

	public boolean getYButton() {
		return pad.getRawButton(4);

	}

	public boolean getLBButton() {
		return pad.getRawButton(5);

	}

	public boolean getRBButton() {
		return pad.getRawButton(6);
	}

	public boolean getLT() {
		return pad.getRawButton(7);

	}

	public boolean getRT() {
		return pad.getRawButton(8);

	}
	public boolean getLSButton(){
		return pad.getRawButton(11);
	}
	public boolean getRSButton(){
		return pad.getRawButton(12);
	}

	public double getPov() {
		return pad.getPOV();
	}
	public boolean isPovPositive(){
		if((135 >= pad.getPOV()) && (pad.getPOV() <= 45)){
			return true;
		}
		else{
			return false;
		}		
	}
	public boolean isPovNegative(){
		if((225 >= pad.getPOV()) && (pad.getPOV() <= 315)){
			return true;
		}
		else{
			return false;
		}		
	}
private static F310 instance;

public static F310 getInstance()
		{
			if(instance == null)
			{
				instance = new F310(Config.CONTROLLER_CHANNEL);
			}
			
			return instance;
			
	}
}
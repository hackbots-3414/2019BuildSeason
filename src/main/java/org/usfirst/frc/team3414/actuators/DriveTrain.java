package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.config.Config;

import edu.wpi.first.wpilibj.AnalogInput;

public class DriveTrain {
	
	double leftJoySpeed = 0;
	double rightJoySpeed = 0;
	private static DriveTrain instance;
	public MultiMotor left = new MultiMotor(Config.LEFT_FRONT,Config.LEFT_MIDDLE,Config.LEFT_REAR);
	public MultiMotor right = new MultiMotor(Config.RIGHT_FRONT,Config.RIGHT_MIDDLE,Config.RIGHT_REAR);
	AnalogInput longRangeIRLeft = new AnalogInput(0);
	AnalogInput longRangeIRRight = new AnalogInput(1);
	AnalogInput lineSensor = new AnalogInput(2);

	public void autocorrect(){
	left.set(0);
    right.set(0);
  //Timer.delay(1);
  //Back up robot to white line if it was passed over
  while (lineSensor.getAverageVoltage() < 1 ) {
    left.set(-0.10);
    right.set(-0.10);
  }
  //Stop Movement
  left.set(0);
  right.set(0);
  //Rotate robot to left until straight towards target
  while ((longRangeIRLeft.getAverageVoltage() - longRangeIRRight.getAverageVoltage()) > 0.05) {
    right.set(-0.25);
    left.set(0.25);
  }
  //Rotate robot to right until straight towards target
  while ((longRangeIRRight.getAverageVoltage() - longRangeIRLeft.getAverageVoltage()) > 0.05) {
    right.set(0.25);
    left.set(-0.25);
  }
  // Timer.delay(1);
}

	
	

	public void teleop(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed);
		leftJoySpeed = leftSpeed;
		rightJoySpeed = rightSpeed;
	}
	public void teleopLimit(double limit, double leftSpeed, double rightSpeed) {
		left.set(leftSpeed*limit);
		right.set(rightSpeed*limit);

	}
	public void setLeft(double speed) {
		left.set(speed);
		}
		public void setRight(double speed) {
		right.set(speed);
	}
	public double getLeft() {
		return leftJoySpeed;
	}
	public double getRight() {
		return rightJoySpeed;
	}
	public void init(){
		right.setInverted(true);

	}
		public static DriveTrain getInstance()
		{
			if(instance == null)
			{
				instance = new DriveTrain();
			}
			
			return instance;
			
	}
		public void stop() {
			teleop(0,0);
		}
}

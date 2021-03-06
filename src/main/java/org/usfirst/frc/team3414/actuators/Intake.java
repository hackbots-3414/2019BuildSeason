/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3414.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3414.config.Config;
import org.usfirst.frc.team3414.sensors.IrSensor;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Intake {
   
    

    private static Intake instance;

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }

        return instance;

    }

    public TalonSRX intakeMotor;
    Solenoid intakePiston0;
    Solenoid intakePiston1;
    double throttle = Config.INTAKE_THROTTLE;
    public void init() {
         intakeMotor = new TalonSRX(Config.INTAKE_TALON);
         intakePiston0 = new Solenoid(Config.INTAKE_PISTON);
         intakePiston1 = new Solenoid(Config.INTAKE_PISTON_TWO);
         
         intakeMotor.setInverted(true);
    }

    public void set(double speed) {

        intakeMotor.set(ControlMode.PercentOutput, speed);
    }

    public void on() {
        intakeMotor.set(ControlMode.PercentOutput, 1*throttle);
    }

    public void off() {
        intakeMotor.set(ControlMode.PercentOutput, 0.0*throttle);
    }

    public void reverse() {
        intakeMotor.set(ControlMode.PercentOutput, -1.0*throttle);
    }

    public void positive() {
        intakeMotor.set(ControlMode.PercentOutput, 1.0*throttle);
    }
    public void goDown(){
        intakePiston0.set(false);
       intakePiston1.set(true);
    }
    public void goUp(){
        intakePiston0.set(true);
        intakePiston1.set(false);
       }

	public void stop() {
    }
   
	public void diagnostic() {
        SmartDashboard.putNumber("Intake Motor %", intakeMotor.getMotorOutputPercent());
        SmartDashboard.putBoolean("Intake Piston 0", intakePiston0.get());
        SmartDashboard.putBoolean("Intake Piston 1", intakePiston1.get());
    }
}

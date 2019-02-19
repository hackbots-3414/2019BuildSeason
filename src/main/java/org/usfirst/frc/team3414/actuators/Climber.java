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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Climber {
    private static Climber instance;

    public static Climber getInstance()
    {
        if(instance == null)
        {
            instance = new Climber();
        }
        
        return instance;
        
}
    TalonSRX motorOne;
    TalonSRX motorTwo;
    TalonSRX motorThree;

   public void init(){
     motorOne = new TalonSRX(Config.CLIMBER_MOTOR_ONE);
     motorTwo = new TalonSRX(Config.CLIMBER_MOTOR_TWO);
     motorThree = new TalonSRX(Config.CLIMBER_MOTOR_THREE);
   }
   public void lockDriveTrain(){
    DriveTrain.getInstance().setBlock(true); //Put a block on the drivetain so Teleop Control doesn't mess with this
   }
   public void extendAll(){
       motorOne.set(ControlMode.PercentOutput, 1);
       motorTwo.set(ControlMode.PercentOutput, 1);
       
   }
   public void moveBottomForward(){
       motorThree.set(ControlMode.PercentOutput, 1);
   }
   public void moveForward(){
    DriveTrain.getInstance().set(.5,.5);
   }
   public void unlockDriveTrain(){
       DriveTrain.getInstance().setBlock(false); //Release drivetrain block
   }
   public void retractFront(){
       motorOne.set(ControlMode.PercentOutput, -1);
   }
   public void retractRear(){
       motorTwo.set(ControlMode.PercentOutput, -1);
   }
   public void stop(){
       motorOne.set(ControlMode.PercentOutput, 0);
       motorTwo.set(ControlMode.PercentOutput, 0);
       motorThree.set(ControlMode.PercentOutput, 0);

   }
    public void diagnostic(){
    SmartDashboard.putNumber("Climber One %:", motorOne.getMotorOutputPercent());
    SmartDashboard.putNumber("Climber Two %:", motorTwo.getMotorOutputPercent());
    SmartDashboard.putNumber("Climber Three %:", motorThree.getMotorOutputPercent());
    
    }
}

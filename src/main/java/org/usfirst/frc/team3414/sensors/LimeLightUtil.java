/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.networktables.*;
import com.ctre.phoenix.motorcontrol.can.*;

import org.usfirst.frc.team3414.actuators.DriveTrain;
import org.usfirst.frc.team3414.actuators.MultiMotor;
import org.usfirst.frc.team3414.config.Config;
import org.usfirst.frc.team3414.teleop.Teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * This is a demo program showing the use of the RobotDrive class. The
 * SampleRobot class is the base of a robot application that will automatically
 * call your Autonomous and OperatorControl methods at the right time as
 * controlled by the switches on the driver station or the field controls.
 *
 * <p>
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 *
 * <p>
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use TimedRobot or Command-Based
 * instead if you're new.
 */

public class LimeLightUtil {

  private static final double MAX_SPEED = 0.37; //.37
  private static final double MIN_SPEED = 0.30; //.37
  private static int escape = Config.ESCAPE_BUTTON;
  public static void shiftRobotLeft(MultiMotor leftMotor, MultiMotor rightMotor) {
    // shift left
    // start by moving backwards
    leftMotor.set(0.1);
    rightMotor.set(0.4);
    Timer.delay(0.3);
    // Move back again to straighten
    leftMotor.set(0.4);
    rightMotor.set(0.1);
    Timer.delay(0.2);
    // move forward
    // leftMotor.set(-0.45 );
    // rightMotor.set(-0.6);

    leftMotor.set(-0.35);
    rightMotor.set(-0.45);
    Timer.delay(0.35);
  }

  public static void shiftRobotRight(MultiMotor leftMotor, MultiMotor rightMotor) {
    // shift right
    // start by moving backwards
    leftMotor.set(0.4);
    rightMotor.set(0.1);
    Timer.delay(0.3);
    // Move back again to straighten
    leftMotor.set(0.1);
    rightMotor.set(0.4);
    Timer.delay(0.2);
    // move forward
    leftMotor.set(-0.45);
    rightMotor.set(-0.35);
    Timer.delay(0.35);
  }

  public static void moveLeftBackwards(MultiMotor leftMotor, MultiMotor rightMotor) {
    leftMotor.set(MIN_SPEED);
    rightMotor.set(-1 * MAX_SPEED);
  }

  public static void moveRightBackwards(MultiMotor leftMotor, MultiMotor rightMotor) {
    leftMotor.set(-1 * MAX_SPEED);
    rightMotor.set(-1 * MIN_SPEED);
  }

  public static void moveStraightForward(double speed, MultiMotor leftMotor, MultiMotor rightMotor) {
    leftMotor.set(speed);
    rightMotor.set(speed);
  }

  public static void moveStraightBackwards(double speed, MultiMotor leftMotor, MultiMotor rightMotor) {
    leftMotor.set(-1 * speed);
    rightMotor.set(-1 * speed);
  }

  public static void moveLeftForwardDirection(MultiMotor leftMotor, MultiMotor rightMotor) {
    leftMotor.set(-1 * MIN_SPEED);
    rightMotor.set(-1 * MAX_SPEED);
  }

  public static void moveRightForward(MultiMotor leftMotor, MultiMotor rightMotor) {
    leftMotor.set(-1 * MAX_SPEED);
    rightMotor.set(-1 * MIN_SPEED);
  }

  public static void alignToTarget(double tx, MultiMotor leftMotor, MultiMotor rightMotor) {
    if (tx < 0) {
      moveLeftForwardDirection(leftMotor, rightMotor);
    } else if (tx > 0) {
      moveRightForward(leftMotor, rightMotor);
    } else {
      System.out.println("I am in line!");
    }
    Timer.delay(0.05);
  }
  static NetworkTable netTable = NetworkTableInstance.getDefault().getTable("limelight");
  public static void turnToTarget(MultiMotor leftMotor, MultiMotor rightMotor) {
    double tx = netTable.getEntry("tx").getDouble(10);
    while(!Teleop.getInstance().getLeftJoystick().getRawButton(Config.ESCAPE_BUTTON) && !Teleop.getInstance().getRightJoystick().getRawButton(Config.ESCAPE_BUTTON) && (Math.abs(tx) > 1))
    {
      tx = netTable.getEntry("tx").getDouble(10);
      if (tx < 0) {
        leftMotor.set(.15);
        rightMotor.set(-.15);
          } else if (tx > 0) {
            rightMotor.set(.15);
            leftMotor.set(-.15);
            
              } else {
                  DriveTrain.getInstance().driveStraight((Teleop.getInstance().getLeftJoystick().getY()+Teleop.getInstance().getRightJoystick().getY())/2);
                }
                    }
      Timer.delay(0.05);
    }
  
   

  public static void moveTwoFeetToTarget(double ta, MultiMotor leftMotor, MultiMotor rightMotor) {
    // moveStraightForward(1.25);
    if (ta < 10) {
      moveStraightForward(0.25, leftMotor, rightMotor);
    } else if (ta > 10.0) {
      moveStraightBackwards(0.25, leftMotor, rightMotor);
    } else {
      System.out.println("Reached the target distance");
    }
  }

  public static void findTheLine(MultiMotor leftMotor, MultiMotor rightMotor, AnalogInput longRangeIRleft,
      AnalogInput longRangeIRright, Joystick rightJoy, AnalogInput lineSensor) {
    if (longRangeIRleft.getAverageVoltage() - longRangeIRright.getAverageVoltage() > 0.005) {
      // shift Robot Right until line is found
      straightenRobotToTarget(leftMotor, rightMotor, longRangeIRleft, longRangeIRright, rightJoy);
      while (lineSensor.getAverageVoltage() < 1 && !rightJoy.getRawButton(escape)) {
        LimeLightUtil.shiftRobotRight(leftMotor, rightMotor);
      }
      straightenRobotToTarget(leftMotor, rightMotor, longRangeIRleft, longRangeIRright, rightJoy);
    } else if (longRangeIRright.getAverageVoltage() - longRangeIRleft.getAverageVoltage() > 0.005) {
      // shift Robot left until line is found
      straightenRobotToTarget(leftMotor, rightMotor, longRangeIRleft, longRangeIRright, rightJoy);
      while (lineSensor.getAverageVoltage() < 1 && !rightJoy.getRawButton(escape)) {
        LimeLightUtil.shiftRobotLeft(leftMotor, rightMotor);

      }
      straightenRobotToTarget(leftMotor, rightMotor, longRangeIRleft, longRangeIRright, rightJoy);
    }
  }

  public static void findTheLineRight(MultiMotor leftMotor, MultiMotor rightMotor, AnalogInput longRangeIRleft,
      AnalogInput longRangeIRright, Joystick rightJoy, AnalogInput lineSensor) {
    // shift Robot Right until line is found
    straightenRobotToTarget(leftMotor, rightMotor, longRangeIRleft, longRangeIRright, rightJoy);
    while (lineSensor.getAverageVoltage() < 1 && !rightJoy.getRawButton(escape)) {
      LimeLightUtil.shiftRobotRight(leftMotor, rightMotor);
    }
    straightenRobotToTarget(leftMotor, rightMotor, longRangeIRleft, longRangeIRright, rightJoy);

  }

  public static void findTheLineLeft(MultiMotor leftMotor, MultiMotor rightMotor, AnalogInput longRangeIRleft,
      AnalogInput longRangeIRright, Joystick rightJoy, AnalogInput lineSensor) {
    // shift Robot left until line is found
    straightenRobotToTarget(leftMotor, rightMotor, longRangeIRleft, longRangeIRright, rightJoy);
    while (lineSensor.getAverageVoltage() < 1 && !rightJoy.getRawButton(escape)) {
      LimeLightUtil.shiftRobotLeft(leftMotor, rightMotor);

    }
    straightenRobotToTarget(leftMotor, rightMotor, longRangeIRleft, longRangeIRright, rightJoy);
  }

  public static void driveToTarget(MultiMotor leftMotor, MultiMotor rightMotor, AnalogInput longRangeIRleft,
      AnalogInput longRangeIRright, Joystick rightJoy, AnalogInput lineSensor) {
    NetworkTable nTable = NetworkTableInstance.getDefault().getTable("limelight");
    double tx = 0.0;
    nTable.getEntry("ledMode").setNumber(3);
    // FInd target
    while ((nTable.getEntry("tv").getDouble(0) > 0)
        && !Teleop.getInstance().getLeftJoystick().getRawButton(Config.ESCAPE_BUTTON)) {
      nTable = NetworkTableInstance.getDefault().getTable("limelight");
      double ta = nTable.getEntry("ta").getDouble(0);
      tx = nTable.getEntry("tx").getDouble(0);
      if (ta < 18) {
        LimeLightUtil.alignToTarget(tx, leftMotor, rightMotor);
      }
    }
    leftMotor.setNeutralMode(NeutralMode.Brake);
    rightMotor.setNeutralMode(NeutralMode.Brake);
    // else{
    // We are very close to target. Turn limelight off and stop moving
    leftMotor.set(0.0);
    rightMotor.set(0.0);
    Timer.delay(0.5);
    leftMotor.setNeutralMode(NeutralMode.Coast);
    rightMotor.setNeutralMode(NeutralMode.Coast);
    nTable.getEntry("ledMode").setNumber(1);
    // findTheLine(leftMotor, rightMotor, longRangeIRleft, longRangeIRright,
    // rightJoy, lineSensor);
    // }

  }

  public static void straightenRobotToTarget(MultiMotor leftMotor, MultiMotor rightMotor, AnalogInput longRangeIRleft,
      AnalogInput longRangeIRright, Joystick rightJoy) {
    // Stop Movement
    leftMotor.set(0);
    rightMotor.set(0);
    // Rotate robot to left until straight towards target
    while ((longRangeIRleft.getAverageVoltage() - longRangeIRright.getAverageVoltage()) > 0.002
        && !rightJoy.getRawButton(escape)) {
      leftMotor.set(0.15);
      rightMotor.set(-0.15);
    }
    // Rotate robot to right until straight towards target
    while ((longRangeIRright.getAverageVoltage() - longRangeIRleft.getAverageVoltage()) > 0.002
        && !rightJoy.getRawButton(escape)) {

      leftMotor.set(-0.15);
      rightMotor.set(0.15);
    }
    // Timer.delay(1);
  }

  public static void diagnostic() {
    SmartDashboard.putNumber("leftIR: ", Teleop.getInstance().getLeftIR().getAverageVoltage());
    SmartDashboard.putNumber("rightIR: ", Teleop.getInstance().getRightIR().getAverageVoltage());
  }
  }
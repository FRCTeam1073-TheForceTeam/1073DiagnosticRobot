/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.*;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ExampleSubsystem extends SubsystemBase {

  diagDashboard dashBoard;
  private WPI_TalonFX leftMotor;
  private static XboxController ioController;
  private SendableChooser chooser;
  private double maxVelocity;


  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem(diagDashboard _dashBoard, double _maxVelocity) {
    dashBoard = _dashBoard;
    maxVelocity = _maxVelocity;
    chooser = new SendableChooser();
    chooser.addOption("Power", ControlMode.PercentOutput);
    chooser.addOption("Velocity", ControlMode.Velocity);

    ioController = new XboxController(0);
    leftMotor = new WPI_TalonFX(24);

    leftMotor.configFactoryDefault();
    leftMotor.setSafetyEnabled(false);
    leftMotor.setNeutralMode(NeutralMode.Brake);
    leftMotor.configPeakOutputForward(1.0);

       /* leftMotorLeader.setInverted(true);
        leftMotorLeader.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        rightMotorLeader.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        leftMotorLeader.setSensorPhase(true);
        rightMotorLeader.setSensorPhase(true);
        double P = 0.05;
        double I = 0;
        double D = 0;
        leftMotorLeader.config_kP(0, P);
        rightMotorLeader.config_kP(0, P);
        leftMotorLeader.config_kI(0, I);
        rightMotorLeader.config_kI(0, I);
        leftMotorLeader.config_kD(0, D);
        rightMotorLeader.config_kD(0, D);

        leftMotorFollower.follow(leftMotorLeader);
        leftMotorFollower.setInverted(true);
        rightMotorFollower.follow(rightMotorLeader);
        rightMotorFollower.setInverted(true);

        leftMotorLeader.setSelectedSensorPosition(0);
        rightMotorLeader.setSelectedSensorPosition(0);
        leftMotorLeader.setIntegralAccumulator(0);
        rightMotorLeader.setIntegralAccumulator(0);

        solenoid.set(false);
        
        winchEngaged = false;*/
  }

  @Override
  public void periodic() {

   double signalInput = ioController.getRawAxis(1);

   SmartDashboard.putNumber("Signal Input", signalInput);
   SmartDashboard.putNumber("Signal Input velocity", (siganlInput * maxVelocity));
   leftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    SmartDashboard.putNumber("position", leftMotor.getSelectedSensorPosition());
    SmartDashboard.putNumber("current", leftMotor.getSupplyCurrent());

   if(chooser.getSelected().equals(ControlMode.PercentOutput)){
    leftMotor.set(ControlMode.PercentOutput, signalInput);
    SmartDashboard.putNumber("power output", leftMotor.getMotorOutputPercent());
   }
   else if(chooser.getSelected().equals(ControlMode.Velocity)){
     //Velocity takes a value in encoder ticks per 100 miliseconds
    leftMotor.set(ControlMode.Velocity, (signalInput*maxVelocity));
    leftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    SmartDashboard.putNumber("velocity encoder feedback", leftMotor.getSelectedSensorVelocity());
   }
   

   /*HashMap<String, Double> hm = new HashMap();

   hm.put("Velocity",1.0);
   hm.put("Position",2.0);
   hm.put("PID Error",3.0);
   hm.put("Voltage",4.0);

   dashBoard.publish("first", hm);*/



    // This method will be called once per scheduler run
  }
}

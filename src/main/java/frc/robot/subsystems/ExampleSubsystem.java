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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ExampleSubsystem extends SubsystemBase {

  diagDashboard dashBoard;
  private static WPI_TalonFX leftMotor;
  private static XboxController ioController;
  private SendableChooser chooser;
  private double maxVelocity;
  private static CANCoder encoder; 


  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem(diagDashboard _dashBoard, double _maxVelocity) {
    dashBoard = _dashBoard;
    maxVelocity = _maxVelocity;
    chooser = new SendableChooser();
    encoder = new CANCoder(5);
    chooser.addOption("Power", ControlMode.PercentOutput);
    chooser.addOption("Velocity", ControlMode.Velocity);

    chooser.setDefaultOption("Power", ControlMode.PercentOutput);

    SmartDashboard.putData("Control Type", chooser);

    ioController = new XboxController(0);
    leftMotor = new WPI_TalonFX(13);

    leftMotor.configFactoryDefault();
    leftMotor.setSafetyEnabled(false);
    leftMotor.setNeutralMode(NeutralMode.Brake);
    leftMotor.configPeakOutputForward(1.0);
    leftMotor.setInverted(true);
  }

  @Override
  public void periodic() {

   double signalInput = ioController.getRawAxis(1);

    SmartDashboard.putNumber("Signal Input", signalInput);
    SmartDashboard.putNumber("position", encoder.getPosition());
    SmartDashboard.putNumber("current", leftMotor.getSupplyCurrent());
    
   if(chooser.getSelected().equals(ControlMode.PercentOutput)){
    leftMotor.set(ControlMode.PercentOutput, signalInput);
    SmartDashboard.putNumber("power output", leftMotor.get());
   }
   else if(chooser.getSelected().equals(ControlMode.Velocity)){
     //Velocity takes a value in encoder ticks per 100 miliseconds
    leftMotor.set(ControlMode.Velocity, (signalInput*maxVelocity));
    SmartDashboard.putNumber("velocity encoder feedback", encoder.getVelocity());
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

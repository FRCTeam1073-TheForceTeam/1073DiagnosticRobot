/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorMap extends SubsystemBase {
  /**
   * Creates a new MotorMap.
   */
  private static SendableChooser<deviceDescriptor> chooser;
  class deviceDescriptor{
    String type;
    int ID;

    public deviceDescriptor(String type_, int ID_) {
      type = type_;
      ID = ID_;
    }

    public String getType(){
      return type;
    }

    public int getID(){
      return ID;
    }

  }

  ArrayList<deviceDescriptor> devices = new ArrayList<deviceDescriptor>();
  public MotorMap() {
    chooser = new SendableChooser<deviceDescriptor>();
  }

  public void DeviceChooser(){

    for(int i = 0; i < devices.size(); i++){
        String motorKey = "motor "+(i+1);
        if(devices[i].getType() == "Talon SRX"){
          chooser.setDefaultOption(motorKey, WPI_TalonSRX(devices[i].getID()));
        }
        else if(devices[i].getType() == "Talon FX"){
          chooser.setDefaultOption(motorKey, WPI_TalonFX(devices[i].getID()));
        } 
    }
  }

  public void discoverCanDevices() {
    devices.add(new deviceDescriptor("Talon FX", 22));
    devices.add(new deviceDescriptor("Talon FX", 23));
    devices.add(new deviceDescriptor("Talon SRX", 24));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}


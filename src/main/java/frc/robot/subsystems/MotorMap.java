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

  ArrayList<deviceDescriptor> devices;
  public MotorMap() {
    devices = new ArrayList<deviceDescriptor>();
    chooser = new SendableChooser<deviceDescriptor>();
  }

  public void DeviceChooser(){

    for(int i = 0; i < devices.size(); i++){
        String motorKey = "motor "+(i+1);
          chooser.setDefaultOption(motorKey, devices[i]);
    }

    while(chooser != null){
      deviceDescriptor choosen = chooser.getSelected();
      if(choosen.getType().equals("Talon SRX")){
        WPI_TalonSRX choosenMotor = new WPI_TalonSRX(choosen.getID());
      }
      else{
        WPI_TalonFX choosenMotor = new WPI_TalonFX(choosen.getID());
      }
    }
  }



  public void discoverCanDevices() {
    /**
     * currently requires manual adding of motors to device descriptor list. 
     * This will be fixed after basic functionality is demonstrated
     */
    devices.add(new deviceDescriptor("Talon FX", 22));
    devices.add(new deviceDescriptor("Talon FX", 23));
    devices.add(new deviceDescriptor("Talon SRX", 24));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}


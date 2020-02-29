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

public class ExampleSubsystem extends SubsystemBase {

  diagDashboard dashBoard;

  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem(diagDashboard _dashBoard) {
    dashBoard = _dashBoard;
  }

  @Override
  public void periodic() {
   HashMap<String, Double> hm = new HashMap();

   hm.put("Velocity",1.0);
   hm.put("Position",2.0);
   hm.put("PID Error",3.0);
   hm.put("Voltage",4.0);

   dashBoard.publish("first", hm);


    // This method will be called once per scheduler run
  }
}

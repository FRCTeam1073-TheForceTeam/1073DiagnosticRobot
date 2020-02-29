/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * Add your docs here.
 */
public class diagDashboardSmartDashboard implements diagDashboard{
   
    public void publish(String tab, HashMap<String, Double> metrics)
    {

        Iterator metricIterator = metrics.entrySet().iterator(); 

        while (metricIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)metricIterator.next(); 

            String metricName = (String)mapElement.getKey();
            double metricValue = (Double)mapElement.getValue();

            SmartDashboard.putNumber(metricName, metricValue);
        }

    }
}

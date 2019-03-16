package org.usfirst.frc.team3414.diagnostic;
import org.usfirst.frc.team3414.config.Config;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MatchTimer {
    static double level2Time = Config.LEVEL_2_TIME;
    static double level3Time = Config.LEVEL_3_TIME;

    private static Double getMatchTime() {
        return Timer.getMatchTime();
    }

    public static boolean enoughTimeForLevel2() {
        if (getMatchTime() > level2Time) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean enoughTimeForLevel3() {
        if (getMatchTime() > level3Time) {
            return true;
        } else {
            return false;

        }
    }

    public static void outputTime() {
      
        SmartDashboard.putString("Remaining Time:", getMatchTime().toString());

    }

}
package org.usfirst.frc.team3414.sensors;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Add your docs here.
 */
public class Lifecam {
    static UsbCamera camera;
    public static void init(){
        camera = CameraServer.getInstance().startAutomaticCapture(0);
       //    camera.setFPS(15);
     //       camera.setResolution(360, 240);
        }
    public static UsbCamera getCam(){
        return camera;
    }
    public static void startRear(){ //NOT IN USE
            camera.setFPS(15);
            camera.setResolution(360, 240);
            }
    public static void stream() {
        NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection").setString(camera.getName());
    }
    public static void stopRear(){ //TODO test this, possibly a BAD IDEA
        camera.free();
    }
        
    
}
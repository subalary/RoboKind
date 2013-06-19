package uk.ac.shef.vision.detection;

import javax.swing.JOptionPane;
import org.jflux.api.core.Listener;
import org.robokind.api.vision.ImageRegionList;
import org.robokind.api.vision.config.FaceDetectServiceConfig;
import org.robokind.api.vision.messaging.RemoteImageRegionServiceClient;
import org.robokind.client.basic.Robokind;
import org.robokind.api.motion.messaging.RemoteRobot;
import org.robokind.client.basic.UserSettings;

import uk.ac.shef.settings.SetSettings;

/**
 * Face detection demo.
 * @author Jason G. Pallack <jgpallack@gmail.com>
 */
public class FaceTest {
    private static RemoteRobot myRobot;
    public static void main(String[] args) {
        //Set the ip address of the robot below:
        String robotID = "myRobot";
        String robotIP = "192.168.0.54";
        // set addresses
        SetSettings settings = new SetSettings(robotID, robotIP);
        // make connection
        myRobot = Robokind.connectRobot();
        UserSettings.setImageRegionAddress(robotIP);
        UserSettings.setImageRegionId("0");
        
        RemoteImageRegionServiceClient<FaceDetectServiceConfig> regions =
                Robokind.connectImageRegionService();
        Listener<ImageRegionList> monitor = new FaceMonitor(robotIP);
        
        regions.addImageRegionsListener(monitor);
        
                String stop = JOptionPane.showInputDialog("Should I stop now? Y/N");
             stop = stop.toUpperCase();
            if (stop.equals("Y")) {
                Robokind.disconnect();
                System.exit(0);        
            }
    }
}
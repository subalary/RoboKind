package uk.ac.shef.basic;

import org.robokind.api.animation.Animation;
import org.robokind.api.animation.messaging.RemoteAnimationPlayerClient;
import org.robokind.api.animation.player.AnimationJob;
import org.robokind.api.common.position.NormalizedDouble;
import org.robokind.api.motion.Joint;
import org.robokind.api.motion.messaging.RemoteRobot;
import org.robokind.api.speech.messaging.RemoteSpeechServiceClient;
import org.robokind.client.basic.Robokind;
import org.robokind.client.basic.UserSettings;
import static org.robokind.api.motion.Robot.*;
import static org.robokind.client.basic.RobotJoints.*;
import uk.ac.shef.settings.SetSettings;

/**
 * App.java
 * @author Lianne Meah
 * @version 3.0
 */

public class App {
  
    private static RemoteRobot myRobot;
    private static RemoteAnimationPlayerClient myPlayer;
    private static RemoteSpeechServiceClient mySpeaker;
    private static RobotPositionMap myGoalPositions;
    
    public static void main(String[] args) {
        long animLen;
        
        ///////////////////////////////////////////
        /// CONFIG
        ///////////////////////////////////////////
        
        String robotID = "myRobot";
        String robotIP = "192.168.0.54";
        
        //////////////////////////////////////////////////////
        // SETTINGS - this is handled in SetSettings.java ////
        //////////////////////////////////////////////////////
        
        ///////////// use line 45 ////////////////////////////
        SetSettings settings = new SetSettings(robotID, robotIP);
        //////////// instead of 47-55 ////////////////////////
        UserSettings.setRobotId(robotID);
        UserSettings.setRobotAddress(robotIP);
        UserSettings.setAnimationAddress(robotIP);
        UserSettings.setSpeechAddress(robotIP);
        UserSettings.setSensorAddress(robotIP);
        UserSettings.setAccelerometerAddress(robotIP);
        UserSettings.setGyroscopeAddress(robotIP);
        UserSettings.setCompassAddress(robotIP);
        UserSettings.setCameraAddress(robotIP);
        //////////// End settings //////////////////////////////
        myRobot = Robokind.connectRobot();
        myPlayer = Robokind.connectAnimationPlayer();
        mySpeaker = Robokind.connectSpeechService();
  
        ///////////////////////////////////////////
        /// LOADING ANIMATIONS
        ///////////////////////////////////////////
        
        Animation introAnim = Robokind.loadAnimation("animations/avatar_wave.anim.xml");
        AnimationJob introJob = myPlayer.playAnimation(introAnim);
        animLen = introAnim.getLength();
        //Robokind.sleep(500 + animLen);
        
        ///////////////////////////////////////////
        /// SPEAKING
        ///////////////////////////////////////////
        
        // make the robot speak
        mySpeaker.speak("Hello.");
        Robokind.sleep(500 + animLen);
        
        ///////////////////////////////////////////
        /// MOVING THE JOINTS
        ///////////////////////////////////////////
        
        JointId left_elbow_yaw = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_ELBOW_YAW)); 
        JointId left_elbow_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_ELBOW_PITCH));
        
        myGoalPositions = new RobotPositionHashMap();
        myGoalPositions.put(left_elbow_yaw, new NormalizedDouble(0.38));
        myGoalPositions.put(left_elbow_pitch, new NormalizedDouble(0.99));
        myRobot.move(myGoalPositions, 1000);
        
        ///////////////////////////////////////////
        /// DISCONNECT AND EXIT
        ///////////////////////////////////////////
        
        Robokind.disconnect();
        System.exit(0);
    }
}
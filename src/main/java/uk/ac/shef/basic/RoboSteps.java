package uk.ac.shef.basic;
import org.robokind.api.common.position.NormalizedDouble;
import org.robokind.api.motion.Joint;
import org.robokind.api.motion.messaging.RemoteRobot;
import static org.robokind.api.motion.Robot.*;
import org.robokind.client.basic.Robokind;
import static org.robokind.client.basic.RobotJoints.*;

/**
 * RoboSteps.java
 * Inspired by the Hanson Robotics outdated API
 * A Java program to get our robot walking
 * @author Lianne Meah <lianne.meah@gmail.com>
 */
public class RoboSteps {
    // class variables
    private static RobotPositionMap myGoalPositions;
    private static RemoteRobot myRobot;
    private static int timeFrame;
    
    // left joints
    JointId left_hip_roll = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_HIP_ROLL));  
    JointId left_hip_yaw = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_HIP_YAW));
    JointId left_hip_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_HIP_PITCH));
    JointId left_knee_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_KNEE_PITCH));
    JointId left_ankle_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_ANKLE_PITCH));
    JointId left_ankle_roll = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_ANKLE_ROLL));
    // right joints
    JointId right_hip_roll = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_HIP_ROLL));  
    JointId right_hip_yaw = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_HIP_YAW));
    JointId right_hip_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_HIP_PITCH));
    JointId right_knee_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_KNEE_PITCH));
    JointId right_ankle_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_ANKLE_PITCH));
    JointId right_ankle_roll = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_ANKLE_ROLL));
}
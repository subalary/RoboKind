package uk.ac.shef.basic;
import javax.swing.JOptionPane;
import org.robokind.api.common.position.NormalizedDouble;
import org.robokind.api.motion.Joint;
import org.robokind.api.motion.messaging.RemoteRobot;
import static org.robokind.api.motion.Robot.*;
import org.robokind.client.basic.Robokind;
import static org.robokind.client.basic.RobotJoints.*;
import uk.ac.shef.expressions.Expression;
import uk.ac.shef.settings.SetSettings;

/**
 * GripDemo.java
 * Demonstrates robot's grip
 * @author Lianne Meah <lianne.meah@gmail.com>
 */
public class GripDemo {
    // class variables
    private static RobotPositionMap myGoalPositions;
    private static RemoteRobot myRobot;
    private static int timeFrame;
    
    // left joints
    JointId left_shoulder_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_SHOULDER_PITCH));
    JointId left_shoulder_roll = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_SHOULDER_ROLL));
    JointId left_elbow_yaw = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_ELBOW_YAW));
    JointId left_elbow_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_ELBOW_PITCH));
    JointId left_wrist_yaw = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_WRIST_YAW));
    JointId left_hand_grasp = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_HAND_GRASP));
    // right joints
    JointId right_shoulder_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_SHOULDER_PITCH));
    JointId right_shoulder_roll = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_SHOULDER_ROLL));
    JointId right_elbow_yaw = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_ELBOW_YAW));
    JointId right_elbow_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_ELBOW_PITCH));
    JointId right_wrist_yaw = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_WRIST_YAW));
    JointId right_hand_grasp = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_HAND_GRASP));

    /**
     * Main method for example use
     */
    public static void main(String[] args) {
        String robotID = "myRobot";
        String robotIP = "192.168.0.54";
        // set addresses
        SetSettings settings = new SetSettings(robotID, robotIP);
        // make connection
        myRobot = Robokind.connectRobot();
        if (myRobot.isConnected()) {
            // set up expression for facial expressions
            Expression expression = new Expression();
            expression.setExpressionJoints(myRobot);
            GripDemo grip = new GripDemo();
            // String for which arm should be used
            String arm = "L";               
            // move arm out
            grip.armOut(1000, arm);
            Robokind.sleep(2000);
            grip.openGrasp(500, arm);
            Robokind.sleep(2000);
            expression.lookUp(500, myRobot);
            Robokind.sleep(500);
            // make the robot smile
            expression.smile(500, myRobot);
            
            // control the robot's grip
            String shouldGrip = JOptionPane.showInputDialog("Have you given me something to hold? Y/N");
            shouldGrip = shouldGrip.toUpperCase();
            if (shouldGrip.equals("Y")) {
                // grip down
                grip.closeGrasp(500, arm);
                Robokind.sleep(500);
                // flail test
                grip.armUp(500, arm);
                Robokind.sleep(500);
                grip.elbowOut(500, arm);
                Robokind.sleep(3000);
            }
            
            // return to defaults to joints aren't strained
            grip.atDefaults(500);
            Robokind.sleep(1000);
            // also set joints used for expressions back to default
            expression.setExpressionDefaults(1000, myRobot);
            Robokind.sleep(5000);
        }
        
        // disconnnect and exit
        Robokind.disconnect();
        System.exit(0);
    }
    
    /**
     * Default constructor
     */
    public GripDemo() {
        
    }
    
    /**
     * method to extend the robot's arm
     * @param timeFrame the amount of time to move over
     * @param arm the arm to be used
     */
    public void armOut(int timeFrame, String arm) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        // move left arm if 0 is given
        arm = arm.toUpperCase();
        if (arm.equals("L") || arm.equals("LEFT")) {
            this.myGoalPositions.put(left_shoulder_pitch, new NormalizedDouble(0.5));
        }
        // move right arm if 1 is given
        else if (arm.equals("R") || arm.equals("RIGHT")) {
            this.myGoalPositions.put(right_shoulder_pitch, new NormalizedDouble(0.5));
        }
        // now move the arm
        myRobot.move(this.myGoalPositions, this.timeFrame); 
    }
    
        public void elbowOut(int timeFrame, String arm) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        // move left arm if 0 is given
        arm = arm.toUpperCase();
        if (arm.equals("L") || arm.equals("LEFT")) {
            this.myGoalPositions.put(left_elbow_yaw, new NormalizedDouble(0.2));
        }
        // move right arm if 1 is given
        else if (arm.equals("R") || arm.equals("RIGHT")) {
            this.myGoalPositions.put(right_elbow_yaw, new NormalizedDouble(0.2));
        }
        // now move the arm
        myRobot.move(this.myGoalPositions, this.timeFrame); 
    }
    
    /**
     * method to make robot grasp
     * @param timeFrame the amount of time to move over (milliseconds)
     * @param arm the arm to be used: left/right
     */
    public void openGrasp(int timeFrame, String arm) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        // move left arm if 0 is given
        arm = arm.toUpperCase();
        if (arm.equals("L") || arm.equals("LEFT")) {
            this.myGoalPositions.put(left_hand_grasp, new NormalizedDouble(1.0));
        }
        // move right arm if 1 is given
        else if (arm.equals("R") || arm.equals("RIGHT")) {
            this.myGoalPositions.put(right_hand_grasp, new NormalizedDouble(1.0));
        }
        // now move the arm
        myRobot.move(this.myGoalPositions, this.timeFrame);
    }
    
    /**
     * method to make robot grip something
     * @param timeFrame the amount of time to move over (milliseconds)
     * @param arm which arm to use (left/right)
     */
    public void closeGrasp(int timeFrame, String arm) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        // move left arm if 0 is given
        arm = arm.toUpperCase();
        if (arm.equals("L") || arm.equals("LEFT")) {
            this.myGoalPositions.put(left_hand_grasp, new NormalizedDouble(0.4));
        }
        // move right arm if 1 is given
        else if (arm.equals("R") || arm.equals("RIGHT")) {
            this.myGoalPositions.put(right_hand_grasp, new NormalizedDouble(0.4));
        }
        // now move the arm
        myRobot.move(this.myGoalPositions, this.timeFrame);
    }
    
    /**
     * method to make robot raise forearm
     * @param timeFrame the amount of time to move over (milliseconds)
     * @param arm which arm to use (left/right)
     */
    public void armUp(int timeFrame, String arm) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        // move left arm if 0 is given
        arm = arm.toUpperCase();
        if (arm.equals("L") || arm.equals("LEFT")) {
            this.myGoalPositions.put(left_elbow_pitch, new NormalizedDouble(0.9));
        }
        // move right arm if 1 is given
        else if (arm.equals("R") || arm.equals("RIGHT")) {
            this.myGoalPositions.put(right_elbow_pitch, new NormalizedDouble(0.9));
        }
        // now move the arm
        myRobot.move(this.myGoalPositions, this.timeFrame);
    }
    
    /**
     * Method to make robot move to defaults
     * @param timeFrame the amount of time to move joint over (milliseconds)
     */
    public void atDefaults(int timeFrame) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = myRobot.getDefaultPositions();
        myRobot.move(this.myGoalPositions, this.timeFrame);
    }
}
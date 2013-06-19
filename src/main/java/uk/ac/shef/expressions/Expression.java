package uk.ac.shef.expressions;
import org.robokind.api.common.position.NormalizedDouble;
import org.robokind.api.motion.Joint;
import org.robokind.api.motion.messaging.RemoteRobot;
import static org.robokind.api.motion.Robot.*;
import org.robokind.client.basic.Robokind;
import static org.robokind.client.basic.RobotJoints.*;

/**
 * Expression.java
 * @author Lianne Meah <lianne.meah@gmail.com>
 */
public class Expression {
    private static RobotPositionMap myGoalPositions;
    private static int timeFrame;
    private static JointId eyelids;
    private static JointId eyes_pitch;
    private static JointId left_eye_yaw;
    private static JointId right_eye_yaw;
    private static JointId left_smile;
    private static JointId right_smile;
    private static JointId jaw;
    private static JointId neck_pitch;
    private static JointId neck_yaw;
    private static JointId brows;

    /**
     * Default constructor
     */
    public Expression() {
        
    }
    
    /**
     * Set joints used for expression class
     * @param myRobot the connected remoteRobot 
     */
    public void setExpressionJoints(RemoteRobot myRobot) {
        this.eyelids = new JointId(myRobot.getRobotId(), new Joint.Id(EYELIDS)); 
        this.eyes_pitch = new JointId(myRobot.getRobotId(), new Joint.Id(EYES_PITCH)); 
        this.left_eye_yaw = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_EYE_YAW)); 
        this.right_eye_yaw  = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_EYE_YAW));
        this.left_smile = new JointId(myRobot.getRobotId(), new Joint.Id(LEFT_SMILE));
        this.jaw = new JointId(myRobot.getRobotId(), new Joint.Id(JAW));
        this.right_smile = new JointId(myRobot.getRobotId(), new Joint.Id(RIGHT_SMILE));
        this.neck_pitch  = new JointId(myRobot.getRobotId(), new Joint.Id(NECK_PITCH));
        this.neck_yaw = new JointId(myRobot.getRobotId(), new Joint.Id(NECK_YAW));
        this.brows = new JointId(myRobot.getRobotId(), new Joint.Id(BROWS));
    }
    
    /**
     * Method to make robot smile
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */
    public void smile(int timeFrame, RemoteRobot myRobot) {
        this.myGoalPositions = new RobotPositionHashMap();
        this.timeFrame = timeFrame;
        // manipulate the robot's eyes and mouth
        this.myGoalPositions.put(this.left_smile, new NormalizedDouble(1.0));
        this.myGoalPositions.put(this.right_smile, new NormalizedDouble(1.0));
        this.myGoalPositions.put(this.brows, new NormalizedDouble(0.6));
        this.myGoalPositions.put(this.eyelids, new NormalizedDouble(0.5));
        myRobot.move(this.myGoalPositions, this.timeFrame);
    }
    
    /**
     * Method to make robot frown
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */
    public void frown(int timeFrame, RemoteRobot myRobot) {
        this.myGoalPositions = new RobotPositionHashMap();
        this.timeFrame = timeFrame;
        // manipulate the robot's eyes and mouth
        this.myGoalPositions.put(left_smile, new NormalizedDouble(0.2));
        this.myGoalPositions.put(right_smile, new NormalizedDouble(0.2));
        this.myGoalPositions.put(brows, new NormalizedDouble(0.0));
        this.myGoalPositions.put(jaw, new NormalizedDouble(0.0));
        this.myGoalPositions.put(eyelids, new NormalizedDouble(0.5));
        myRobot.move(this.myGoalPositions, this.timeFrame); 
    }
    
    /**
     * Method to make robot close eyes
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */
    public void closeEyes(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        this.myGoalPositions.put(eyelids, new NormalizedDouble(0.0));
        myRobot.move(this.myGoalPositions, this.timeFrame); 
    }
    
    /**
     * Method to make robot open eyes
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */
    public void openEyes(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        this.myGoalPositions.put(eyelids, new NormalizedDouble(0.5));
        myRobot.move(this.myGoalPositions, this.timeFrame); 
    }
    
    /** 
     * Method to make robot blink
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */
    public void blink(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        this.myGoalPositions.put(eyelids, new NormalizedDouble(0.0));
        myRobot.move(this.myGoalPositions, this.timeFrame); 
        //Robokind.sleep(50);
        this.myGoalPositions.put(eyelids, new NormalizedDouble(0.5));
        myRobot.move(this.myGoalPositions, this.timeFrame); 
    }
    
    /** 
     * Method to make robot nod
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */    
    public void nod(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        this.myGoalPositions.put(neck_pitch, new NormalizedDouble(0.8));
        myRobot.move(this.myGoalPositions, this.timeFrame);
        Robokind.sleep(300);
        this.myGoalPositions.put(neck_pitch, new NormalizedDouble(0.2));
        myRobot.move(this.myGoalPositions, this.timeFrame);
        Robokind.sleep(300);
        this.myGoalPositions.put(neck_pitch, new NormalizedDouble(0.5));
        myRobot.move(this.myGoalPositions, this.timeFrame);
        Robokind.sleep(300);        
    }

     /** 
     * Method to make robot shake it's head 
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */  
    public void shakeHead(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        this.myGoalPositions.put(neck_yaw, new NormalizedDouble(0.8));
        myRobot.move(this.myGoalPositions, this.timeFrame);
        Robokind.sleep(300);
        this.myGoalPositions.put(neck_yaw, new NormalizedDouble(0.2));
        myRobot.move(this.myGoalPositions, this.timeFrame);
        Robokind.sleep(300);
        this.myGoalPositions.put(neck_yaw, new NormalizedDouble(0.5));
        myRobot.move(this.myGoalPositions, this.timeFrame);
        Robokind.sleep(500);        
    }
    
     /** 
     * Method to make robot look up
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */    
    public void lookUp(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        this.myGoalPositions.put(neck_pitch, new NormalizedDouble(1.0));
        myRobot.move(this.myGoalPositions, this.timeFrame);
    }
    
    /** 
     * Method to make robot look up slightly
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */    
    public void gentleLookUp(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        this.myGoalPositions.put(neck_pitch, new NormalizedDouble(0.8));
        myRobot.move(this.myGoalPositions, this.timeFrame);
    }
    
    /** 
     * Method to make robot look down
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */ 
    public void lookDown(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        this.myGoalPositions.put(neck_pitch, new NormalizedDouble(0.2));
        myRobot.move(this.myGoalPositions, this.timeFrame); 
    }
    
    /** 
     * Method to make robot look down
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */ 
    public void gentleLookDown(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = new RobotPositionHashMap();
        this.myGoalPositions.put(neck_pitch, new NormalizedDouble(0.4));
        myRobot.move(this.myGoalPositions, this.timeFrame); 
    }
    
     /**
     * Method to make robot move to defaults
     * @param timeFrame the amount of time to move joint over (milliseconds)
     * @param myRobot the connected RemoteRobot
     */
    public void setExpressionDefaults(int timeFrame, RemoteRobot myRobot) {
        this.timeFrame = timeFrame;
        this.myGoalPositions = myRobot.getDefaultPositions();
        // push joints back to defaults
        myRobot.move(this.myGoalPositions, this.timeFrame);
    }
}
package uk.ac.shef.basic;
import org.robokind.api.motion.messaging.RemoteRobot;
import static org.robokind.api.motion.Robot.*;
import org.robokind.client.basic.Robokind;
import uk.ac.shef.settings.SetSettings;
import uk.ac.shef.expressions.Expression;

/**
 * ExpressionDemo.java
 * Class to demonstrate how to use expressions in Expression.java
 * @author Lianne Meah <lianne.meah@gmail.com>
 */
public class ExpressionDemo {
    // class variables
    private static RobotPositionMap myGoalPositions;
    private static RemoteRobot myRobot;
    private static int timeFrame;
    public static void main(String[] args) {
        String robotID = "myRobot";
        String robotIP = "192.168.0.54";
        // set addresses
        SetSettings settings = new SetSettings(robotID, robotIP);
        // make connection
        myRobot = Robokind.connectRobot();
        // check if robot is connected
        if (myRobot.isConnected()) {
            // set up expression joints
            Expression expression = new Expression();
            expression.setExpressionJoints(myRobot);
            // expression test
            expression.gentleLookDown(400, myRobot);
            Robokind.sleep(200);
            expression.smile(200, myRobot);
            Robokind.sleep(200);
            expression.closeEyes(200, myRobot);
            Robokind.sleep(300);
            expression.shakeHead(300, myRobot);
            Robokind.sleep(200);
            expression.openEyes(200, myRobot);
            Robokind.sleep(300);                
            expression.gentleLookUp(400, myRobot);
            Robokind.sleep(200);
            expression.nod(400, myRobot);
            Robokind.sleep(200); 
        }
    }  
}
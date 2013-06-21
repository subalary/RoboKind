package uk.ac.shef.basic;
import javax.swing.*;
import org.robokind.api.animation.Animation;
import org.robokind.api.animation.messaging.RemoteAnimationPlayerClient;
import org.robokind.api.motion.messaging.RemoteRobot;
import org.robokind.api.speech.messaging.RemoteSpeechServiceClient;
import org.robokind.client.basic.*;
import static org.robokind.api.motion.Robot.*;
import org.robokind.api.motion.protocol.RobotDefinitionResponse.JointDefinition;
import uk.ac.shef.expressions.Expression;
import uk.ac.shef.settings.SetSettings;

/**
 * Demo.java; a nice little demo 
 * Works with robot and standalone avatar
 * @author Lianne
 */
public class Demo {
    // class variables
    private static RemoteRobot myRobot;   
    private static JointDefinition def;
    private static RemoteAnimationPlayerClient myPlayer;
    private static RemoteSpeechServiceClient mySpeaker;
    private static RobotPositionMap myGoalPositions;
    
    public static void main(String[] args) {
        long animLen;
        // config and settings - set robotID and IP 
        // Not needed for avatar(uncomment for robot)
        //String robotID = "myRobot"; 
        //String robotIP = "192.168.0.54";
        
        // set respective addresses
        // Not needed for avatar (uncomment for robot)
        //SetSettings settings = new SetSettings(robotID, robotIP);
        // try to make connections
        myRobot = Robokind.connectRobot();
        myPlayer = Robokind.connectAnimationPlayer();
        mySpeaker = Robokind.connectSpeechService();

        String command = "";
        while (!command.equals("STOP")) {
            // give Zeno a command, from
            command = JOptionPane.showInputDialog("Give me a command: SPEAK, DANCE, ANIM, STOP");
            command = command.toUpperCase();
            String speech = "";
            if (command.equals("SPEAK")) {
                while (!speech.equals("0")) {
                    myGoalPositions = myRobot.getDefaultPositions();
                    // push joints back to defaults
                    myRobot.move(myGoalPositions, 1000);
                    speech = JOptionPane.showInputDialog("What shall I say? (0 to stop)");
                    if (!speech.equals("0")) {
                        Expression expression = new Expression();
                        expression.setExpressionJoints(myRobot);
                        expression.lookUp(400, myRobot);
                        Robokind.sleep(200);
                        expression.smile(200, myRobot);
                        Robokind.sleep(200);
                        expression.blink(400, myRobot);
                        Robokind.sleep(200);
                        mySpeaker.speak(speech);
                    }
                }
            }
            
            String dance = "";
            if (command.equals("DANCE")) {
                while (!dance.equals("stop")) {
                    dance = JOptionPane.showInputDialog("Which dance? (Pick 1-5 or 'stop')");
                    if (dance.equals("1")) {
                        Animation danceAnim1 = Robokind.loadAnimation("all/AZR50_Dancing_01.anim.xml");
                        myPlayer.playAnimation(danceAnim1);
                        animLen = danceAnim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }
                    if (dance.equals("2")) {
                        Animation danceAnim1 = Robokind.loadAnimation("all/AZR50_Dancing_02.anim.xml");
                        myPlayer.playAnimation(danceAnim1);
                        animLen = danceAnim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }
                    if (dance.equals("3")) {
                        Animation danceAnim1 = Robokind.loadAnimation("all/AZR50_Dancing_03.anim.xml");
                        myPlayer.playAnimation(danceAnim1);
                        animLen = danceAnim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }
                    if (dance.equals("4")) {
                        Animation danceAnim1 = Robokind.loadAnimation("all/AZR50_Dancing_04.anim.xml");
                        myPlayer.playAnimation(danceAnim1);
                        animLen = danceAnim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }
                    if (dance.equals("5")) {
                        Animation danceAnim1 = Robokind.loadAnimation("all/AZR50_Dancing_05.anim.xml");
                        myPlayer.playAnimation(danceAnim1);
                        animLen = danceAnim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (dance.equals("stop") || dance.equals("STOP")) {
                        // do nothing
                    }
                }
            }
            
            if (command.equals("ANIM")) {
                String anim = "";
                while (!anim.equals("stop")) {
                    anim = JOptionPane.showInputDialog("What shall I do? (Pick 1-15 or 'stop')");
                    if (anim.equals("1")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Aggressive_01.anim.xml");
                        System.out.println("Playing 'aggresive'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("2")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Appreciation_02.anim.xml");
                        System.out.println("Playing 'appreciation'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("3")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Astonished_02.anim.xml");
                        System.out.println("Playing 'astonished'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("4")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Cheer_FistPump_01.anim.xml");
                        System.out.println("Playing 'cheer'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("5")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Conceited_01.anim.xml");
                        System.out.println("Playing 'conceited'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("6")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Handshake_02.anim.xml");
                        System.out.println("Playing 'handshake' (lol I'll hit myself in the face).");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("7")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_InLove_02.anim.xml");
                        System.out.println("Playing 'in love'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("8")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Listening_05.anim.xml");
                        System.out.println("Playing 'listening'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("9")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Offended_02.anim.xml");
                        System.out.println("Playing 'offended'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("10")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Quizzical_02.anim.xml");
                        System.out.println("Playing 'quizzical'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("11")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Tired_01.anim.xml");
                        System.out.println("Playing 'tired'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }
                    if (anim.equals("12")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Relieved_02.anim.xml");
                        System.out.println("Playing 'relieved'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("13")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Shy_02.anim.xml");
                        System.out.println("Playing 'shy'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("14")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_Tired_02.anim.xml");
                        System.out.println("Playing 'tired (2)'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("15")) {
                        Animation anim1 = Robokind.loadAnimation("all/AZR50_VictoryPose_01.anim.xml");
                        System.out.println("Playing 'victory pose'.");
                        myPlayer.playAnimation(anim1);
                        animLen = anim1.getLength();
                        Robokind.sleep(2000 + animLen);
                    }

                    if (anim.equals("stop") || anim.equals("STOP")) {
                        // do nothing
                    }

                    // back to defaults
                    myGoalPositions = myRobot.getDefaultPositions();
                    // push joints back to defaults
                    myRobot.move(myGoalPositions, 1000);
                   }
                }
                
        }
        
        Robokind.disconnect();
        System.exit(0);
    }
}

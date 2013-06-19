package uk.ac.shef.basic;

import org.robokind.api.motion.messaging.RemoteRobot;
import org.robokind.client.basic.*;
import org.jflux.api.core.Listener;
import org.robokind.api.sensor.*;
import org.robokind.avrogen.sensor.*;
import org.robokind.api.sensor.gpio.RemoteGpioServiceClient;
import org.robokind.api.sensor.imu.*;
import uk.ac.shef.settings.SetSettings;

public class SensorsTest {
    // variables for class
    private static RemoteRobot myRobot;
    private static RemoteGpioServiceClient mySensors;
    private static RemoteAccelerometerServiceClient accel;
    private static RemoteGyroscopeServiceClient gyro;
    private static RemoteCompassServiceClient compass;
    
    public static void main(String[] args) {
        // config and settings - set robotID and IP 
        String robotID = "myRobot";
        String robotIP = "192.168.0.54";
        // set respective addresses
        SetSettings settings = new SetSettings(robotID, robotIP);
        // make connections
        myRobot = Robokind.connectRobot();
        mySensors = Robokind.connectSensors();
        
        RemoteGpioServiceClient sensors = Robokind.connectSensors();
        DeviceReadPeriodEvent<HeaderRecord> readPeriod =
                new DeviceReadPeriodRecord();
        HeaderRecord header = new HeaderRecord();

        header.setFrameId(0);
        header.setSequenceId(0);
        header.setTimestamp(0L);
        readPeriod.setHeader(header);
        readPeriod.setPeriod(100.0);
        sensors.setReadPeriod(readPeriod);

        System.out.println("Adding listener.");
        sensors.addListener(new TestGpioListener());
        System.out.println("Adding IMU.");

        // connect accelorometer, gyroscope and compass
        accel = Robokind.connectAccelerometer();
        gyro = Robokind.connectGyroscope();
        compass = Robokind.connectCompass();

        // set read period for them all
        readPeriod.setPeriod(1000.0);
        accel.setReadPeriod(readPeriod);
        gyro.setReadPeriod(readPeriod);
        compass.setReadPeriod(readPeriod);

        // config records for the three?
        AccelerometerConfigRecord accelConfig = new AccelerometerConfigRecord();
        GyroConfigRecord gyroConfig = new GyroConfigRecord();
        CompassConfigRecord compassConfig = new CompassConfigRecord();
        // setting headers for all three
        accelConfig.setHeader(header);
        gyroConfig.setHeader(header);
        compassConfig.setHeader(header);

        // accelorometer address and values
        accelConfig.setRegisterAddress(45);
        accelConfig.setRegisterValue(8);
        // gyro config - no idea what this does
        gyroConfig.setCtl1(15);
        gyroConfig.setCtl2(-1);
        gyroConfig.setCtl3(-1);
        gyroConfig.setCtl4(-1);
        gyroConfig.setCtl5(-1);
        // setting up compass config - no idea about this either
        compassConfig.setAverage(3);
        compassConfig.setBias(0);
        compassConfig.setGain(7);
        compassConfig.setRate(2);

        // confirm configs and send
        accel.sendConfig(accelConfig);
        gyro.sendConfig(gyroConfig);
        compass.sendConfig(compassConfig);
        
        // test these guys
        accel.addListener(new TestAccelListener());
        gyro.addListener(new TestGyroListener());
        compass.addListener(new TestCompassListener());
        
        // end of program - disconnect and exit
        Robokind.disconnect();
        System.exit(0);     
    }
    
    /*
     * classes to test sensors
     */
    public static class TestGpioListener implements Listener<DeviceBoolEvent> {
        public void handleEvent(DeviceBoolEvent t) {
            System.out.println(t.getChannelId() + ": " +
                    (t.getBoolValue() ? "on" : "off"));
        }
    }   
    public static class TestAccelListener
        implements Listener<FilteredVector3Event> {
        public void handleEvent(FilteredVector3Event t) {
            Vector3Event v = t.getFilteredVector();
            Vector3Event r = t.getRawVector();
            System.out.println("Accelerometer (f): " + v.getX() + ", " +
                    v.getY() + ", " + v.getZ());
            System.out.println("Accelerometer (r): " + r.getX() + ", " +
                    r.getY() + ", " + r.getZ());
        }
    }
    public static class TestGyroListener
        implements Listener<FilteredVector3Event> {
        public void handleEvent(FilteredVector3Event t) {
            Vector3Event v = t.getFilteredVector();
            Vector3Event r = t.getRawVector();
            System.out.println("Gyroscope (f): " + v.getX() + ", " +
                    v.getY() + ", " + v.getZ());
            System.out.println("Gyroscope (r): " + r.getX() + ", " +
                    r.getY() + ", " + r.getZ());
        }
    }
    public static class TestCompassListener
        implements Listener<FilteredVector3Event> {
        public void handleEvent(FilteredVector3Event t) {
            Vector3Event v = t.getFilteredVector();
            Vector3Event r = t.getRawVector();
            System.out.println("Compass (f): " + v.getX() + ", " +
                    v.getY() + ", " + v.getZ());
            System.out.println("Compass (r): " + r.getX() + ", " +
                    r.getY() + ", " + r.getZ());
        }
    }
    
    /**
     * Method to return all sensor pin directions and values
     * @param sensors - sensors client obtained upon connecting sensors
     */
    public void getAllPins(RemoteGpioServiceClient sensors) {

        System.out.println("PRINTING ALL PIN DIRECTIONS ");
        System.out.println("Left foot kick: "+sensors.getPinDirection(0));
        System.out.println("Left foot ground: "+sensors.getPinDirection(1));
        System.out.println("Left foot heel: "+sensors.getPinDirection(2));
        System.out.println("Right foot kick: "+sensors.getPinDirection(3));
        System.out.println("Right foot ground: "+sensors.getPinDirection(4));
        System.out.println("Right foot heel: "+sensors.getPinDirection(5));
        System.out.println("Left hip proximity: "+sensors.getPinDirection(9));
        System.out.println("Right hip proximity: "+sensors.getPinDirection(11));
        
        System.out.println("PRINTING ALL PIN VALUES ");
        System.out.println("Left foot kick: "+sensors.getPinValue(0));
        System.out.println("Left foot ground: "+sensors.getPinValue(1));
        System.out.println("Left foot heel: "+sensors.getPinValue(2));
        System.out.println("Right foot kick: "+sensors.getPinValue(3));
        System.out.println("Right foot ground: "+sensors.getPinValue(4));
        System.out.println("Right foot heel: "+sensors.getPinValue(5));
        System.out.println("Left hip proximity: "+sensors.getPinValue(9));
        System.out.println("Right hip proximity: "+sensors.getPinValue(11));
    }
}
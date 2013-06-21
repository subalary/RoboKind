# RoboKind

Java project which uses the RoboKind Basic API; for use with R50 RoboKind-based robot.

## Packages

### **basic** ###
Contains classes with main methods for testing and example use. App.java is the most basic example, VisitorDemo.java
builds on this. ExpressionDemo shows how to use the Expression class; GripDemo is a basic example which demonstrates
the robot's grip. SensorsTest.java is the RoboKind example for connecting to the robot's sensors. JointTest.
java is a class which demonstrates a joint's movements (between 0 and 1) and then sets it back to default. Finally, 
Demo.java is an example class which works with the standalone avatar.


#### Classes: 
* App.java
* ExpressionsDemo.java
* GripDemo.java
* JointTest.java
* SensorsTest.java
* VisitorDemo.java
* Demo.java

### **expressions** ###
Contains a class with simple expressions that can be used.

#### Classes:
* Expression.java

### **settings** ###
Contains settings and config class.

#### Classses:
* SetSettings.java

### **vision.detection** ###
Contains example code for use with face detection and tracking. Requires the native binaries to be run. This example
should work but currently doesn't.

#### Classes:
* FaceMonitor.java
* FaceTest.java

***
#### Authors

**Lianne Meah** [(Subalary)](http://www.twitter.com/subalary)
FaceMonitor.java/FaceTest.java by [Jason Pallack](mailto:jgpallack@gmail.com)

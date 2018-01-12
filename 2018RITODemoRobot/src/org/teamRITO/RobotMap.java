package org.teamRITO;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * 
 * ***************************************************************
 * Note there are two choices you need to make in this file.
 * You must choose at the lines marked 'XXXXX CHOICE XXXXX'
 * The first is the controllers you are using. There is code for PWM talons. This should work unchanged for SPARC or Victor controllers too.
 * The second is the driving style you are using. There are three choices allowed, arcade, tank or curve.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	// Device configuration
	public static final int DRIVER_GAMEPAD_ID = 0;
	public static final int OPERATOR_GAMEPAD_ID = 1;
	
	/* Drive Subsystem
	 * The robot has a single motor on each side.
	 * these motors are controlled by motor controllers. There are various different types.
	 * We provide example for PWM driven controllers
	 */
	
	/*
	 * PWM example. The ID is the PWM port on the RoboRIO we plug the talon into.
	 * The PWM controllers do not provide any feedback. All feedback has to be through external sensors.
	 */
	public static final int DRIVE_LEFT_PWM_ID = 0;			// PWM channel for LEFT side
	public static final int DRIVE_RIGHT_PWM_ID = 2;			// PWM channel for RIGHT side.
	
	/*
	 * Drive style choices.
	 */
	public enum DriveStyle {
		DRIVE_STYLE_ARCADE,			// use arcade drive - left joystick = forward/back, right joystick = left/right.
		DRIVE_STYLE_TANK,			// use tank drive - left joystick = left wheels, right joystick = right wheels.
		DRIVE_STYLE_CURVE 			// use curve drive
	}
	public static final DriveStyle DRIVE_STYLE = DriveStyle.DRIVE_STYLE_TANK; // XXXXX CHOICE XXXXX pick your drive style here.
	
	// Widget subsystem
	// The demo robot controls a widget through a motor and speed controller (talon or whatever!)
	// We can make the motor move 'up' or 'down'. the speed we make it move is controlled here:
	public static final int WIDGET_CONTROLLER_ID = 3;
	public static final double WIDGET_SPEED_UP = 0.1;		// UP speed of widget (from 0 to 1)
	public static final double WIDGET_SPEED_DOWN = -0.1;	// DOWN speed of the widget (from 0 to -1)
}

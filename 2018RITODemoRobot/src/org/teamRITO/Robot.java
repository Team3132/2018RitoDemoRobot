/*
 * This is a demonstration robot code for the RITO teams. It provides a generic drive robot with a widget.
 * This code will be updated for the 2018 robot code release and made available for the RITO teams.
 * 
 * It has one choice to be made: the type of drive config desired. The WPIlib provides three styles, arcade, tank or curve.
 * All three are made available in the drive subsystem, and configurable through the parameter passed during the drive subsystem's creation.
 * 
 * We also provide a example widget that is controlled by the joysticks. This can be an arm, or some other mechanism.
 * Note, there is no feedback loop for the widget, so it can move in an unconstrained fashion.
 * 
 * Peripheral sensors can be added, and other features can be added to extend the functionality of the robot.
 */

package org.teamRITO;

import org.teamRITO.commands.AutoMovementGroupExample1;
import org.teamRITO.commands.AutoMovementGroupExample2;
import org.teamRITO.commands.AutoMovementGroupExample3;
import org.teamRITO.subsystems.DriveSubsystem;
import org.teamRITO.subsystems.WidgetSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private DriveSubsystem driveSubsystem;
	private WidgetSubsystem widgetSubsystem;
	@SuppressWarnings("unused")
	private static OI oi;
	private Joystick driverGamePad, operatorGamePad;
	private SpeedController leftController, rightController;	// left and right motor controllers
	private SpeedController widgetController;					// widget motor controllers
	private DifferentialDrive drive;
    Command autonomousCommand;
    SendableChooser<Command> chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        driverGamePad = new Joystick(RobotMap.DRIVER_GAMEPAD_ID);
        operatorGamePad = new Joystick(RobotMap.OPERATOR_GAMEPAD_ID);
        
	    // This is how you create controllers that use PWM ports on the RoboRIO.
        // if you have Victors, Sparks, or Talons
	    leftController = new Spark(RobotMap.DRIVE_LEFT_PWM_ID);
	    rightController = new Spark(RobotMap.DRIVE_RIGHT_PWM_ID);
	    // OR
	    //leftController = new VictorSP(RobotMap.DRIVE_LEFT_PWM_ID);
	    //rightController = new VictorSP(RobotMap.DRIVE_RIGHT_PWM_ID);
	    // OR
	    //leftController = new Talon(RobotMap.DRIVE_LEFT_PWM_ID);
	    //rightController = new Talon(RobotMap.DRIVE_RIGHT_PWM_ID);
	    // If you have multiple motors on each side create the ones you want then combine using a SpeedControllerGroup
	    
        // use the controllers to create a drive class that will take the move and turn values and pass the correct power to each motor controller.
    	drive = new DifferentialDrive(new SpeedControllerGroup(leftController), new SpeedControllerGroup(rightController));
    	
    	// the drive subsystem is the wrapper code that ties the joysticks and the drive class together. We also specify the drive style desired.
        driveSubsystem = new DriveSubsystem(() -> driverGamePad.getRawAxis(1), () -> driverGamePad.getRawAxis(5), drive, RobotMap.DRIVE_STYLE);
        
        /*
         *  create a widget subsystem. This is code that controls some widget. In the example code it is just a simple motor.
         *  The widgetController is the motor, and needs to be created for the subsystem to manipulate.
         *  
         *  Here we use the Victor SP, a PWM controller that will be available. Yes, we can mix and match our motor controllers,
         *  although it is better to use the same controller when working in groups (such as left and right on the drive train.
         */
        widgetController = new VictorSP(RobotMap.WIDGET_CONTROLLER_ID);
        widgetSubsystem = new WidgetSubsystem(widgetController);

        chooser = new SendableChooser<Command>();
        chooser.addDefault("Default Auto", new AutoMovementGroupExample1(driveSubsystem));
        chooser.addObject("Group 2 Description", new AutoMovementGroupExample2(driveSubsystem));
        chooser.addObject("Group 3 Description", new AutoMovementGroupExample3(driveSubsystem, widgetSubsystem));
        SmartDashboard.putData("Auto mode", chooser);

        /*
         * The Operator Interface is the code that we use to bind the joystick buttons to the various actions on the robot.
         * The operator interface registers the buttons and their associated commands. When the button is pressed/held/or released the associated command
         * is "fired" or made ready to run. The command will then be executed until it completes, or it is interupted.
         */
		oi = new OI(driverGamePad, operatorGamePad, widgetSubsystem);		// create operator interface.
    }
    
    /**
     * Robot periodic is called each time there is a packet received from the driver's station.
     * It is an excellent place to put code that needs to be run every so often.
     * 
     * In this case we ask the framework to run any scheduled commands.
     */
    public void robotPeriodic() {
        Scheduler.getInstance().run();
    }
    
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
        
        // Fetch the autonomous randomisation data from the FMS message. This will allow us to determine which sides are ours.
        String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		// The String is three L or R characters, one for each of the alliance switch, scale, other alliance switch.
		// See http://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/826278-2018-game-data-details for details.
		System.out.println("GameData: " + gameData);
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        driveSubsystem.setEnabled(true);		// enable drive subsystem for teleop.
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        // the drive subsystem is called from the subsystem's default command which is automatically run by the framework. We don't need to call it directly here.
    }
    
    public void testInit() {
    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}

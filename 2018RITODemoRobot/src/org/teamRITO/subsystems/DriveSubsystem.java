
package org.teamRITO.subsystems;

import java.util.function.DoubleSupplier;

import org.teamRITO.RobotMap;
import org.teamRITO.commands.DefaultDriveCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is the drive subsystem. It is the code that is wrapped around the robot drive code.
 * This subsystem is created with a default command. the default command calls 'drive()' to prod the motors
 * into acting as the joysticks direct.
 * 
 * During autonomous the drive subsystem is disabled. This locks out the operator joysticks.
 * Instead the autoDrive() entry point is called from the autonomous commands.
 * At the start of teleop we enable the drive subsystem
 */
public class DriveSubsystem extends Subsystem {
	private DifferentialDrive drive;		// drive code
	private DoubleSupplier left;
    private DoubleSupplier right;
    private RobotMap.DriveStyle driveType = RobotMap.DriveStyle.DRIVE_STYLE_ARCADE;
    private boolean enabled;
    
	/**
	 * Constructor for the drive subsystem. We call this to build the drive subsystem
	 * @param left
	 * @param right
	 * @param drive
	 * @param driveType
	 */
    public DriveSubsystem(DoubleSupplier left, DoubleSupplier right, DifferentialDrive drive, RobotMap.DriveStyle driveType) {
    	this.left = left;
    	this.right = right;
    	this.drive = drive;
    	this.driveType = driveType;
    	enabled = false;
    }
    
    public void autoDrive(double forwardPower, double turnPower) {
    	drive.arcadeDrive(forwardPower, turnPower);
    }
    
    public void drive() {
    	if (!enabled) {
    		return;		// do nothing when not enabled. (during autonomous or disabled modes).
    	}
    	/*
    	 *  Note: The joysticks return -1 as forward, but drive wants 1 as forward, so we need to reverse the sign of the forward component.
    	 *  
    	 *  If you are using a gamepad as the driver's controller then you will want to use one joystick, and read the two thumb sticks from it instead.
    	 */
    	switch (driveType) {
    	case DRIVE_STYLE_ARCADE:
    	default:
    		//drive.arcadeDrive(-leftJoystick.getY(), rightJoystick.getX(), true);
    		drive.arcadeDrive(left.getAsDouble(), right.getAsDouble(), true);
    		break;
    	case DRIVE_STYLE_TANK:
    		System.out.printf("Drive(%f, %f)\n", left.getAsDouble(), right.getAsDouble());
    		//drive.tankDrive(-leftJoystick.getY(), -rightJoystick.getY(), true);
    		drive.tankDrive(left.getAsDouble(), right.getAsDouble(), true);
    		break;
    	case DRIVE_STYLE_CURVE:
    		drive.curvatureDrive(left.getAsDouble(), right.getAsDouble(), false);
    		break;
    	}
    }
    
    public void setEnabled(boolean enabled) {
    	this.enabled = enabled;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DefaultDriveCommand(this));
    }
}


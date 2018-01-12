
package org.teamRITO.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.teamRITO.subsystems.DriveSubsystem;

/**
 * This is the default drive command. It is called whenever nothing else wants to use the drive subsystem.
 * It tells the drive subsystem to sample the joysticks and set the motor power accordingly.
 * 
 * It never ends, as we don't want the robot to just drift off without control. If another command needs the
 * drive subsystem then this command is interrupted and will restart when the interrupting command has finished.
 */
public class DefaultDriveCommand extends Command {
	DriveSubsystem driveSubsystem;
	
    public DefaultDriveCommand(DriveSubsystem driveSubsystem) {
        // Use requires() here to declare subsystem dependencies
    	this.driveSubsystem = driveSubsystem;
        requires(driveSubsystem);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveSubsystem.drive();		// tell the robot to drive!
    }

    protected boolean isFinished() {
        return false;				// never finish, so isFinished never returns true
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

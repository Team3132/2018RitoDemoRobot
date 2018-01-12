package org.teamRITO.commands;

import org.teamRITO.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This is an auto command to make the robot move during autonomous.
 * NOTE: This command is designed to be chained together into a sequential set of movements,
 * this means the robot will not stop at the end of the chain. We must explicitly put a
 * AutoMove(...,..., 0, 0, 0); as the last entry in the chain.
 * 
 * The drive subsystem default Command must be disabled while this command is running.
 * 
 * To make the robot move we call drive.arcadeDrive(move, turn) with the ranges -1 to 1.
 * When the time has finished we exit.
 */
public class AutoMoveCommand extends Command {
	private DriveSubsystem driveSubsystem;
	private double startTime;
	private double runTime;
	private double turnPower;
	private double forwardPower;
	
	/**
	 * AutoMove: move the robot during autonomous period
	 * @param driveSubsystem The drive subsystem for controlling the motors
	 * @param forwardPower How much to move forward or back (-1 full back to +1 full forward)
	 * @param turnPower How much to turn left or right (-1 full left to +1 full right)
	 * @param runTime How long to perform this action in seconds.
	 */
    public AutoMoveCommand(DriveSubsystem driveSubsystem, double forwardPower, double turnPower, double runTime) {
    	this.driveSubsystem = driveSubsystem;
    	this.turnPower = turnPower;
    	this.forwardPower = forwardPower;
    	this.runTime = runTime;
    	requires(driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveSubsystem.autoDrive(forwardPower, turnPower);		// Ask the drivebase to move.
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    // We do NOT stop the robot when the command finishes, this allows us to chain commands together
    protected boolean isFinished() {
    	double now = Timer.getFPGATimestamp();
        if (now > (startTime + runTime)) {
        	return true;
        }
        return false;					// not finished yet
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.teamRITO.commands;

import org.teamRITO.subsystems.WidgetSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Example command to control a widget.
 * This command is called from the Operator Interface (OI) on button presses or releases.
 * It can also be called from an autonomous group.
 * 
 * This command takes a time, and moves the widget for that period of time.
 * Afterwards the default widget command runs which stops the widget.
 */
public class WidgetMoveDownCommand extends Command {
	WidgetSubsystem widgetSubsystem;
	private double startTime;
	private double runTime;
	
    public WidgetMoveDownCommand(WidgetSubsystem widgetSubsystem, double runTime) {
    	this.widgetSubsystem = widgetSubsystem;
    	this.runTime = runTime;
    	requires(widgetSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	widgetSubsystem.setMoveDown();
    }

    // Make this return true when this Command no longer needs to run execute()
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

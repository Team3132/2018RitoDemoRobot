package org.teamRITO.commands;

import org.teamRITO.subsystems.WidgetSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Example command to control a widget from the joysticks.
 * This command is called from the Operator Interface (OI) on button presses or releases.
 */
public class WidgetStopCommand extends Command {
	WidgetSubsystem widgetSubsystem;
	
    public WidgetStopCommand(WidgetSubsystem widgetSubsystem) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.widgetSubsystem = widgetSubsystem;
    	requires(widgetSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	widgetSubsystem.setMoveStop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

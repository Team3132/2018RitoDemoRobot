package org.teamRITO.commands;

import org.teamRITO.subsystems.WidgetSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This is the default command for the widget. If no other command is running
 * that requires the widget then this command runs. It's purpose is to continue
 * providing a speed setting for the speed controller. if we do not provide a
 * speed update the motor safety code will stop the motor. In this case the
 * default command just halts the motor.
 * 
 * We do not call this command directly. It is called automatically by the
 * framework from the 'setDefaultCommand' declaration in the subsystem. When
 * another command wants to use the WidgetSubsystem our interrupted() method is
 * called and we end. When no other command wants to the the WidgetSubsystem we
 * are restarted.
 */
public class DefaultWidgetCommand extends Command {
	WidgetSubsystem widgetSubsystem;

	public DefaultWidgetCommand(WidgetSubsystem widgetSubsystem) {
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
		widgetSubsystem.setMoveStop(); // by default we stop the widget!
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

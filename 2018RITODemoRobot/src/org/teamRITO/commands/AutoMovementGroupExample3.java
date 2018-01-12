package org.teamRITO.commands;

import org.teamRITO.subsystems.DriveSubsystem;
import org.teamRITO.subsystems.WidgetSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Example Group to perform a sequence of moves during autonomous period.
 */
public class AutoMovementGroupExample3 extends CommandGroup {

    public AutoMovementGroupExample3(DriveSubsystem driveSubsystem, WidgetSubsystem widgetSubsystem) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	requires(driveSubsystem);
    	addSequential(new AutoMoveCommand(driveSubsystem, 0.8, 0.0, 1.0));	// move straight forward at 80% power for 1 second
    	addSequential(new AutoMoveCommand(driveSubsystem, 0.0, -0.3, 1.0));	// turn left at 30% power for 1 second
    	addSequential(new AutoMoveCommand(driveSubsystem, 1.0, 0.0, 0.5));	// move straight forward at 100% power for 0.5 seconds
    	addSequential(new AutoMoveCommand(driveSubsystem, 0.0, 0.3, 1.0));	// turn right at 30% power for 1 second (inverse of above turn)
    	addSequential(new AutoMoveCommand(driveSubsystem, 1.0, 0.0, 1.3));	// move straight forward at 100% power for 1.3 seconds
    	addSequential(new WidgetMoveUpCommand(widgetSubsystem, 2.0));	// move the widget up for 2 seconds
    	addSequential(new AutoMoveCommand(driveSubsystem, 0.0, 0.0, 0.0));	// and stop
    }
}

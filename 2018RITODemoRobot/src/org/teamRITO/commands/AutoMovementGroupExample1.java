package org.teamRITO.commands;

import org.teamRITO.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Example Group to perform a sequence of moves during autonomous period.
 */
public class AutoMovementGroupExample1 extends CommandGroup {

    public AutoMovementGroupExample1(DriveSubsystem driveSubsystem) {
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
    	addSequential(new AutoMoveCommand(driveSubsystem, 0.8, 0.0, 4.0));	// move straight forward at 80% power for 4 seconds
    	addSequential(new AutoMoveCommand(driveSubsystem, 0.0, 0.0, 0.0));	// and stop
    }
}

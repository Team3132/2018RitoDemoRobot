package org.teamRITO.subsystems;

import org.teamRITO.RobotMap;
import org.teamRITO.commands.DefaultWidgetCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	The Widget Subsystem
 *
 *	This subsystem is an example for a widget device.
 *	The widget device can be moved up, down, and can be stopped.
 *	There is a motor controller that we are passed that moves the widget device.
 *	We control the widget device through movements of the motor controller.
 *	If there was a feedback device we could use a PID loop instead for finer control.
 *  Other feedback devices (position switch, hall effect sensors etc) can be reported on here.
 *
 *	We supply three methods to move the widget device: setMoveUp, setMoveDown and setMoveStop
 *	These methods change the motor controller's speed.
 *
 *	We supply one sensing method isInPosition which allows us to provide feedback about this device.
 *	In a real widget we would have a physical sensor being triggered and report on it here.
 *
 */
public class WidgetSubsystem extends Subsystem {
    SpeedController controller;
    
    /**
     * Widget Subsystem. The constructor for a widget needs to know the motor controller that
     * is connected to the widget device.
     * 
     * @param controller - The motor controller that moves the widget device
     */
    public WidgetSubsystem(SpeedController controller) {
    	this.controller = controller;
    	this.controller.set(0);		// and start with the motor stopped
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    /**
     * setMoveUp - Cause the widget to start moving upwards.
     * When the appropriate movement has been completed setMoveStop must be called
     */
    public void setMoveUp() {
    	controller.set(RobotMap.WIDGET_SPEED_UP);
    }

    /**
     * setMoveDown - Cause the widget to start moving downwards.
     * When the appropriate movement has been completed setMoveStop must be called
     */
    public void setMoveDown() {
    	controller.set(RobotMap.WIDGET_SPEED_DOWN);
    }

    /**
     * setMoveStop - Causes the widget to stop moving
     */
    public void setMoveStop() {
    	controller.set(0);
    }
    
    // Put methods for talking about this subsystem here
    // call these when other things need to know about this subsystem

    /**
     * isInPosition identifies whether the widget is in position. In the demo case this
     * is a dummy method that always returns true. On a real robot this method would check a sensor
     * to determine if the widget was in the correct position
     * 
     * @return true if the widget is in the correct position
     */
    public boolean isInPosition() {
    	return true;
    }
    
    /**
     * Default command. Should not be called directly.
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DefaultWidgetCommand(this));
    }
}


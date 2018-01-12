package org.teamRITO.triggers;

import org.teamRITO.subsystems.WidgetSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * The demo trigger class shows how to create a trigger that will fire whenever the get() method is true
 * In this case when a button is pressed and a subsystem is ready.
 * 
 * We also only return true once per button press.
 */
public class DemoTrigger extends Trigger {

	private Joystick joystick;
	private int buttonNumber;
	private WidgetSubsystem widgetSubsystem;
	private boolean previousState;
	
	/**
	 * The constructor for our trigger. The trigger is passed those elements that are required to determine if the trigger has occurred.
	 * In this example we want a button to be first pressed and a subsystem to be in a position.
	 * @param joystick The joystick to test
	 * @param buttonNumber The button on the joystick that we wish pressed
	 * @param subsystem The susbystem we test
	 */
	public DemoTrigger(Joystick joystick, int buttonNumber, WidgetSubsystem widgetSubsystem) {
		this.joystick = joystick;
		this.buttonNumber = buttonNumber;
		this.widgetSubsystem = widgetSubsystem;
		this.previousState = false;
	}
	
	/**
	 * get() returns true when the conditions for this trigger are true.
	 * We return true once only for the conditions being true. When the conditions are no longer true and become true again we
	 * return true a subsequent time.
	 * @returns true when the trigger has fired
	 */
	@Override
	public boolean get() {
		boolean currentState = joystick.getRawButton(buttonNumber);
		boolean result;
		
		if (!widgetSubsystem.isInPosition()) {
			/*
			 * We set previousState to false here so that if the button is held down as soon as the subsystem is in position we will return true.
			 * Otherwise the operator would have to release and depress the button once the subsystem is in position.
			 */
			previousState = false;
			return false;
		}
		result = !previousState && currentState;
		previousState = currentState;
		return result;
	}

}

package org.teamRITO;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.teamRITO.commands.WidgetMoveDownCommand;
import org.teamRITO.commands.WidgetMoveUpCommand;
import org.teamRITO.commands.WidgetStopCommand;
import org.teamRITO.subsystems.WidgetSubsystem;
import org.teamRITO.triggers.DemoTrigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public OI(
			Joystick driverGamePad,
			Joystick operatorGamePad,
			WidgetSubsystem widgetSubsystem
	) {
		/*
		 * The trigger buttons on the joysticks move the widget up and down whilst held.
		 * releasing a button stops the widget movement.
		 * 
		 * Also, pressing the A button on the gamepad stops the widget
		 */
		new JoystickButton(driverGamePad, 1).whileHeld(new WidgetMoveUpCommand(widgetSubsystem, 0.2));
		new JoystickButton(driverGamePad, 1).whenReleased(new WidgetStopCommand(widgetSubsystem));
		new JoystickButton(driverGamePad, 2).whileHeld(new WidgetMoveDownCommand(widgetSubsystem, 0.2));
		new JoystickButton(driverGamePad, 2).whenReleased(new WidgetStopCommand(widgetSubsystem));
		
		new JoystickButton(operatorGamePad, GamepadButtons.A_BUTTON).whenPressed(new WidgetStopCommand(widgetSubsystem));
		
		/*
		 * When the demo trigger conditions are met then we run the WidgetStopCommand()
		 */
		new DemoTrigger(operatorGamePad, 3, widgetSubsystem).whenActive(new WidgetStopCommand(widgetSubsystem));
	}
}


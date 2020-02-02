package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Climber { 
    private static TalonSRX actuator;
    private static TalonSRX extender;
    private static TalonSRX winch;
    
    private static DigitalInput upLimit;
    private static DigitalInput downLimit;
    
    private static boolean actuated;

    public Climber(){
        actuator = new TalonSRX(7);
        extender = new TalonSRX(8);
        winch = new TalonSRX(9);
        
        upLimit = new DigitalInput(0);
        downLimit = new DigitalInput(1);
        
        actuated = false;
    }
    
    //Actuates and holds when limit switch is hit
    public static void actuation() {
        if(!getUpLimit()) {
            actuator.set(ControlMode.PercentOutput, 0.3); //Raising
        }
        else {
            hold();
        }
        actuated = true;
    }
    
    public static void hold() {
        actuator.set(ControlMode.PercentOutput, 0.05); //Holding
    }
    
    public static boolean getUpLimit() {
        return upLimit.get(); //NOTE: May need to be inverted depending on switch used and if MCU is actice-high or active-low
    }
    
    public static boolean getActuated() {
        return actuated;
    }
    
    public static void extention(){
        extender.set(ControlMode.PercentOutput, 0.3);
    }
    public static void winchination(){
        winch.set(ControlMode.PercentOutput, 0.3);
    }
    public static void actuationStop(){
        actuator.set(ControlMode.PercentOutput, 0.0);
        actuated = false;
    }
    public static void extentionStop(){
        extender.set(ControlMode.PercentOutput, 0.0);
    }
    public static void winchinationStop(){
        winch.set(ControlMode.PercentOutput, 0.0);
    }
}

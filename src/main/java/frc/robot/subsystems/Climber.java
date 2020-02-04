package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Climber { 
    private TalonSRX actuator;
    private TalonSRX extender;
    private TalonSRX winch;
    
    private DigitalInput upLimit;
    private DigitalInput downLimit;
    
    private boolean actuated;

    public Climber(){
        actuator = new TalonSRX(7);
        extender = new TalonSRX(8);
        winch = new TalonSRX(9);
        
        upLimit = new DigitalInput(0);
        downLimit = new DigitalInput(1);
        
        actuated = false;
    }
    
    //Actuates and holds when limit switch is hit
    public void actuation() {
        if(!getUpLimit()) {
            actuator.set(ControlMode.PercentOutput, 0.3); //Raising
        }
        else {
            hold();
        }
        actuated = true;
    }
    
    //TODO the hold value needs to be tested 
    public void hold() {
        actuator.set(ControlMode.PercentOutput, 0.05); //Holding
    }
    
    public boolean getUpLimit() {
        return upLimit.get(); //NOTE: May need to be inverted depending on switch used and if MCU is actice-high or active-low
    }
    
    public boolean getActuated() {
        return actuated;
    }
    
    public void extention(){
        extender.set(ControlMode.PercentOutput, 0.3);
    }
    public void winchination(){
        winch.set(ControlMode.PercentOutput, 0.3);
    }
    public void actuationStop(){
        actuator.set(ControlMode.PercentOutput, 0.0);
        actuated = false;
    }
    public void extentionStop(){
        extender.set(ControlMode.PercentOutput, 0.0);
    }
    public void winchinationStop(){
        winch.set(ControlMode.PercentOutput, 0.0);
    }
}

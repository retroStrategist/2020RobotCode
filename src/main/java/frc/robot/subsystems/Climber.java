package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Climber { 
    private TalonSRX actuator;
    private TalonSRX extender;
    private TalonSRX winch;

    public Climber(){
        actuator = new TalonSRX(7);
        extender = new TalonSRX(8);
        winch = new TalonSRX(9);
        
        //Sets TalonSRX to Brake Mode if true
        actuator.setNeutralMode(true);
        extender.setNeutralMode(true);
        winch.setNeutralMode(true);
        
    }
    public void actuation(){
        actuator.set(ControlMode.PercentOutput, 0.3);
    }
    public void extention(){
        extender.set(ControlMode.PercentOutput, 0.3);
    }
    public void winchination(){
        winch.set(ControlMode.PercentOutput, 0.3);
    }
    public void actuationStop(){
        actuator.set(ControlMode.PercentOutput, 0.0);
    }
    public void extentionStop(){
        extender.set(ControlMode.PercentOutput, 0.0);
    }
    public void winchinationStop(){
        winch.set(ControlMode.PercentOutput, 0.0);
    }
}

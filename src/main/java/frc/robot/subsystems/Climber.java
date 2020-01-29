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
}

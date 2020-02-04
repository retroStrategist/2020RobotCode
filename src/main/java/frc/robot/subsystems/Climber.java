package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Climber { 
    private static TalonSRX actuator;
    private static TalonSRX extender;
    private static TalonSRX winch;

    public Climber(){
        actuator = new TalonSRX(7);
        extender = new TalonSRX(8);
        winch = new TalonSRX(9);
    }
    public static void actuation(){
        actuator.set(ControlMode.PercentOutput, 0.3);
    }
    public static void extention(){
        extender.set(ControlMode.PercentOutput, 0.3);
    }
    public static void winchination(){
        winch.set(ControlMode.PercentOutput, 0.3);
    }
    public static void actuationStop(){
        actuator.set(ControlMode.PercentOutput, 0.0);
    }
    public static void extentionStop(){
        extender.set(ControlMode.PercentOutput, 0.0);
    }
    public static void winchinationStop(){
        winch.set(ControlMode.PercentOutput, 0.0);
    }
}

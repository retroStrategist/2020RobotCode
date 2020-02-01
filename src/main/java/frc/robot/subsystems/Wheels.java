package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Wheels { 

    private WPI_TalonSRX intake;
    private WPI_TalonSRX largeWheel;
    private WPI_TalonSRX shooter;

    public Wheels(){
        intake = new WPI_TalonSRX(4);
        largeWheel = new WPI_TalonSRX(5);
        shooter = new WPI_TalonSRX(6);
    }
    //forward is from intake to shooters
    public void spinBigWheelFor(){
        largeWheel.set(ControlMode.PercentOutput, -0.3);
    }

    public void spinBigWheelBac(){
        largeWheel.set(ControlMode.PercentOutput, 0.3);
    }
    
    public void stopBigWheel(){
        largeWheel.set(ControlMode.PercentOutput, 0.0);
    }
    
    public void spinIntake(){
        intake.set(ControlMode.PercentOutput, -0.5);
    }
    
    public void stopIntake(){
        intake.set(ControlMode.PercentOutput, 0.0);
    }
    
    public void spinShooter(){
        shooter.set(ControlMode.PercentOutput, 0.85);
    } 
    
    public void stopShooter(){
        shooter.set(ControlMode.PercentOutput, 0.0);
    }
}

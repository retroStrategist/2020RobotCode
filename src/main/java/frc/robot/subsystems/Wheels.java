package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SlewRateLimiter;

public class Wheels { 

    private WPI_TalonSRX intake;
    private WPI_TalonSRX largeWheel;
    private WPI_TalonSRX shooter;
    
    //Maximum rate at which motor speed can change
    private final double INTAKE_RATE_LIMIT = 2;
    private final double LARGE_WHEEL_RATE_LIMIT = 1;
    private final double SHOOTER_RATE_LIMIT = 2;
    
    private final SlewRateLimiter intakeRateFilter;
    private final SlewRateLimiter largeWheelRateFilter;
    private final SlewRateLimiter shooterRateFilter;

    public Wheels(){
        intake = new WPI_TalonSRX(4);
        largeWheel = new WPI_TalonSRX(5);
        shooter = new WPI_TalonSRX(6);
        
        intakeRateFilter = new SlewRateLimiter(INTAKE_RATE_LIMIT);
        largeWheelRateFilter = new SlewRateLimiter(LARGE_WHEEL_RATE_LIMIT);
        shooterRateFilter = new SlewRateLimiter(SHOOTER_RATE_LIMIT);
    }
    //forward is from intake to shooters
    public void spinBigWheelFor(){
        setBigWheel(-0.3);
    }

    public void spinBigWheelBac(){
        setBigWheel(0.3);
    }
    
    public void stopBigWheel(){
        setBigWheel(0);
    }
    
    //Sets speed of big wheel
    private void setBigWheel(double speed) {
        largeWheel.set(ControlMode.PercentOutput, largeWheelRateFilter.calculate(speed));
    }
    
    public void spinIntake(){
        setIntake(-0.5);
    }
    
    public void stopIntake(){
        setIntake(0);
    }
    
    private void setIntake(double speed) {
        intake.set(ControlMode.PercentOutput, intakeRateFilter.calculate(speed));
    }
    
    public void spinShooter(){
        setShooter(0.85);
    } 
    
    public void stopShooter(){
        setShooter(0);
    }
    
    private void setShooter(double speed) {
        shooter.set(ControlMode.PercentOutput, shooterRateFilter.calculate(speed));
    }
}

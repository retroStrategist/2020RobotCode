package frc.robot.subsystems;  

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Drivetrain {

    private static WPI_TalonSRX leftFront;
    private static WPI_TalonSRX leftBack;
    private static WPI_TalonSRX rightFront;
    private static WPI_TalonSRX rightBack;

    final int kTimeoutMs = 30;
    
    public Drivetrain() {
        leftFront = new WPI_TalonSRX(0);
        leftBack = new WPI_TalonSRX(1);
        rightFront = new WPI_TalonSRX(2);
        rightBack = new WPI_TalonSRX(3);

        leftBack.follow(leftFront);
        rightBack.follow(rightFront);
        
        initQuadrature(leftFront);
        initQuadrature(rightFront);
        leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
        rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
    }

    public void arcadeDrive(double straight, double left, double right) { 
        leftFront.set(ControlMode.PercentOutput, straight + left - right);
        rightFront.set(ControlMode.PercentOutput, -(straight - left + right)); 
    }

    public static void drive(double speed){
        leftFront.set(ControlMode.PercentOutput, speed);
        rightFront.set(ControlMode.PercentOutput, speed);
    }

    public void tankDrive(double lspeed, double rspeed){
        leftFront.set(ControlMode.PercentOutput, lspeed);
        rightFront.set(ControlMode.PercentOutput, rspeed);
    }
    
    private void initQuadrature(TalonSRX talon) {
        int pulseWidth = talon.getSensorCollection().getPulseWidthPosition();        
        talon.getSensorCollection().setQuadraturePosition(pulseWidth, kTimeoutMs);
	}

    public static int getLeftTicks(){
        return leftFront.getSelectedSensorPosition(0);
    }
    
    public static int getRightTicks(){
        return rightFront.getSelectedSensorPosition(0);
    }
}

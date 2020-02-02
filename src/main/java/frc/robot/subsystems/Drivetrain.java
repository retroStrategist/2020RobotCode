package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SlewRateLimiter;

public class Drivetrain {

    private static WPI_TalonSRX leftFront;
    private static WPI_TalonSRX leftBack;
    private static WPI_TalonSRX rightFront;
    private static WPI_TalonSRX rightBack;
    
    private Faults leftFrontFault;
    private Faults leftBackFault;
    private Faults rightFrontFault;
    private Faults rightBackFault;
    
    private final SlewRateLimiter rateFilterLeft;
    private final SlewRateLimiter rateFilterRight;
    private final double SLEW_RATE_LIMIT = 5;

    final int kTimeoutMs = 30;
    
    public Drivetrain() {
        leftFront = new WPI_TalonSRX(0);
        leftBack = new WPI_TalonSRX(1);
        rightFront = new WPI_TalonSRX(2);
        rightBack = new WPI_TalonSRX(3);

        leftBack.follow(leftFront);
        rightBack.follow(rightFront);
        
        leftFrontFault = new Faults();
        leftBackFault = new Faults();
        rightFrontFault = new Faults();
        rightBackFault = new Faults();
        
        initQuadrature(leftFront);
        initQuadrature(rightFront);
        leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
        rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
                
        rateFilterLeft = new SlewRateLimiter(SLEW_RATE_LIMIT);
        rateFilterRight = new SlewRateLimiter(SLEW_RATE_LIMIT);
    }
    
    public boolean isFault() {
        updateFaults();
        return leftFrontFault.hasAnyFault() || leftBackFault.hasAnyFault() || rightFrontFault.hasAnyFault() || rightBackFault.hasAnyFault();
    }
    
    public String getFaults() {
        updateFaults();
        return ("Left Front: " + leftFrontFault.toString() + "\nLeft Back: " + leftBackFault.toString() + "\nRight Front: " + rightFrontFault.toString() + "\nRight Back: " + rightBackFault.toString() + "\n");
    }
    
    private void updateFaults() {
        leftFront.getFaults(leftFrontFault);
        leftBack.getFaults(leftBackFault);
        rightFront.getFaults(rightFrontFault);
        rightBack.getFaults(rightBackFault);
    }

    public void arcadeDrive(double straight, double left, double right) { 
        leftDrive(straight + left - right);
        rightDrive(-(straight - left + right));
    }

    //Straight drive for autonomy
    public static void drive(double speed){
        leftDrive(speed);
        rightDrive(speed);
    }

    public void tankDrive(double lspeed, double rspeed){
        leftDrive(lspeed);
        rightDrive(rspeed);
    }
    
    private void leftDrive(double speed) {
        leftFront.set(rateFilterLeft.calculate(speed));
    }
    
    private void rightDrive(double speed) {
        rightFront.set(rateFilterRight.calculate(speed));
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
    
    public void setNeutralMode(NeutralMode mode) {
        leftFront.setNeutralMode(mode);
        leftBack.setNeutralMode(mode);
        rightFront.setNeutralMode(mode);
        rightBack.setNeutralMode(mode);
    }
}

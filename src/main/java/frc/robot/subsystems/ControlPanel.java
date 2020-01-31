package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.I2C.Port;

import com.revrobotics.ColorSensorV3;

public class ControlPanel {
    
    private WPI_TalonSRX motor1;
    private WPI_TalonSRX motor2;
    
    private ColorSensorV3 colorSensor;

    public ControlPanel(){
        motor1 = new WPI_TalonSRX(10);
        motor2 = new WPI_TalonSRX(11);
        
        colorSensor = new ColorSensorV3(Port.kOnboard);
    }
}

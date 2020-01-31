package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;

public class ControlPanel {
    
    private WPI_TalonSRX motor1;
    private WPI_TalonSRX motor2;
    
    private ColorSensorV3 colorSensor;
    
    private final RawColor blue = new RawColor(0,0,0,0);//FIND VALUES FOR HERE
    private final RawColor green = new RawColor(0,0,0,0);//FIND VALUES FOR HERE
    private final RawColor red = new RawColor(0,0,0,0);//FIND VALUES FOR HERE
    private final RawColor yellow = new RawColor(0,0,0,0);//FIND VALUES FOR HERE

    public ControlPanel() {
        motor1 = new WPI_TalonSRX(10);
        motor2 = new WPI_TalonSRX(11);
        
        colorSensor = new ColorSensorV3(Port.kOnboard);
    }
    
    //Return RawColor currently seen by color sensor
    private RawColor getCurrentColor() {
        return colorSensor.getRawColor();
    }
    
    //Return target RawColor sent by FMS
    private RawColor getIntendedColor() {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0) {
            switch (gameData.charAt(0)) {
                case 'B' :
                    return 
                case 'G' :
                    return
                case 'R' :
                    return
                case 'Y' :
                    return
                default :
                    System.out.println("Error: Corrupt data received");
                    break;
            }
        } 
        else {
            System.out.println("No data received");
        }
    }
}

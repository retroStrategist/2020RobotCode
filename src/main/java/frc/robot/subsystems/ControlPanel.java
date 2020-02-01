package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;

public class ControlPanel {
    
    private WPI_TalonSRX motor1;
    private WPI_TalonSRX motor2;
    
    private ColorSensorV3 colorSensor;
    
    private final RawColor BLUE = new RawColor(0,0,0,0);//FIND VALUES FOR HERE
    private final RawColor GREEN = new RawColor(0,0,0,0);//FIND VALUES FOR HERE
    private final RawColor RED = new RawColor(0,0,0,0);//FIND VALUES FOR HERE
    private final RawColor YELLOW = new RawColor(0,0,0,0);//FIND VALUES FOR HERE
    
    enum PossibleColor
    { 
        BLUE, GREEN, RED, YELLOW; 
    } 

    public ControlPanel() {
        motor1 = new WPI_TalonSRX(10);
        motor2 = new WPI_TalonSRX(11);
        
        colorSensor = new ColorSensorV3(Port.kOnboard);
    }
    
    //Turn wheel until the correct color is reached
    public void positionControl() {
        
    }
    
    //Turn wheel specified number of times
    public void rotationControl(byte timesToRotate) {
        
    }
    
    //Return RawColor currently seen by color sensor
    private RawColor getCurrentColor() {
        return colorSensor.getRawColor();
    }
    
    //Return target PossibleColor sent by FMS
    private PossibleColor getIntendedColor() {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0) {
            switch (gameData.charAt(0)) {
                case 'B' :
                    return PossibleColor.BLUE;
                case 'G' :
                    return PossibleColor.GREEN;
                case 'R' :
                    return PossibleColor.RED;
                case 'Y' :
                    return PossibleColor.YELLOW;
                default :
                    System.out.println("Error: Corrupt data received");
                    break;
            }
        } 
        else {
            System.out.println("No data received");
        }
    }
    
    //Returns closest PossibleColor
    private PossibleColor findCloseColor() {
        
    }
    
    //Returns difference in raw color value
    private int colorDifference() {
        
    }

}

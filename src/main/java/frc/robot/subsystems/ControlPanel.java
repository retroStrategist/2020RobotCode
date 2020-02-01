package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;

import java.lang.Math; 

public class ControlPanel {
    
    private double SPIN_MOTOR_SPEED = 0.5;
    
    private WPI_TalonSRX arm;
    private WPI_TalonSRX spin;
    
    private ColorSensorV3 colorSensor;
    
    private final PossibleColor colorOrder[];
    
    private PossibleColor lastColor;
    
    private double timesToRotate; //Number of fulls rotations that still must be done to finish position control
    
    private enum PossibleColor
    { 
        BLUE(new RawColor(0,0,0,0)),//FIND VALUES FOR HERE
        GREEN(new RawColor(0,0,0,0)),//FIND VALUES FOR HERE
        RED(new RawColor(0,0,0,0)),//FIND VALUES FOR HERE
        YELLOW(new RawColor(0,0,0,0));//FIND VALUES FOR HERE
        
        private final RawColor color;
        
        PossibleColor(RawColor color) {
            this.color = color
        }
        
        public RawColor getColor() {
            return color;
        }
    } 

    public ControlPanel() {
        arm = new WPI_TalonSRX(10);
        spin = new WPI_TalonSRX(11);
        
        colorSensor = new ColorSensorV3(Port.kOnboard);
        
        colorOrder = new PossibleColor[4];
        colorOrder[0] = PossibleColor.BLUE;
        colorOrder[1] = PossibleColor.GREEN;
        colorOrder[2] = PossibleColor.RED;
        colorOrder[3] = PossibleColor.YELLOW;
        
        lastColor = findCloseColor();
        
        timesToRotate = 0.0;
    }
    
    //Turns wheel when the correct color is not reached & returns true if position is reached
    public boolean positionControl() {
        if(fieldSensorColor() != getIntendedColor()) {
            spin.set(SPIN_MOTOR_SPEED);
            return false;
        }
        else {
            spin.set(0);
            return true;
        }
    }
    
    //Queue up rotations of the control panel. Add in multiples of 0.125 as each segment is 0.125 of wheel total.
    public void addControlPanelRotation(double addTimesToRotate) {
        timesToRotate += addTimesToRotate;
    }
    
    //Turns control panel timesToRotate number of times
    public void rotationControl() {
        if(timesToRotate != 0) {
            spin.set(SPIN_MOTOR_SPEED);
            if(colorChange()) {
                timesToRotate -= 0.125 //Each color segment is 1/8 of wheel. This is subtracting a segment that still needs to be spun through.
            }
        }
        else {
            spin.set(0);
        }
    }
    
    public void flipUpMotor() {
        
    }
    
    public void flipDownMotor() {
        
    }
    
    //Returns true if the current color is different from the last color
    private boolean colorChanged() {
        if(findCloseColor() == lastColor) {
            return false;
        }
        else {
            lastColor = findCloseColor();
            return true;
        }
    }
    
    //Returns the color seen by the field sensor based on the color seen by the robot sensor
    private PossibleColor fieldSensorColor() {
        return colorOrder[(x + 2)%4];
    }
    
    //returns corresponding array index
    private int getArrayIndex() {
        PossibleColor color = findCloseColor();
        for(int x = 0; x < 4;x++) {
            if(color == colorOrder[x]) {
                return x;
            }
        }
    }
    
    //Returns RawColor currently seen by color sensor
    //Only a utility function to help other methods
    //Use findCloseColor() instead
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
            }
            
            System.out.println("Error: Corrupt data received");
            return NULL;
        } 
        else {
            System.out.println("No data received");
            return NULL;
        }
    }
    
    //Returns closest PossibleColor to current sensor color
    private PossibleColor findCloseColor() {
        RawColor currColor = getCurrentColor();
        
        blueDiff = colorDifference(currColor, PossibleColor.BLUE.getColor());
        greenDiff = colorDifference(currColor, PossibleColor.GREEN.getColor());
        redDiff = colorDifference(currColor, PossibleColor.RED.getColor());
        yellowDiff = colorDifference(currColor, PossibleColor.YELLOW.getColor());
        
        int temp, size;
        int array[] = {blueDiff, greenDiff, redDiff, yellowDiff};
        size = array.length;

        for(int i = 0; i<size; i++ ) {
            for(int j = i+1; j<size; j++) {
                if(array[i]>array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        
        switch (array[0]) {
            case blueDiff:
                return PossibleColor.BLUE;
            case greenDiff:
                return PossibleColor.GREEN;
            case redDiff:
                return PossibleColor.RED;
            case yellowDiff:
                return PossibleColor.YELLOW;
        }
        return NULL;
    }
    
    //Returns the sum of each difference in raw color value of inputed color and PossibleColors
    private int colorDifference(RawColor color, RawColor possible) {
        return Math.abs(color.red - possible.red) + Math.abs(color.green - possible.green) + Math.abs(color.blue - possible.blue) + Math.abs(color.ir - possible.ir);
    }
}

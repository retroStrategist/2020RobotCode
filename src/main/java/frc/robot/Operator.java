package frc.robot;

import frc.robot.subsystems.*;

public class Operator {

    private Controller OP;
    private Wheels wheels;
    private ControlPanel controlPanel;
    private Climber climb;

    private boolean startedPositionControl;
    private boolean finishedPositionControl;
    
    private long lastButtonPress;//Last time rotation control button was pressed
    private final int BUTTON_DELAY = 500;//Delay time for rotation control button in ms
    
    private long lastPositionPress;//Last time position control button was pressed
    private final int POSITION_TIMEOUT = 1000;//Position control timeout in ms
    
    public Operator(int port) {
        OP = new Controller(port);
        wheels = new Wheels();
        controlPanel = new ControlPanel();
        climb = new Climber();
        
        resetControlPanel();
        lastButtonPress = System.currentTimeMillis();
    }

    public void opControls() {
        controlPanelControl();        
        
        if (OP.getXButton()) {
            wheels.spinBigWheelFor();
            System.out.println("attempting to spin big wheel forward");
        } else if (OP.getTriangleButton()) {
            wheels.spinBigWheelBac();
            System.out.println("attempting to spin big wheel backwards");
        } else {
            wheels.stopBigWheel();
            System.out.println("attempting to stop wheel");
        }

        if (OP.getOButton()) {
            wheels.spinIntake();
        } else {
            wheels.stopIntake();
        }

        if (OP.getSquareButton()) {
            wheels.spinShooter();
        } else {
            wheels.stopShooter();
        }
        
        if (OP.getDPadUp()){
            Climber.actuation();
        }
        else if(OP.getDPadDown()) {
            Climber.actuationStop();
        }
        else if(getActuated() && getUpLimit()) {
            Climber.hold();
        }

        if(OP.getDPadLeft()){
            Climber.extention();
        }
        else {
            Climber.extentionStop();
        }

        if(OP.getDPadRight()){
            Climber.winchination();
        }
        else {
            Climber.winchinationStop();
        }
        
    }
    
    private void controlPanelControl(){
        //Position control
        if(OP.getRightBumper()) {
            startedPositionControl = true;
            flipUpMotor();
        }
        
        if(startedPositionControl && !finishedPositionControl){
            finishedPositionControl = positionControl();
        }
        
        if(finishedPositionControl) {
            flipDownMotor();
            resetControlPanel();
        }
        
        //Pos control timeout
        if((lastPositionPress + POSITION_TIMEOUT) < System.currentTimeMillis()) {
            finishedPositionControl = true;
        }
        
        //Rotation control
        if(OP.getLeftBumper() && ((lastButtonPress + BUTTON_DELAY) < System.currentTimeMillis())) {
            addControlPanelRotation(1);//Each button press queues another rotation for the motor to spin through
        }
        rotationControl();
    }
            
    private void resetControlPanel() {
        startedPositionControl = false;
        finishedPositionControl = false;
    }
}

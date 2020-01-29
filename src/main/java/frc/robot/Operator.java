package frc.robot;

import frc.robot.subsystems.*;

public class Operator {

    private Controller OP;
    private Wheels wheels;

    public Operator(int port) {
        OP = new Controller(port);
        wheels = new Wheels();

    }

    public void opControls() {
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
    }

}

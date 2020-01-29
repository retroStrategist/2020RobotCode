package frc.robot.auto.functions;
 
import edu.wpi.first.wpilibj.Timer;
import frc.robot.auto.setup.RobotFunction;
import frc.robot.subsystems.Drivetrain;
 
// Drives straight for period of time
 
public class timeDrive extends RobotFunction {
 
    private double startTime, speed, time;
 
    public timeDrive(double speed, double time) {
        this.speed = speed;
        this.time = time;
    }
    @Override
    public void init() {
        startTime = Timer.getFPGATimestamp();
    }
    @Override
    public void run() {
        //PrettyPrint.put("DriveTime Elapsed");
        Drivetrain.drive(speed);
    }
    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime > time;
    }
    @Override
    public void stop(){
        Drivetrain.drive(0.0);
    }
 
 
}

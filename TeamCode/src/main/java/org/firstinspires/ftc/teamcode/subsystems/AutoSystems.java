package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.Constants.BASE.*;
import static org.firstinspires.ftc.teamcode.Constants.FIELD.*;
import static org.firstinspires.ftc.teamcode.Constants.SWIVEL.*;
import static org.firstinspires.ftc.teamcode.Constants.SPEED.*;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.Dataflow;

public class AutoSystems extends Drivebase {
    private final Telemetry telemetry;
    private final IMUHandler imuHandler;
    private final LinearOpMode linearOpMode;
    private Dataflow dataflow;

    public AutoSystems(LinearOpMode linearOpMode) {
        super(linearOpMode);
        this.linearOpMode = linearOpMode;
        this.telemetry = linearOpMode.telemetry;
        this.imuHandler = new IMUHandler(linearOpMode);
        this.imuHandler.init();
        this.imuHandler.resetHeading();
        this.dataflow = new Dataflow(this.telemetry);
    }

    public void turnToHeading(double heading) {
        while (linearOpMode.opModeIsActive() && !onHeading(TURN_SPEED, howMuchTurning(heading), P_TURN_GAIN)) {
            telemetry.update();
        }
        setMotorsPower(0, 0, 0);
    }

    private double howMuchTurning(double targetHeading) {
        double current = imuHandler.getHeading();
        double difference = targetHeading - current;
        difference = (difference + 360) % 360;
        if (difference > 180) {
            difference -= 360;
        }

        return difference;
    }

    private boolean onHeading(double speed, double heading, double PCoeff) {
        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();
        double error = getError(heading);
        double steer;
        boolean onTarget = false;
        double leftSpeed;
        double rightSpeed;

        if (abs(error) <= HEADING_THRESHOLD) {
            steer = 0.0;
            leftSpeed = 0.0;
            rightSpeed = 0.0;
            onTarget = true;
        } else {
            steer = getSteer(error, PCoeff);
            leftSpeed = speed * steer;
            rightSpeed = -leftSpeed;
        }

        setMotorsPower(leftSpeed, rightSpeed, 0);
        dataflow.addToAll(new String[] {"Target", "Err", "Current heading", "Speed left", "Speed right", "Runtime"},
                                        heading, error, imuHandler.getHeading(), leftSpeed, rightSpeed, runtime);
        dataflow.sendDatas();
        return onTarget;
    }

    private double getError(double targetAngle) {
        double robotError = targetAngle - imuHandler.getHeading();
        while (robotError > 180) robotError -= 360;
        while (robotError <= -180) robotError += 360;
        return robotError;
    }

    private double getSteer(double error, double PCoeff) {
        return Range.clip(error * PCoeff, -1, 1);
    }

    
    private void setUpForEncoder(double moveTarget, DcMotorEx motorEx, double COUNTS_PER_INCH) {
        int newTarget = motorEx.getCurrentPosition() + (int) (moveTarget * COUNTS_PER_INCH);
        motorEx.setTargetPosition(newTarget);
        motorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void horizontalMove(double moveTarget, boolean left) {
        setUpForEncoder(moveTarget, middleWheel, HD_SMALL_COUNTS_PER_INCH);
        if(left) {
            middleWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        middleWheel.setPower(1.0);
        while (linearOpMode.opModeIsActive() && middleWheel.isBusy()) {
            // Wait for the movement to complete
        }
        middleWheel.setPower(0);
        middleWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        middleWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}

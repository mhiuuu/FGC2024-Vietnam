package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.Constants.SPEED.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;


import java.util.Arrays;
import java.util.List;

public class Drivebase {
    private DcMotorEx leftFront;
    private DcMotorEx rightFront;
    private DcMotorEx leftBack;
    private DcMotorEx rightBack;
    public  DcMotorEx middleWheel;
    private DcMotorEx inTake;
    private final HardwareMap hardwareMap;
    private double speed = NORMAL_DRIVE;
    public List<DcMotorEx> motors, leftMotors, rightMotors;


    public Drivebase(LinearOpMode linearOpMode) {
        this.hardwareMap = linearOpMode.hardwareMap;
    }

    public void init(){
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class,"rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        inTake = hardwareMap.get(DcMotorImplEx.class, "inTake");

        motors = Arrays.asList(leftBack, leftFront, rightBack, rightFront);
        rightMotors = Arrays.asList(rightFront, rightBack);
        leftMotors = Arrays.asList(leftFront, leftBack);

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        setDirection(DcMotorSimple.Direction.REVERSE, leftMotors);
    }

    public void setMotorsPower(double leftPower, double rightPower){
        leftFront.setPower(leftPower * speed);
        leftBack.setPower(leftPower * speed);
        rightFront.setPower(rightPower * speed);
        rightBack.setPower(rightPower * speed);
    }

    public void boost() {
        if(speed == NORMAL_DRIVE) {
            speed = BOOST_DRIVE;
        } else {
            speed = NORMAL_DRIVE;
        }
    }

    public double getPower(DcMotorEx motorEx) {
        return motorEx.getPower();
    }

    public double getLeftPower() {
        return getPower(leftBack);
    }

    public double getRightPower() {
        return getPower(rightBack);
    }

    public void setMode(DcMotor.RunMode runMode) {
        for (DcMotorEx motor : motors) {
            motor.setMode(runMode);
        }
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        for (DcMotorEx motor : motors) {
            motor.setZeroPowerBehavior(zeroPowerBehavior);
        }
    }

    public void setDirection(DcMotorSimple.Direction Direction, List<DcMotorEx> sideMotors) {
        for (DcMotorEx motor : sideMotors) {
            motor.setDirection(Direction);
        }
    }

    public void setInTake(double pow) {
        inTake.setPower(Range.clip(pow, -1.0, 1.0));
    }
}

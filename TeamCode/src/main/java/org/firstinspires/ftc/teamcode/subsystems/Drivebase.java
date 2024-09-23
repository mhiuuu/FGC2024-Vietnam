package org.firstinspires.ftc.teamcode.subsystems;



import static org.firstinspires.ftc.teamcode.Constants.SPEED.NORMAL_DRIVE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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
    private CRServo clawLeft, clawRight;
    private final HardwareMap hardwareMap;
    public List<DcMotorEx> motors, leftMotors, rightMotors;


    public Drivebase(LinearOpMode linearOpMode) {
        this.hardwareMap = linearOpMode.hardwareMap;
    }

    public void init(){
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class,"rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        middleWheel = hardwareMap.get(DcMotorEx.class, "middleWheel");

        clawLeft = hardwareMap.get(CRServo.class, "clawLeft");
        clawRight = hardwareMap.get(CRServo.class, "clawRight");

        clawLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        clawRight.setDirection(DcMotorSimple.Direction.FORWARD);

        motors = Arrays.asList(leftBack, leftFront, rightBack, rightFront);
        rightMotors = Arrays.asList(rightFront, rightBack);
        leftMotors = Arrays.asList(leftFront, leftBack);

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        setDirection(DcMotorSimple.Direction.REVERSE, rightMotors);
    }

    public void setMotorsPower(double leftPower, double rightPower, double speed){
        leftFront.setPower(leftPower * speed);
        leftBack.setPower(leftPower * speed);
        rightFront.setPower(rightPower * speed);
        rightBack.setPower(rightPower * speed);
    }

    public void setHorizontalMove(double hex_power) {
        double power;
        power = Range.clip(hex_power, -1.0, 1.0);
        middleWheel.setPower(power);
    }

    public int getPosition(DcMotor motor) {
        return motor.getCurrentPosition();
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

    public void navigateClaws(double position) {
        clawRight.setPower(position);
        clawLeft.setPower(position);
    }
}

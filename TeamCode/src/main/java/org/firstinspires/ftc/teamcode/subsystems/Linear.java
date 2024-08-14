package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class Linear {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private DcMotorEx middleLinear;
    private List<DcMotor> motors;
    private Servo servo1;
    private Servo servo2;
    private final HardwareMap hardwareMap;

    public Linear(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
    }

    public void init() {
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
        middleHex = hardwareMap.get(DcMotorEx.class, "elevator") ;
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");

        motors = Arrays.asList(leftMotor, rightMotor, middleHex);

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servo1.setPosition(0);
        servo2.setPosition(0);
    }

    public void setMode(DcMotor.RunMode runMode) {
        for (DcMotor motor : motors) {
            motor.setMode(runMode);
        }
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        for (DcMotor motor : motors) {
            motor.setZeroPowerBehavior(zeroPowerBehavior);
        }
    }
   public void setLeftMotor(double pow) {
        leftMotor.setPower(pow);
    }

    public void setRightMotor(double pow) {
        rightMotor.setPower(pow);
    }

    public void setElevator(double pow) { middleHex.setPower(pow);}

    public void setAllLinear(double pow) {
        setRightMotor(pow);
        setLeftMotor(pow);
    }

    public void setLinearServo() {
        servo1.setPosition(0.27);
        servo2.setPosition(0.27);
    }
}

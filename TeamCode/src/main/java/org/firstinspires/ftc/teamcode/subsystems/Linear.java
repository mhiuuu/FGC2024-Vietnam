package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Linear {
    private DcMotorEx upLinear;
    private DcMotorEx downLinear;
    private DcMotorEx middleLinear;
    private Servo servo1, servo2, lock;
    private final HardwareMap hardwareMap;
    private TouchSensor limit_switch;
    private boolean hasRumbled = false;
    public Linear(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
    }

    public void init() {
        upLinear = hardwareMap.get(DcMotorEx.class, "upLinear");
        downLinear = hardwareMap.get(DcMotorEx.class, "downLinear");
        middleLinear = hardwareMap.get(DcMotorEx.class, "middleLinear");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        limit_switch = hardwareMap.get(TouchSensor.class, "limit");
        lock = hardwareMap.get(Servo.class, "lock");
        //upLinear.setDirection(DcMotorSimple.Direction.REVERSE);
        //downLinear.setDirection(DcMotorSimple.Direction.FORWARD);

        //servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.REVERSE);

        upLinear.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        downLinear.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        middleLinear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        upLinear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        downLinear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        middleLinear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void setUpLinear(double pow) {
        upLinear.setPower(pow);
    }

    public void setDownLinear(double pow) {
        downLinear.setPower(pow);
    }

    public void setAllLinear(double pow) {
        setDownLinear(pow);
        setUpLinear(pow);
    }

    public void setMiddleLinear(double pow) {
        middleLinear.setPower(pow);
    }

    public void setLinearServo(double pos) {
        servo2.setPosition(pos);
        servo1.setPosition(pos+0.2);
    }

    public void open(double pos) {

        lock.setPosition(pos);
    }


    public void isEnd(Gamepad gamepad) {
        if (limit_switch.isPressed() && !hasRumbled) {
            gamepad.rumble(300);
            hasRumbled = true;
        } else if (!limit_switch.isPressed()) {
            hasRumbled = false;
        }
    }

}

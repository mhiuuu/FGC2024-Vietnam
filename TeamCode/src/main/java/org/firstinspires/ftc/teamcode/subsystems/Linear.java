package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Linear {
    private DcMotorEx leftLinear;
    private DcMotorEx rightLinear;
    private final HardwareMap hardwareMap;
    public Linear(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
    }

    public void init() {
        leftLinear = hardwareMap.get(DcMotorEx.class, "leftLinear");
        rightLinear = hardwareMap.get(DcMotorEx.class, "rightLinear");

        leftLinear.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightLinear.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        leftLinear.setDirection(DcMotor.Direction.REVERSE);

        leftLinear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightLinear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    private void setLeftLinear(double pow) {
        leftLinear.setPower(pow);
    }

    private void setRightLinear(double pow) {
        rightLinear.setPower(pow);
    }

    public void setAllLinear(boolean x1, boolean x2) {
        double pow = power(x1) - power(x2);
        setLeftLinear(pow);
        setRightLinear(pow);
    }

    private double power(boolean pressed) {
        return (pressed) ? 1:0;
    }
}

package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode ;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp ;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Main extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "leftFront");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor backLeft = hardwareMap.get(DcMotor.class,"leftBack") ;
        DcMotor backRight = hardwareMap.get(DcMotor.class,"rightBack") ;
        DcMotor middleMotor = hardwareMap.get(DcMotor.class,"middleHex") ;
        DcMotor elevator = hardwareMap.get(DcMotor.class,"elevator") ;
        DcMotor leftLinear = hardwareMap.get(DcMotor.class,"leftMotor") ;
        DcMotor rightLinear = hardwareMap.get(DcMotor.class,"rightMotor") ;

        leftLinear.setDirection(DcMotor.Direction.REVERSE) ;

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLinear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLinear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        middleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        middleMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftLinear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightLinear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double spdratio = 0.6 ;
            if ( this.gamepad1.right_bumper ) spdratio = 1;

            double l = -this.gamepad1.left_stick_y ;
            double r = -this.gamepad1.right_stick_y ;

            double leftPower = l * spdratio ;
            double rightPower = r * spdratio ;

            frontLeft.setPower(leftPower);
            backLeft.setPower(leftPower);
            frontRight.setPower(rightPower);
            backRight.setPower(rightPower);
            middleMotor.setPower(gamepad1.left_trigger-gamepad1.right_trigger);

            leftLinear.setPower(pow(gamepad1.dpad_up)-pow(gamepad1.dpad_down));
            rightLinear.setPower(pow(gamepad1.dpad_up)-pow(gamepad1.dpad_down));

            elevator.setPower(pow(gamepad1.y)-pow(gamepad1.a));


        }

    }
    public double pow ( boolean x ) {
        return (x ) ? 1 : 0 ;
    }

}

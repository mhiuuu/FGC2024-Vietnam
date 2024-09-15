package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name="HorizontalAlign", group="Robot")
public class HorizontalAlign extends LinearOpMode {
    private DcMotorEx middleWheel;

    // Constants
    public static final double COUNTS_PER_CORE_HEX_MOTOR_REV = 288.0;
    public static final double WHEEL_DIAMETER_INCHES = 9.00 / 2.54;  // Convert 9 cm to inches
    public static final double HEX_COUNT_PER_INCH = COUNTS_PER_CORE_HEX_MOTOR_REV / (WHEEL_DIAMETER_INCHES * Math.PI);

    @Override
    public void runOpMode() {
        // Initialize the middle wheel motor
        middleWheel = hardwareMap.get(DcMotorEx.class, "middleWheel");
        middleWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        middleWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        horizontalMove(30);  // Move horizontally for 30 inches
    }

    private void setUpForEncoder(double moveTarget, DcMotorEx motorEx, double COUNTS_PER_INCH) {
        // Calculate the new target position in encoder counts
        int newTarget = motorEx.getCurrentPosition() + (int) (moveTarget * COUNTS_PER_INCH);
        motorEx.setTargetPosition(newTarget);
        motorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void horizontalMove(double moveTarget) {
        // Set up motor for movement using encoder feedback
        setUpForEncoder(moveTarget, middleWheel, HEX_COUNT_PER_INCH);
        middleWheel.setPower(0.5);

        // Display telemetry while the motor is moving
        while (opModeIsActive() && middleWheel.isBusy()) {
            telemetry.addData("Current Position", middleWheel.getCurrentPosition());
            telemetry.addData("Target Position", middleWheel.getTargetPosition());
            telemetry.update();
        }

        // Stop the motor and reset to normal encoder mode
        middleWheel.setPower(0);
        middleWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}

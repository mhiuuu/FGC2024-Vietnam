package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.Linear;

@TeleOp
@Disabled
public class LinearTuning extends LinearOpMode {
    private Linear linear;
    @Override
    public void runOpMode() {
        linear = new Linear(this);
        linear.init();

        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.dpad_up) {
                linear.setDownLinear(1.0);
            } else if(gamepad1.dpad_down) {
                linear.setDownLinear(-1.0);
            } else {
                linear.setDownLinear(0);
            }

            if(gamepad1.triangle) {
                linear.setUpLinear(1.0);
            } else if(gamepad1.cross) {
                linear.setUpLinear(-1.0);
            } else {
                linear.setUpLinear(0);
            }
        }
    }
}

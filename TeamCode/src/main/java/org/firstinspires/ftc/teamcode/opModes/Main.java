package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Manual;
import org.firstinspires.ftc.teamcode.robot.SemiAuto;

@TeleOp(name = "Main OpMode", group = "Robot")
public class Main extends LinearOpMode {
    private Manual robot;

    @Override
    public void runOpMode() {
        robot = new Manual(this);
        robot.init();

        waitForStart();

        if (opModeIsActive()) {
            robot.loop(this);
        }
    }
}

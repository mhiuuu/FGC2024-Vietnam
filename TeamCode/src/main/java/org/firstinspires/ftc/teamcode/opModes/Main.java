package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.SemiAuto;

@TeleOp(name = "DÙNG CÁI NÀY", group = "Robot")
public class Main extends LinearOpMode {
    private SemiAuto robot;

    @Override
    public void runOpMode() {
        robot = new SemiAuto(this);
        robot.init();

        waitForStart();

        if (opModeIsActive()) {
            robot.loop(this);
        }
    }
}

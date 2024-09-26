package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.SemiAuto;

@TeleOp(name = "Semi auto with horiziontal move", group = "Auto")
public class SemiMain extends LinearOpMode {
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

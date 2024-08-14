package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp
public class Main extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() {
        robot = new Robot(this);
        robot.init();

        waitForStart();

        if (opModeIsActive()) {
            robot.loop(this);
        }
    }
}

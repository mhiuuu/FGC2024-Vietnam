package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.AutoSystems;
import org.firstinspires.ftc.teamcode.subsystems.Drivebase;
import org.firstinspires.ftc.teamcode.subsystems.IMUHandler;
import org.firstinspires.ftc.teamcode.subsystems.Linear;
import org.firstinspires.ftc.teamcode.utils.Dataflow;

public class Manual {
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    private IMUHandler imuHandler;
    private Drivebase driveBase;
    private Linear linear;
    private Telemetry telemetry;
    private Dataflow dataflow;
    private AutoSystems autoSystems;

    public Manual(LinearOpMode linearOpMode) {
        this.imuHandler = new IMUHandler(linearOpMode);
        this.telemetry = linearOpMode.telemetry;
        this.driveBase = new Drivebase(linearOpMode);
        this.linear = new Linear(linearOpMode);
        this.gamepad1 = linearOpMode.gamepad1;
        this.gamepad2 = linearOpMode.gamepad2;
        this.dataflow = new Dataflow(this.telemetry);
        this.autoSystems = new AutoSystems(linearOpMode);
    }

    public void init() {
        driveBase.init();
        autoSystems.init();
        linear.init();
        imuHandler.init();
    }

    public void loop(LinearOpMode linearOpMode) {
        while (linearOpMode.opModeIsActive()) {
            setGamepad1();
            setGamepad2();
            setGamepad2();
            dataflow.addToAll(new String[]{ "Left Power:", "Right Power:", "Current heading"},
                                            driveBase.getLeftPower(), driveBase.getRightPower(), imuHandler.getHeading());
            dataflow.sendDatas();
        }
    }


    private void setGamepad1() {
        double leftY = gamepad1.left_stick_y;
        double rightY = gamepad1.right_stick_y;
        double leftPower = Range.clip(leftY, -1.0, 1.0);
        double rightPower = Range.clip(rightY, -1.0, 1.0);
        driveBase.setMotorsPower(leftPower, rightPower);
        driveBase.setHorizontalMove(gamepad1.right_trigger-gamepad1.left_trigger);
        if(gamepad1.right_bumper) {
            driveBase.navigateClaws(1.0);
        } else if(gamepad1.left_bumper) {
            driveBase.navigateClaws(0.0);
        }
    }

    public void setGamepad2() {
        linear.setUpLinear(-gamepad2.left_stick_y);
        linear.setDownLinear(gamepad2.right_stick_y);
        linear.setMiddleLinear(gamepad2.left_trigger - gamepad2.right_trigger);

        if(gamepad2.dpad_up) {
            linear.setLinearServo(1.0);
        } else if(gamepad2.dpad_down) {
            linear.setLinearServo(0.3);
        }
    }
}

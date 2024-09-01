package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.AutoSystems;
import org.firstinspires.ftc.teamcode.subsystems.Drivebase;
import org.firstinspires.ftc.teamcode.subsystems.Linear;
import org.firstinspires.ftc.teamcode.utils.Dataflow;

public class SemiAuto {
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    private Drivebase driveBase;
    private Linear linear;
    private Telemetry telemetry;
    private Dataflow dataflow;
    private AutoSystems autoSystems;
    private enum RobotState { IDLE, TURNING, MANUAL_CONTROL }
    private RobotState currentState;

    public SemiAuto(LinearOpMode linearOpMode) {
        this.telemetry = linearOpMode.telemetry;
        this.driveBase = new Drivebase(linearOpMode);
        this.linear = new Linear(linearOpMode);
        this.gamepad1 = linearOpMode.gamepad1;
        this.gamepad2 = linearOpMode.gamepad2;
        this.dataflow = new Dataflow(this.telemetry);
        this.autoSystems = new AutoSystems(linearOpMode);
        this.currentState = RobotState.IDLE;
        this.linear = new Linear(linearOpMode);
    }

    public void init() {
        driveBase.init();
        linear.init();
        autoSystems.init();
    }

    public void loop(LinearOpMode linearOpMode) {
        while (linearOpMode.opModeIsActive()) {
            switch (currentState) {
                case IDLE:
                    if (gamepad1.circle) {
                        currentState = RobotState.TURNING;
                    } else if (gamepad1.start) {
                        currentState = RobotState.MANUAL_CONTROL;
                    }
                    break;
                case TURNING:
                    turning();
                    currentState = RobotState.MANUAL_CONTROL;
                    break;
                case MANUAL_CONTROL:
                    manual_control();
                    break;
            }

            dataflow.addToAll(new String[]{"LeftBack:", "RightBack:", "Current State:"}, driveBase.getLeftPower(), driveBase.getRightPower(), currentState);
            dataflow.sendDatas();
        }
    }

    private void turning() {
        autoSystems.turnToHeading(90);
        gamepad1.rumble(200);
    }

    private void manual_control() {
        double leftY = gamepad1.left_stick_y;
        double rightY = gamepad1.right_stick_y;

        double leftPower = Range.clip(leftY, -1.0, 1.0);
        double rightPower = Range.clip(rightY, -1.0, 1.0);

        driveBase.setMotorsPower(leftPower, rightPower);

        linear.setAllLinear(gamepad1.dpad_up, gamepad1.dpad_down);
        if (gamepad1.x) {
            driveBase.boost();
        }
        if (gamepad1.circle) {
            currentState = RobotState.TURNING;
        }
        if (gamepad1.triangle) {
            currentState = RobotState.IDLE;
        }
    }

    private double power(boolean pressed) {
        return (pressed) ? 1:0;
    }
}

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
    //private enum RobotState { IDLE, TURNING, MANUAL_CONTROL }
    //private RobotState currentState;

    public SemiAuto(LinearOpMode linearOpMode) {
        this.telemetry = linearOpMode.telemetry;
        this.driveBase = new Drivebase(linearOpMode);
        this.linear = new Linear(linearOpMode);
        this.gamepad1 = linearOpMode.gamepad1;
        this.gamepad2 = linearOpMode.gamepad2;
        this.dataflow = new Dataflow(this.telemetry);
        this.autoSystems = new AutoSystems(linearOpMode);
        //this.currentState = RobotState.IDLE;
        this.linear = new Linear(linearOpMode);
    }

    public void init() {
        driveBase.init();
        linear.init();
        autoSystems.init();
    }

    public void loop(LinearOpMode linearOpMode) {
        while (linearOpMode.opModeIsActive()) {
            manual_control();

            dataflow.addToAll(new String[]{"LeftBack:", "RightBack:"}, driveBase.getLeftPower(), driveBase.getRightPower());
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
        driveBase.setInTake(gamepad1.right_trigger - gamepad1.left_trigger);
        //Right sidee
        if(gamepad1.dpad_right) {
            linear.rightServoUp.setPower(1.0);
        } else if(gamepad1.right_bumper) {
            linear.rightServoDown.setPower(1.0);

        } else if(gamepad1.left_bumper) {
            linear.rightServoDown.setPower(-1.0);
        } else if(gamepad1.dpad_left) {
            linear.rightServoUp.setPower(-1.0);
        //Left side
        } else if(gamepad1.cross) {
            linear.leftServoUp.setPower(1.0);
        } else if(gamepad1.triangle) {
            linear.leftServoUp.setPower(-1.0);

        } else if(gamepad1.circle) {
            linear.leftServoDown.setPower(1.0);
        } else if(gamepad1.square) {
            linear.leftServoDown.setPower(-1.0);

        } else {
            linear.rightServoDown.setPower(0);
            linear.rightServoUp.setPower(0);
            linear.leftServoDown.setPower(0);
            linear.leftServoUp.setPower(0);
        }
    }
}

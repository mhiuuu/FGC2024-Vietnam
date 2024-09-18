package org.firstinspires.ftc.teamcode.tests;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.IMUHandler;
import org.firstinspires.ftc.teamcode.utils.Dataflow;

@TeleOp
@Config
public class IMU extends LinearOpMode {
    private IMUHandler imuHandler;
    private Dataflow dataflow;
    private double desireHeading;
    private double targetHeading = 45;
    @Override
    public void runOpMode() {
        this.imuHandler = new IMUHandler(this);
        this.dataflow = new Dataflow(this.telemetry);
        imuHandler.init();
        imuHandler.resetHeading();
        waitForStart();
        while(opModeIsActive()) {
            dataflow.addToAll(new String[]{"Current heading:", "Pivot", "How much turning"},
                                imuHandler.getHeading(),
                                        (imuHandler.getHeading() + 180) % 360,
                                        howMuchTurning(targetHeading));
            dataflow.sendDatas();
        }
    }

    private double howMuchTurning(double targetHeading) {
        double current = imuHandler.getHeading();
        double difference = targetHeading - current;
        difference = (difference + 360) % 360;
        if (difference > 180) {
            difference -= 360;
        }

        return difference;
    }
}
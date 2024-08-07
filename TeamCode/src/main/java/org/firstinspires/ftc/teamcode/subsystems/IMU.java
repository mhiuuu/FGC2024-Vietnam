package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class IMU {
    private BNO055IMU imu;
    private final HardwareMap hardwareMap;

    public IMU(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
    }

    public void init() {
        imu = hardwareMap.get(BNO055IMU.class, "IMU");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS; // We use radians as our basic units
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC; // We use m/s^2 as the unit for acceleration

        imu.initialize(parameters);
    }

    public double getYaw() {
        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).thirdAngle;
    }
}
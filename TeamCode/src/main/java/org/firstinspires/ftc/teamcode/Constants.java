package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Config
@Disabled
public class Constants {

    @Config
    public static class BASE {
        public static double COUNTS_PER_HD_MOTOR_REV = 28;
        //3:1=2.89:1  4:1==3.61:1 5:1=5.32:1
        public static double DRIVE_GEAR_REDUCTION = 2.89*3.61;
        public static double WHEEL_DIAMETER_INCHES = 9.00 / 2.54;
        public static double SMALL_WHEEL_DIAMETER_INCHES = 6.00 / 2.54;
        // These values are computed based on the above constants.
        public static double HD_COUNTS_PER_INCH = (COUNTS_PER_HD_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);
        public static double HD_SMALL_COUNTS_PER_INCH = (COUNTS_PER_HD_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (SMALL_WHEEL_DIAMETER_INCHES * Math.PI);

            }

    @Config
    public static class SPEED {
        public static double BOOST_DRIVE = 0.6;
        public static double NORMAL_DRIVE = 0.4;
        public static double LINEAR_SPEED = 1.0;
        public static double AUTO_DRIVE = 0.2;
    }

    @Config
    public static class SENSE {
        public static double JOYSTICK_SENSE = 0.05;
        public static float TRIGGER_SENSE = 0.2f;
    }

    @Config
    public static class SWIVEL {
        public static double TURN_SPEED = 0.4;
        public static double HEADING_THRESHOLD = 0.5;
        public static double P_TURN_GAIN = 0.01;
    }

    @Config
    public static class FIELD {
        // Taken from Official field OnShape CAD
        public static double HORIZONTAL_AUTO_REQUIREMENT = 30.03706;
    }
}

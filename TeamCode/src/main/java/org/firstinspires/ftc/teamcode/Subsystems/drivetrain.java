package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class drivetrain {

    private static final double DEADZONE = 0.1;

    private final DcMotor lMotor;
    private final DcMotor rMotor;

    public drivetrain(HardwareMap hardwareMap) {
        lMotor = hardwareMap.get(DcMotor.class, "lMotor");
        rMotor = hardwareMap.get(DcMotor.class, "rMotor");

        lMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        lMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void drive(double drive, double turn, double speed) {
        drive = deadzone(drive);
        turn = deadzone(turn);

        double left = Range.clip(drive + turn, -1.0, 1.0) * speed;
        double right = Range.clip(drive - turn, -1.0, 1.0) * speed;

        lMotor.setPower(left);
        rMotor.setPower(right);
    }

    public double getLeftPower() { return lMotor.getPower(); }
    public double getRightPower() { return rMotor.getPower(); }

    private double deadzone(double value) {
        return Math.abs(value) < DEADZONE ? 0 : value;
    }
}
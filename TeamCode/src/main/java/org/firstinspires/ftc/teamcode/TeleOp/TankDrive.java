package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.intake;

@TeleOp()
public class TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() {
        drivetrain drivetrain = new drivetrain(hardwareMap);
        intake intake = new intake(hardwareMap);

        boolean lastA = false;

        waitForStart();

        while (opModeIsActive()) {

            double speed = gamepad1.right_trigger > 0.1 ? 1.0 : 0.5;
            drivetrain.drive(-gamepad1.left_stick_y, -gamepad1.right_stick_x, speed);

            if (gamepad1.a && !lastA) intake.toggle();
            lastA = gamepad1.a;
            intake.update();

            telemetry.addData("Left Power", drivetrain.getLeftPower());
            telemetry.addData("Right Power", drivetrain.getRightPower());
            telemetry.addData("Intake On", intake.isOn());
            telemetry.update();
        }
    }
}
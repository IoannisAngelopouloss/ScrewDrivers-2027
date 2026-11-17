package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.intake;
import org.firstinspires.ftc.teamcode.Subsystems.servo;
import org.firstinspires.ftc.teamcode.Subsystems.shooter;

@TeleOp()
public class TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() {
        drivetrain drivetrain = new drivetrain(hardwareMap);
        intake intake = new intake(hardwareMap);
        shooter shooter = new shooter(hardwareMap);
        servo servo = new servo(hardwareMap);

        boolean lastA = false;
        boolean lastB = false;
        boolean lastX = false;
        boolean lastY = false;

        waitForStart();

        while (opModeIsActive()) {

            double speed = gamepad1.right_trigger > 0.1 ? 1.0 : 0.5;
            drivetrain.drive(-gamepad1.left_stick_y, -gamepad1.right_stick_x, speed);

            // A: intake on/off
            if (gamepad1.a && !lastA) intake.toggle();
            lastA = gamepad1.a;
            intake.update();

            // B: main shooter on/off
            if (gamepad1.b && !lastB) shooter.toggleMain();
            lastB = gamepad1.b;

            // X: middle shooter on/off
            if (gamepad1.x && !lastX) shooter.toggleMiddle();
            lastX = gamepad1.x;

            shooter.update();

            // Y: servo goes to 1, press again to go back to 0
            if (gamepad1.y && !lastY) servo.toggle();
            lastY = gamepad1.y;
            servo.update();

            telemetry.addData("Left Power", drivetrain.getLeftPower());
            telemetry.addData("Right Power", drivetrain.getRightPower());
            telemetry.addData("Intake On", intake.isOn());
            telemetry.addData("Shooter (B)", shooter.isMainOn());
            telemetry.addData("Middle Shooter (X)", shooter.isMiddleOn());
            telemetry.addData("Servo (Y)", servo.isOn() ? "1" : "0");
            telemetry.update();
        }

        shooter.stop();
    }
}
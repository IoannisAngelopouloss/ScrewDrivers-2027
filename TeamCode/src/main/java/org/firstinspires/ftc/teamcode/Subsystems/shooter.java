package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class shooter {

    // ---------- Main shooter settings ----------
    private static final String MAIN_MOTOR_NAME = "shooter";        // name in the Robot Configuration
    private static final Direction MAIN_DIRECTION = Direction.REVERSE; // flip to REVERSE if it spins the wrong way
    private static final double MAIN_POWER = 1.0;                   // 0..1

    private static final String MIDDLE_MOTOR_NAME = "middleShooter"; // name in the Robot Configuration
    private static final Direction MIDDLE_DIRECTION = Direction.FORWARD;
    private static final double MIDDLE_POWER = 1.0;                 // 0..1

    private final DcMotor mainMotor;
    private final DcMotor middleMotor;
    private boolean mainOn = false;
    private boolean middleOn = false;

    public shooter(HardwareMap hardwareMap) {
        mainMotor = hardwareMap.get(DcMotor.class, MAIN_MOTOR_NAME);
        middleMotor = hardwareMap.get(DcMotor.class, MIDDLE_MOTOR_NAME);

        mainMotor.setDirection(MAIN_DIRECTION);
        middleMotor.setDirection(MIDDLE_DIRECTION);

        for (DcMotor m : new DcMotor[]{mainMotor, middleMotor}) {
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            // FLOAT = coasts to a stop. Change to BRAKE to stop immediately.
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    public void toggleMain() {
        mainOn = !mainOn;
    }

    public void toggleMiddle() {
        middleOn = !middleOn;
    }

    public void update() {
        mainMotor.setPower(mainOn ? MAIN_POWER : 0.0);
        middleMotor.setPower(middleOn ? MIDDLE_POWER : 0.0);
    }

    public void stop() {
        mainOn = false;
        middleOn = false;
        mainMotor.setPower(0.0);
        middleMotor.setPower(0.0);
    }

    public boolean isMainOn() {
        return mainOn;
    }

    public boolean isMiddleOn() {
        return middleOn;
    }
}

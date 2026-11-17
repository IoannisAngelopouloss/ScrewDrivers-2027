package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {

    private final DcMotor motor;
    private boolean on = false;

    public intake(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "intake");
    }

    public void toggle() {
        on = !on;
    }

    public void update() {
        motor.setPower(on ? 1. : 0);
    }

    public boolean isOn() { return on; }
}
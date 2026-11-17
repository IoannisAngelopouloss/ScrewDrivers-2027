package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servo {

    // ---------- Settings ----------
    private static final String SERVO_NAME = "servo";
    private static final double POSITION_OFF = 0.0;
    private static final double POSITION_ON = 1.0;

    private final Servo servo;
    private boolean on = false;

    public servo(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, SERVO_NAME);
        servo.setPosition(POSITION_OFF);
    }

    public void toggle() {
        on = !on;
    }

    public void update() {
        servo.setPosition(on ? POSITION_ON : POSITION_OFF);
    }

    public boolean isOn() {
        return on;
    }
}
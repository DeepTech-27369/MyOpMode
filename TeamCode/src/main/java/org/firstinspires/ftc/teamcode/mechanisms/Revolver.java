package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Revolver {

    private CRServo revolverServo;
    private ElapsedTime timer = new ElapsedTime();

    private boolean isMoving = false;

    // TUNE THESE
    private static final double MOVE_POWER = 0.4; // 0.35-0.45
    private static final double ROTATION_TIME = 0.6; // seconds for ~120°, 0.5-0.7

    public void init(HardwareMap hwMap) {
        revolverServo = hwMap.get(CRServo.class, "revolverServo");
        stop();
    }

    // Called ONCE per button press
    public void nextPosition() {
        if (isMoving) return;   // ignore if already moving

        timer.reset();
        isMoving = true;
    }

    // Called EVERY loop
    public void update() {
        if (!isMoving) return;

        if (timer.seconds() < ROTATION_TIME) {
            revolverServo.setPower(MOVE_POWER);
        } else {
            stop();
            isMoving = false;
        }
    }

    public void stop() {
        revolverServo.setPower(0.0);
    }

    // Optional helper
    public boolean isBusy() {
        return isMoving;
    }
}
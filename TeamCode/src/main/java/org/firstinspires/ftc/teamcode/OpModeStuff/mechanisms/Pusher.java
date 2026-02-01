package org.firstinspires.ftc.teamcode.OpModeStuff.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Pusher {

    private static CRServo pusherServo;

    public void init(HardwareMap hwMap) {
        pusherServo = hwMap.get(CRServo.class, "pusherServo");

    }

    public static void run() {
        pusherServo.setPower(0.2);
    }

    public static void stop() {
        pusherServo.setPower(0.0);
    }
}

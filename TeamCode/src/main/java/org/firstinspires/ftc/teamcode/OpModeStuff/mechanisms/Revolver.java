package org.firstinspires.ftc.teamcode.OpModeStuff.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Revolver {

    private static CRServo revolverServo;
    private static CRServo pusherServo;
    private static ElapsedTime timer = new ElapsedTime();



    // Tune these
    private static final double indexPower = 0.35;
    private static final double manualPower = 0.135;
    private static final double one2_time = 0.875; // seconds for ~120°
    private static final double six_time = one2_time/2;
    private static boolean isIndexing = false;
    private static int sixOrTwelve = 0;


    public void init(HardwareMap hwMap) {
        revolverServo = hwMap.get(CRServo.class, "revolverServo");
        revolverServo.setPower(0.0);
        pusherServo = hwMap.get(CRServo.class, "pusherServo");
    }

    /** Call once to begin a 120-degree index */
    public static void startIndex(int degrees) {
        if (!isIndexing) {
            timer.reset();
            revolverServo.setPower(indexPower);
            isIndexing = true;
            sixOrTwelve = degrees;
        }
//
    }

    /** Call every loop() */
    public static void update() {
        if (sixOrTwelve == 0 && isIndexing && timer.seconds() >= one2_time) {
            revolverServo.setPower(0.0);
            isIndexing = false;
        }
        if (sixOrTwelve == 1 && isIndexing && timer.seconds() >= six_time) {
            revolverServo.setPower(0.0);
            isIndexing = false;
        }
    }
    public static void turnyTurn(int type) {
        if (type == 1) {
            revolverServo.setPower(0.35);
        }
        if (type == 2) {
            revolverServo.setPower(-0.35);
        }
    }
    public static void stoppyStop() {
        revolverServo.setPower(0.0);
    }

    public static boolean isBusy() {
        return isIndexing;
    }
}

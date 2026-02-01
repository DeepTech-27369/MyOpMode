
package org.firstinspires.ftc.teamcode.OpModeStuff.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private DcMotorEx leftShooter;
    private DcMotorEx rightShooter;

    // gobilda 5202 encoder
    private static final double TICKS_PER_REV = 28.0;

    // tune ts
    private static final double kP = 0.0006;

    private double targetRPM = 0.0;

    public void init(HardwareMap hwMap) {
        leftShooter = hwMap.get(DcMotorEx.class, "leftShooter");
        rightShooter = hwMap.get(DcMotorEx.class, "rightShooter");

        leftShooter.setDirection(DcMotor.Direction.FORWARD);
        rightShooter.setDirection(DcMotor.Direction.REVERSE);

        leftShooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightShooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        stop();
    }


    public void update() {
        double currentRPM = getRPM();
        double error = targetRPM - currentRPM;

        double power = error * kP;


        if (power > 1.0) power = 1.0;
        if (power < 0.0) power = 0.0;

        leftShooter.setPower(power);
        rightShooter.setPower(power);
    }

    public void setTargetRPM(double rpm) {
        targetRPM = rpm;
    }

    public void stop() {
        targetRPM = 0.0;
        leftShooter.setPower(0.0);
        rightShooter.setPower(0.0);
    }


    public double getRPM() {
        return (leftShooter.getVelocity() / TICKS_PER_REV) * 60.0;
    }
}

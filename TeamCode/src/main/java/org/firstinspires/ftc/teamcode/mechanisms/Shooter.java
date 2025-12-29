package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private DcMotor leftShooterMotor;
    private DcMotor rightShooterMotor;

    public void init(HardwareMap hwMap) {
        leftShooterMotor = hwMap.get(DcMotor.class, "leftShooter");
        rightShooterMotor = hwMap.get(DcMotor.class, "rightShooter");

        stop();
    }

    public void shoot() {
        leftShooterMotor.setPower(1.0);
        rightShooterMotor.setPower(-1.0);
    }

    public void stop() {
        leftShooterMotor.setPower(0.0);
        rightShooterMotor.setPower(0.0);
    }

}

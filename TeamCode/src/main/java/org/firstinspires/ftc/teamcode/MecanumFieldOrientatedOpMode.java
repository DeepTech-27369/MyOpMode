package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;

public class MecanumFieldOrientatedOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;
    Intake intake = new Intake();
    Shooter shooter = new Shooter();

    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
    }

    @Override
    public void loop() {
        // Drive
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);

        // Intake
        if (gamepad2.left_bumper) {
            intake.run();
        } else {
            intake.stop();
        }

        // Shooter
        if (gamepad2.right_bumper) {
            shooter.shoot();
        } else {
            shooter.stop();
        }
    }
}
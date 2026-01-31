package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.Revolver;

@TeleOp(name = "Mecanum Field Oriented", group = "TeleOp")
public class MecanumFieldOrientatedOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;
    Intake intake = new Intake();
    Shooter shooter = new Shooter();
    Revolver revolver = new Revolver();

    boolean lastA = false;

    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        revolver.init(hardwareMap);
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

        // Revolver
        boolean currentA = gamepad2.a;

        if (currentA && !lastA) {
            revolver.nextPosition();
        }

        lastA = currentA;

        revolver.update();
    }
}
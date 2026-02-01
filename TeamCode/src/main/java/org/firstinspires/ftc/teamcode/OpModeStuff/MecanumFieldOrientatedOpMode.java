package org.firstinspires.ftc.teamcode.OpModeStuff;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OpModeStuff.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.OpModeStuff.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.OpModeStuff.mechanisms.Pusher;
import org.firstinspires.ftc.teamcode.OpModeStuff.mechanisms.Revolver;
import org.firstinspires.ftc.teamcode.OpModeStuff.mechanisms.Shooter;

@SuppressWarnings("unused")
@TeleOp(name = "myOpMode", group = "TeleOp")
public class MecanumFieldOrientatedOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;
    Intake intake = new Intake();
    Shooter shooter = new Shooter();
    Revolver revolver = new Revolver();
    Pusher pusher = new Pusher();

    boolean shooterOn = false;
    boolean lastLongButton = false;
    boolean lastShortButton = false;
    double shooterRPM = 0.0;

    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        revolver.init(hardwareMap);
        pusher.init(hardwareMap);
    }

    @Override
    public void loop() {
        // Drive
        forward = - gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);

        // Intake
        if (gamepad2.a) {
            intake.run();
        } else if (gamepad2.b) {
            intake.runItBack();
        } else {
            intake.stop();
        }


        // Shooter
        boolean longButton = gamepad1.right_bumper;
        boolean shortButton = gamepad1.left_bumper;

        // Long shot
        if (longButton && !lastLongButton) {
            if (!shooterOn) {
                shooterOn = true;
                shooterRPM = 3600; // long distance
            } else if (shooterRPM == 3600) {
                shooterOn = false; // pressing same mode turns it off
            } else {
                shooterRPM = 3600; // switch from short -> long
            }
        }

        // Short shot
        if (shortButton && !lastShortButton) {
            if (!shooterOn) {
                shooterOn = true;
                shooterRPM = 2900; // short distance
            } else if (shooterRPM == 2900) {
                shooterOn = false; // pressing same mode turns it off
            } else {
                shooterRPM = 2900; // switch from long -> short
            }
        }

        lastLongButton = longButton;
        lastShortButton = shortButton;

        if (shooterOn) {
            shooter.setTargetRPM(shooterRPM);
        } else {
            shooter.setTargetRPM(0);
        }

        shooter.update();
        //revolver
        if (gamepad2.x) {
            Revolver.startIndex(0);
        } else if (gamepad2.y) {
            Revolver.startIndex(1);
        }

        if (gamepad2.dpad_up) {
            Pusher.run();
        }
        else {
            Pusher.stop();
        }
        if (gamepad2.dpad_right) {
            Revolver.turnyTurn(1);
        } else if (gamepad2.dpad_left) {
            Revolver.turnyTurn(2);
        } else if (!gamepad2.dpad_right && !Revolver.isBusy() && !gamepad2.dpad_left){
            Revolver.stoppyStop();
        }

//
        if (gamepad2.x && !Revolver.isBusy()) {
            Revolver.startIndex(0);
        }
        if (gamepad2.y && !Revolver.isBusy()) {
            Revolver.startIndex(1);
        }


        Revolver.update();

        // Telemetry
        telemetry.addData("Shooter RPM", shooter.getRPM());
        telemetry.addData("ball pusher", gamepad2.dpad_up);
        telemetry.addData("Revolver Busy", Revolver.isBusy());
        telemetry.addData("x pressed?", gamepad2.x);
        telemetry.update();
    }

}

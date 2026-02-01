package org.firstinspires.ftc.teamcode.AutonomousCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.AutonomousCode.mechanisms.Shooter;
import org.json.JSONArray;


@SuppressWarnings("unused")
@Autonomous(name="shooterNear", group="Autonomous")
public class OtherMainAuto extends LinearOpMode {

    private DcMotor frontLeftMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backRightMotor = null;
    Shooter shooter = new Shooter();
    double shooterRPM = 3200.0;
    boolean shooterOn = false;
    private DcMotor leftShooter;
    private DcMotor rightShooter;
    private HardwareMap hwMap;
    @Override
    public void runOpMode() {

        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");

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
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        moveBackward(0.5, 800);

        shooter.setTargetRPM(shooterRPM);
        boolean shooterOn = true;
        shooter.update();

        stopMotors();
    }

    private void moveForward(double power, long duration) {
        double forward = - power;
        double strafe = 0;
        double rotate = 0;

        double fl = forward + strafe - rotate;
        double bl = -forward - strafe - rotate;
        double fr = forward - strafe + rotate;
        double br = -(-forward + strafe + rotate);

        frontLeftMotor.setPower(fl);
        backLeftMotor.setPower(bl);
        frontRightMotor.setPower(fr);
        backRightMotor.setPower(br);

        telemetry.addData("Movement", "FORWARD");
        telemetry.addData("FL Power", fl);
        telemetry.addData("BL Power", bl);
        telemetry.addData("FR Power", fr);
        telemetry.addData("BR Power", br);
        telemetry.update();

        sleep(duration);
    }

    private void moveBackward(double power, long duration) {
        double forward = power;
        double strafe = 0;
        double rotate = 0;

        double fl = forward + strafe - rotate;
        double bl = -forward - strafe - rotate;
        double fr = forward - strafe + rotate;
        double br = -(-forward + strafe + rotate);

        frontLeftMotor.setPower(fl);
        backLeftMotor.setPower(bl);
        frontRightMotor.setPower(fr);
        backRightMotor.setPower(br);

        telemetry.addData("Movement", "BACKWARD");
        telemetry.addData("FL Power", fl);
        telemetry.addData("BL Power", bl);
        telemetry.addData("FR Power", fr);
        telemetry.addData("BR Power", br);
        telemetry.update();

        sleep(duration);
    }

    private void strafe(double power, long duration) {
        double forward = 0;
        double strafe = power;
        double rotate = 0;

        double fl = forward + strafe - rotate;
        double bl = -forward - strafe - rotate;
        double fr = forward - strafe + rotate;
        double br = -(-forward + strafe + rotate);

        frontLeftMotor.setPower(fl);
        backLeftMotor.setPower(bl);
        frontRightMotor.setPower(fr);
        backRightMotor.setPower(br);

        String direction = power > 0 ? "RIGHT STRAFE" : "LEFT STRAFE";
        telemetry.addData("Movement", direction);
        telemetry.addData("FL Power", fl);
        telemetry.addData("BL Power", bl);
        telemetry.addData("FR Power", fr);
        telemetry.addData("BR Power", br);
        telemetry.update();

        sleep(duration);
    }

    private void turn(double power, long duration) {
        double forward = 0;
        double strafe = 0;
        double rotate = power;

        double fl = forward + strafe - rotate;
        double bl = -forward - strafe - rotate;
        double fr = forward - strafe + rotate;
        double br = -(-forward + strafe + rotate);

        frontLeftMotor.setPower(fl);
        backLeftMotor.setPower(bl);
        frontRightMotor.setPower(fr);
        backRightMotor.setPower(br);

        String direction = power > 0 ? "RIGHT TURN" : "LEFT TURN";
        telemetry.addData("Movement", direction);
        telemetry.addData("FL Power", fl);
        telemetry.addData("BL Power", bl);
        telemetry.addData("FR Power", fr);
        telemetry.addData("BR Power", br);
        telemetry.update();

        sleep(duration);
    }

    private void stopMotors() {
        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        backRightMotor.setPower(0.0);
    }
}

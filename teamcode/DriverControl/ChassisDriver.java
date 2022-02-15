package org.firstinspires.ftc.teamcode.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
//@Disabled


public class ChassisDriver extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        // Declare four mecanum motors

        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        // Declare servos
        //CRServo carouselservo1 = hardwareMap.crservo.get("carouselservo1");
        DcMotor carouselmotor1 = hardwareMap.dcMotor.get("carouselmotor1");
        DcMotor liftmotor = hardwareMap.dcMotor.get("liftmotor"); // 2 expansion
        CRServo capservo = hardwareMap.crservo.get("capservo");//0 expansion
        CRServo intakeservo = hardwareMap.crservo.get("intakeservo");

        // Sleep method
        //Thread.sleep(200);
        // Reverse the right side motors
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        // Reverse left motors
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);


        if (Thread.interrupted()) {
            telemetry.addData("<", "Name doesn't match the configuration");
        }
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {//keep looping
            //detecting drift of the stick
            if (gamepad1.left_stick_x >= 0.2
                    || gamepad1.left_stick_x <= 0.2
                    || gamepad1.left_stick_y >= 0.2
                    || gamepad1.left_stick_y <= 0.2
                    || gamepad1.right_stick_x >= 0.2
                    || gamepad1.right_stick_x <= 0.2) {

                motorSetting(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight);

            }
            servoSetting(carouselmotor1, capservo, liftmotor, intakeservo);
        }
    }
// ------------------------------------Function Below----------------------------------------

    //motorSetting <setup the power of the motor>
    public void motorSetting(DcMotor motorFrontLeft, DcMotor motorBackLeft,
                             DcMotor motorFrontRight, DcMotor motorBackRight) {

        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;


        motorFrontLeft.setPower(frontLeftPower);
        motorBackLeft.setPower(backLeftPower);
        motorFrontRight.setPower(frontRightPower);
        motorBackRight.setPower(backRightPower);

    }

    //servoSetting <setup the power of the motor>
    public void servoSetting(DcMotor carouselmotor1, CRServo capservo, DcMotor liftmotor, CRServo intakeservo) {

        carouselmotor1.setPower(gamepad1.left_trigger);
        intakeservo.setPower(gamepad2.left_trigger);
        intakeservo.setPower(-gamepad2.right_trigger);

        if (gamepad2.b) {
            capservo.setPower(0.3);
        } else if (gamepad2.x) {
            capservo.setPower(-0.3);
        }else{
            capservo.setPower(0);
        }
        if (gamepad2.dpad_up) {
            liftmotor.setPower(1);
        } else if (gamepad2.dpad_down) {
            liftmotor.setPower(-1);
        } else {
            liftmotor.setPower(0);
        } if (gamepad2.y) { // y stop button
            capservo.setPower(0);
            carouselmotor1.setPower(0);
            liftmotor.setPower(0);
            intakeservo.setPower(0);

        }
    }
}

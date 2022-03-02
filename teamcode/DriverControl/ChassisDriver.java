package org.firstinspires.ftc.teamcode.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
//@Disabled


public class ChassisDriver extends LinearOpMode {
        DcMotor motorFrontLeft;
        DcMotor motorBackLeft;
        DcMotor motorFrontRight;
        DcMotor motorBackRight;
        DcMotor carouselmotor1;
        DcMotor carouselmotor2;
        DcMotor liftmotor;
        CRServo capservo;
        CRServo intakeservo;
        DistanceSensor dsensor1;
        DigitalChannel tsensor;



    @Override
    public void runOpMode() throws InterruptedException {

        // Declare four mecanum motors

        // Make sure your ID's match your configuration
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");


        // Declare servos
        //CRServo carouselservo1 = hardwareMap.crservo.get("carouselservo1");
        carouselmotor1 = hardwareMap.dcMotor.get("carouselmotor1");
        carouselmotor2= hardwareMap.dcMotor.get("carouselmotor2");
        liftmotor = hardwareMap.dcMotor.get("liftmotor"); // 2 expansion
        capservo = hardwareMap.crservo.get("capservo");//0 expansion
        intakeservo = hardwareMap.crservo.get("intakeservo");
        DistanceSensor dsensorL = hardwareMap.get(DistanceSensor.class, "dsensorLeft");
        DistanceSensor dsensorR = hardwareMap.get(DistanceSensor.class, "dsensorRight");
        dsensor1 = hardwareMap.get(DistanceSensor.class, "dsensor1");
        tsensor = hardwareMap.get(DigitalChannel.class, "tsensor1");
        tsensor.setMode(DigitalChannel.Mode.INPUT);
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
        boolean tk = false;
        if (isStopRequested()) return;

        while (opModeIsActive()) {//keep looping
            //detecting drift of the stick

            if(gamepad1.back){
                tk = !tk;
            }
            if(tk){
                telemetry.addData("TK: ", "ON");
                tankDrive();

            }else {
                telemetry.addData("TK: ", "OFF");
                if (gamepad1.left_stick_x >= 0.2
                        || gamepad1.left_stick_x <= 0.2
                        || gamepad1.left_stick_y >= 0.2
                        || gamepad1.left_stick_y <= 0.2
                        || gamepad1.right_stick_x >= 0.2
                        || gamepad1.right_stick_x <= 0.2) {

                    motorSetting();

                }


            }
            servoSetting();
            telemetry.addData("DistancesensorLeft: ", dsensorL.getDistance(DistanceUnit.CM));
            telemetry.addData("DistancesensorRight: ", dsensorR.getDistance(DistanceUnit.CM));
            telemetry.addData("DistancesensorFront: ", dsensor1.getDistance(DistanceUnit.CM));

            telemetry.update();
        }
    }
// ------------------------------------Function Below----------------------------------------

    //motorSetting <setup the power of the motor>
    public void motorSetting() {

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
    public void servoSetting() throws InterruptedException {

        carouselmotor1.setPower(gamepad1.left_trigger);
        carouselmotor2.setPower(gamepad1.left_trigger);

        if (gamepad2.b) {
            capservo.setPower(0.3);
        } else if (gamepad2.x) {
            capservo.setPower(-0.3);
        } else {
            capservo.setPower(0);
        }
        if (gamepad1.right_bumper && !tsensor.getState()) {
            liftmotor.setPower(1);
        } else if (gamepad1.right_trigger > 0.2) {
            liftmotor.setPower(-1);
        } else {
            liftmotor.setPower(0);
        }
        if (gamepad1.left_bumper) {
            intakeservo.setPower(-1);

        } else if (gamepad1.left_trigger > 0.2) {
            intakeservo.setPower(1);

        } else {
            intakeservo.setPower(0);
        }


    }

    public void tankDrive() {

        motorFrontLeft.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x);
        motorBackLeft.setPower(-gamepad1.left_stick_y - gamepad1.left_stick_x);
        motorFrontRight.setPower(-gamepad1.right_stick_y - gamepad1.left_stick_x);
        motorBackRight.setPower(-gamepad1.right_stick_y + gamepad1.left_stick_x);


    }

    //publci void upward automattically
    }



package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous()
public class autonomous extends LinearOpMode {
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


        waitForStart();

    }

    public void moverForward(double pow) {
        motorFrontLeft.setPower(pow);
        motorBackRight.setPower(-pow);
        motorFrontRight.setPower(-pow);
        motorBackLeft.setPower(pow);

    }

    public void moveRight(double pow) {
        motorFrontLeft.setPower(pow);
        motorFrontRight.setPower(pow);
        motorBackLeft.setPower(-pow);
        motorBackRight.setPower(-pow);

    }

    public void turnleft(double pow) {
        motorFrontLeft.setPower(pow);
        motorBackRight.setPower(pow);
        motorFrontRight.setPower(pow);
        motorBackLeft.setPower(pow);

    }

    public void moveLeft(double pow) {
        motorFrontLeft.setPower(-pow);
        motorFrontRight.setPower(-pow);
        motorBackLeft.setPower(pow);
        motorBackRight.setPower(pow);
    }

}

//Foward

        /*Right
        moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0.7);
        Thread.sleep(1500);
        moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        //Backward
        moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -0.3);
        Thread.sleep(290);
        moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        Thread.sleep(1500);
        //Carousel's
        carouselmotor1.setPower(3);
        Thread.sleep(5000);
        //Forward
        moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 1);
        Thread.sleep(50);
        moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        Thread.sleep(800);
        TurnLeft
            to warehouse
        moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -0.7);
        Thread.sleep(300);
        moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        turnleft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -0.7);
        Thread.sleep(960);
        turnleft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        Thread.sleep(500);
        //Forward (slow down when reach the barrier)
        moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 1);
        Thread.sleep(2600);
        moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0.7);
        Thread.sleep(500);
        //Done park (32points)
        moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);*/

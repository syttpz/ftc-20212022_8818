package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class autonomous extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {


        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        // Declare servos
        DcMotor carouselmotor1 = hardwareMap.dcMotor.get("carouselmotor1");
        waitForStart();
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

    }

    public void moverForward(DcMotor motorFrontLeft, DcMotor motorBackLeft,
                             DcMotor motorFrontRight, DcMotor motorBackRight, double pow) {
        motorFrontLeft.setPower(pow);
        motorBackRight.setPower(-pow);
        motorFrontRight.setPower(-pow);
        motorBackLeft.setPower(pow);

    }

    public void moveRight(DcMotor motorFrontLeft, DcMotor motorBackLeft,
                          DcMotor motorFrontRight, DcMotor motorBackRight, double pow) {
        motorFrontLeft.setPower(pow);
        motorFrontRight.setPower(pow);
        motorBackLeft.setPower(-pow);
        motorBackRight.setPower(-pow);

    }

    public void turnleft(DcMotor motorFrontLeft, DcMotor motorBackLeft,
                         DcMotor motorFrontRight, DcMotor motorBackRight, double pow) {
        motorFrontLeft.setPower(pow);
        motorBackRight.setPower(pow);
        motorFrontRight.setPower(pow);
        motorBackLeft.setPower(pow);

    }

    public void moveLeft(DcMotor motorFrontLeft, DcMotor motorBackLeft,
                         DcMotor motorFrontRight, DcMotor motorBackRight, double pow) {
        motorFrontLeft.setPower(-pow);
        motorFrontRight.setPower(-pow);
        motorBackLeft.setPower(pow);
        motorBackRight.setPower(pow);
    }

}
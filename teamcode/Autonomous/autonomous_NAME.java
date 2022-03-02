package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

//@Autonomous(Name, Group)
public class autonomous_NAME extends LinearOpMode {

    DcMotor motorFrontLeft;


    @Override
    public void runOpMode() throws InterruptedException {


        //Get motor, make sure name matches in the configuration file
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DistanceSensor dsensor = hardwareMap.get(DistanceSensor.class, "dsensor1");
        TouchSensor tsensor = hardwareMap.get(TouchSensor.class, "tsensor1");
        DcMotor liftmotor = hardwareMap.dcMotor.get("liftmotor"); // 2 expansion


        //Reset encoders first reset the count to 0

        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        //Repeat
        motorFrontLeft.setTargetPosition(1000); //Encoder count
        motorFrontLeft.setPower(1);
        motorFrontLeft.setPower(0);

        motorFrontLeft.setPower(-1);
        Thread.sleep(100); //millisecond
        motorFrontLeft.setPower(0);



        //Pulley

        while(!tsensor.isPressed()){
            liftmotor.setPower(1);
        }
        liftmotor.setPower(0);
    }


}

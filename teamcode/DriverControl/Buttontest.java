package org.firstinspires.ftc.teamcode.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/*This is for game button testing, value will shown in the phone*/
@TeleOp
public class Buttontest extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        telemetry.addData("X",gamepad1.x);
        telemetry.addData("Y",gamepad1.y);
        telemetry.addData("A",gamepad1.a);
        telemetry.addData("B",gamepad1.b);
        telemetry.addData("lefttrigger",gamepad1.left_trigger);
        telemetry.addData("righttrigger",gamepad1.right_trigger);
        telemetry.addData("leftbumper",gamepad1.left_bumper);
        telemetry.addData("rightbumper",gamepad1.right_bumper);
        telemetry.addData("leftstickbutton",gamepad1.left_stick_button);
        telemetry.addData("Rightstickbutton",gamepad1.right_stick_button);

    }
}


/* */



package org.firstinspires.ftc.teamcode.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/*This is for game button testing, value will shown in the phone*/
@TeleOp
public class Buttontest extends OpMode {
    @Override
    public void init() {


    }

    @Override
    public void loop() {
        DistanceSensor dsensorL = hardwareMap.get(DistanceSensor.class, "dsensorLeft");
        DistanceSensor dsensorR = hardwareMap.get(DistanceSensor.class, "dsensorRight");
        DistanceSensor dsensor1 = hardwareMap.get(DistanceSensor.class, "dsensor1");
        //TouchSensor tsensor = hardwareMap.get(TouchSensor.class, "tsensor1");
        //ColorSensor csensor = hardwareMap.get(ColorSensor.class, "csensor1");
        telemetry.addData("X",gamepad1.x);
        telemetry.addData("Y",gamepad1.y);
        telemetry.addData("A",gamepad1.a);
        telemetry.addData("B",gamepad1.b);
        telemetry.addData("Leftstick_x: ",gamepad1.left_stick_x);
        telemetry.addData("Leftstick_y: ",gamepad1.left_stick_y);
        telemetry.addData("Rightstick_x: ",gamepad1.right_stick_x);
        telemetry.addData("Rightstick_y: ",gamepad1.right_stick_y);
        telemetry.addData("lefttrigger",gamepad1.left_trigger);
        telemetry.addData("righttrigger",gamepad1.right_trigger);
        telemetry.addData("leftbumper",gamepad1.left_bumper);
        telemetry.addData("rightbumper",gamepad1.right_bumper);
        telemetry.addData("leftstickbutton",gamepad1.left_stick_button);
        telemetry.addData("Rightstickbutton",gamepad1.right_stick_button);
        telemetry.addData("DistancesensorLeft: ", dsensorL.getDistance(DistanceUnit.CM));
        telemetry.addData("DistancesensorRight: ", dsensorR.getDistance(DistanceUnit.CM));
        telemetry.addData("DistancesensorFront: ", dsensor1.getDistance(DistanceUnit.CM));
        //telemetry.addData("tsensor: ", tsensor.getValue());
        //255, 255, 255 white
        //telemetry.addData("colorsensor_redvalue: ", csensor.red());
        //telemetry.addData("colorsensor_bluevalue: ", csensor.blue());
        //telemetry.addData("colorsensor_greenvalue: ", csensor.green());

    }
}


/* */

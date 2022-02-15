package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name = "8818autonomous")
public class automode_three extends LinearOpMode {
    OpenCvWebcam webcam;

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        DcMotor carouselmotor1 = hardwareMap.dcMotor.get("carouselmotor1");
        DcMotor liftmotor = hardwareMap.dcMotor.get("liftmotor"); // 2 expansion
        CRServo capservo = hardwareMap.crservo.get("capservo");//0 expansion
        CRServo intakeservo = hardwareMap.crservo.get("intakeservo");

        // Declare servos


        CameraDetection cameraDetection = new CameraDetection();
        autonomous robot = new autonomous();
        //Webcam
        CameraDetection a = new CameraDetection();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam 1"), cameraMonitorViewId);

        webcam.setPipeline(cameraDetection);

        webcam.setMillisecondsPermissionTimeout(1000);
        //Open Camera Asynchronously
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT); //320*240 resolution
            }
            @Override
            public void onError(int errorCode) {

            }
        });

        telemetry.addLine("Camera Starting...");
        telemetry.update();




        waitForStart();

        ShippingHubLevel level = cameraDetection.getShippingHubLevel();
        telemetry.addData("Detected duck position: ", level);
        telemetry.update();

        webcam.stopStreaming();

        sleep(50);

        if(level == ShippingHubLevel.RIGHTSIDE){
            liftmotor.setPower(1);
            Thread.sleep(3000);
            liftmotor.setPower(0);
            robot.moveLeft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 1);
            Thread.sleep(700);
            robot.moveLeft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
            Thread.sleep(200);

            robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0.6);
            Thread.sleep(930);
            robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
            Thread.sleep(100);
            intakeservo.setPower(-1);
            Thread.sleep(600);
            intakeservo.setPower(0);
            Thread.sleep(100);

            robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -0.6);
            Thread.sleep(930);
            robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
            Thread.sleep(500);

            robot.moveLeft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -1);
            Thread.sleep(700);
            robot.moveLeft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);



        }else if(level == ShippingHubLevel.MIDDLE || level == ShippingHubLevel.LEFTSIDE){
            liftmotor.setPower(1);
            Thread.sleep(5400);
            liftmotor.setPower(0);
            robot.moveLeft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 1);
            Thread.sleep(700);
            robot.moveLeft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
            Thread.sleep(200);

            robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0.5);
            Thread.sleep(1250);
            robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
            Thread.sleep(800);
            intakeservo.setPower(-1);
            Thread.sleep(600);
            intakeservo.setPower(0);
            Thread.sleep(100);

            robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -0.5);
            Thread.sleep(1250);
            robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
            Thread.sleep(700);

            liftmotor.setPower(-1);
            robot.moveLeft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -1);
            Thread.sleep(700);
            robot.moveLeft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
            Thread.sleep(2000);
            liftmotor.setPower(0);


        }
        //Start from init position, drive to warehouse
        robot.moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 1);
        Thread.sleep(550);
        robot.moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        Thread.sleep(100);
        robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 1);
        Thread.sleep(400);
        robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        Thread.sleep(100);
        robot.moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 1);
        Thread.sleep(400);
        robot.moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        Thread.sleep(100);
        robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -0.3);
        Thread.sleep(850);
        robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        Thread.sleep(100);

        //Carousel's
        carouselmotor1.setPower(0.4);
        Thread.sleep(3600);
        carouselmotor1.setPower(0);
        Thread.sleep(1000);
        //Forward
        robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 1);
        Thread.sleep(70);
        robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        Thread.sleep(800);
        /*TurnLeft
            to warehouse*/
        robot.moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -0.7);
        Thread.sleep(300);
        robot.moveRight(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);

        robot.turnleft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, -0.7);
        Thread.sleep(960);
        robot.turnleft(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);
        Thread.sleep(200);
        //Forward (crossing barrier)
        robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 1);
        Thread.sleep(3500);
        robot.moverForward(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, 0);






    }

        //Pipeline
    class CameraDetection extends OpenCvPipeline {

        //private volatile
        private volatile ShippingHubLevel shippingHubLevel = ShippingHubLevel.NOTFOUND;



        Mat mat = new Mat();
        //Submat
        final Rect LEFT_ROI = new Rect(
                new Point(20, 20),
                new Point(170, 200));
        final Rect MIDDLE_ROI = new Rect(
                new Point(180, 20),
                new Point(280, 200));


        /*We only use two submats for the left
        * and middle position
        * */

        @Override
        public Mat processFrame(Mat input) {


            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV); // Convert from RGB to HSV


            //Lower and upper color range bound
            Scalar lowHSV = new Scalar(23, 50, 70); // 23 50 70
            Scalar highHSV = new Scalar(32, 255, 255); // 32 255 255

            // Yellow pixel in range
            Core.inRange(mat, lowHSV, highHSV, mat);

            Mat left = mat.submat(LEFT_ROI);
            Mat mid = mat.submat(MIDDLE_ROI);



            double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
            double midvalue = Core.sumElems(mid).val[0] / MIDDLE_ROI.area() / 255;

            //mat release
            left.release();
            mid.release();

            //percentage of pixel with the hsv range in the two region
            long leftpercent = Math.round(leftValue * 100);
            long midpercent = Math.round(midvalue * 100);


            telemetry.addData("Left percentage", leftpercent + "%");
            telemetry.addData("Mid percentage", midpercent + "%");


            if ((midpercent + leftpercent < 4 )){
                shippingHubLevel = ShippingHubLevel.RIGHTSIDE;
                telemetry.addData("Duck position: ", shippingHubLevel);
                /*Since we are only analyzing two duck positions
                * in the frame, if both percentage is lower
                * then 4% (Threshold). Then we consider the
                * duck in the other position
                * */
            }
            /*If the percent in either region is
            * greater then the other, we set the
            * enum value to 'shippinghublevel'
            * correspond to that region.*/

            else if (midpercent > leftpercent) {
                shippingHubLevel = ShippingHubLevel.MIDDLE;
                telemetry.addData("Duck position: ", shippingHubLevel);

            } else if (leftpercent > midpercent) {
                shippingHubLevel = ShippingHubLevel.LEFTSIDE;
                telemetry.addData("Duck position: ", shippingHubLevel);

            }

            telemetry.update();

            return mat;


        }
        public ShippingHubLevel getShippingHubLevel() {
                return shippingHubLevel;
            }



    }
}

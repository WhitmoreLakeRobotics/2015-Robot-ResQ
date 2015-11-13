package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

/**
 * Created by techclub on 9/12/15.
 */
public class operatormode2 extends OpMode {
    private DcMotorController dc_left_controller;
    private DcMotorController dc_right_controller;
    private ServoController servoController;
    private DcMotor dc_drive_left;
    private Servo bucket_servo;
    private Servo left_slide;
    private Servo right_slide;
    private DcMotor dc_drive_right;
    private DcMotor dc_4link;
    private TwoMotorDrive rightWheel;
    private TwoMotorDrive leftWheel;
    private int fourLinkUpper = 1920;
    private int fourLinkLower = 0;
    private DcMotor dc_drive_right2;
    private DcMotor dc_drive_left2;
    private DcMotor dc_sweeper;
    private double bucket_left_out = 0.25;
    private double bucket_in = 0.5;
    private double bucket_right_out = 0.75;
    private double left_slide_in = 0.0;
    private double left_slide_out = 0.3;
    private double right_slide_in = 0.0;
    private double right_slide_out = 0.3;

    @Override
    public void init() {
        dc_left_controller = hardwareMap.dcMotorController.get("left");
        dc_right_controller = hardwareMap.dcMotorController.get("right");
        /*servoController = hardwareMap.servoController.get("servoController");
        bucket_servo = hardwareMap.servo.get("bucket_servo");
        left_slide = hardwareMap.servo.get("left_slide");
        right_slide = hardwareMap.servo.get("right_slide");
       */ dc_drive_left = hardwareMap.dcMotor.get("left_drive");
       // dc_4link = hardwareMap.dcMotor.get("4link");
        dc_drive_right = hardwareMap.dcMotor.get("right_drive");
        dc_drive_right2 = hardwareMap.dcMotor.get("right_drive2");
        dc_drive_left2 = hardwareMap.dcMotor.get("left_drive2");
        dc_sweeper = hardwareMap.dcMotor.get("sweeper");
        dc_drive_left2.setDirection(DcMotor.Direction.REVERSE);
        dc_drive_left.setDirection(DcMotor.Direction.REVERSE);
        leftWheel = new TwoMotorDrive(dc_drive_left, dc_drive_left2);
        rightWheel = new  TwoMotorDrive(dc_drive_right, dc_drive_right2);
       /* dc_4link.setChannelMode
                ( DcMotorController.RunMode.RUN_USING_ENCODERS
                );*/

    }

    @Override
    public void loop() {
        /*if (gamepad2.a && bucket_servo.getPosition()!= bucket_in) { //sets bucket to normal with a button
            bucket_servo.setPosition(bucket_in);
        }
        if (gamepad2.a && bucket_servo.getPosition()!=bucket_left_out) { //sets bucket to dump left with a button
            bucket_servo.setPosition(bucket_left_out);

        }

        if (gamepad2.y && bucket_servo.getPosition()!=bucket_in) { //sets bucket to normal with y button
            bucket_servo.setPosition(bucket_in);
        }
        if (gamepad2.y && bucket_servo.getPosition()!=bucket_right_out) { //sets bucket to dump right with y button
            bucket_servo.setPosition(bucket_right_out);

        }

        if (gamepad2.x && left_slide.getPosition()!=left_slide_in) { //sets left slide to in with x button
            left_slide.setPosition(left_slide_in);
        }
        if (gamepad2.x && left_slide.getPosition()!=left_slide_out) { //sets left slide to out with x button
            left_slide.setPosition(left_slide_out);

        }

        if (gamepad2.b && right_slide.getPosition()!=right_slide_in) { //sets right slide to in with b button
            right_slide.setPosition(right_slide_in);
        }
        if (gamepad2.b && right_slide.getPosition()!=right_slide_out) { //sets right slide to out with b button
            right_slide.setPosition(right_slide_out);

        }

        if ( (dc_4link.getCurrentPosition()<=fourLinkLower && gamepad2.right_stick_y > 0.0 ) ||                 //
        (dc_4link.getCurrentPosition()>=fourLinkUpper && gamepad2.right_stick_y < 0.0) ||                       //Crazy Long 'If' statement 2.0!
                (dc_4link.getCurrentPosition()< fourLinkUpper && dc_4link.getCurrentPosition()>fourLinkLower)){ //
            dc_4link.setPower(gamepad2.right_stick_y);

        }*/
        dc_sweeper.setPower(gamepad2.left_stick_y);
        leftWheel.setPower(gamepad1.left_stick_y);
        rightWheel.setPower(gamepad1.right_stick_y);
    }
}
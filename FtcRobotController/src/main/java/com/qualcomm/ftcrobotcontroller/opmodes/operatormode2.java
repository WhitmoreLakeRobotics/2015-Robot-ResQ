package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by techclub on 9/12/15.
 */
public class operatormode2 extends OpMode {
    private DcMotorController dc_left_controller;
    private DcMotorController dc_right_controller;
    private ServoController servoController;

    private DcMotor dc_drive_left;
    private Servo servoDump;
    private Servo servoThrow;
    private Servo test;
    private DcMotor dc_drive_right;
    private DcMotor dc_4link;
    private TwoMotorDrive rightWheel;
    private TwoMotorDrive leftWheel;
    private int fourLinkUpper = 1920;
    private int fourLinkLower = 0;
    private DcMotor dc_drive_right2;
    private DcMotor dc_drive_left2;

    private double test_pos;
    private double step_size = 0.05;
    private DcMotor dc_sweeper;
    private double bucket_left_out = 0.25;
    private double bucket_in = 0.5;
    private double bucket_right_out = 0.75;
    private double left_slide_in = 0.0;
    private double left_slide_out = 0.3;
    private double right_slide_in = 0.0;
    private double right_slide_out = 0.3;
    private double fourlink_pos;
    @Override
    public void init() {
        dc_left_controller = hardwareMap.dcMotorController.get("left");
        dc_right_controller = hardwareMap.dcMotorController.get("right");
        //test = hardwareMap.servo.get("test");

        servoDump = hardwareMap.servo.get("Dump");
        servoThrow = hardwareMap.servo.get("throw");
        /*servoController = hardwareMap.servoController.get("servoController");
        bucket_servo = hardwareMap.servo.get("bucket_servo");
        left_slide = hardwareMap.servo.get("left_slide");
        right_slide = hardwareMap.servo.get("right_slide");
       */ dc_drive_left = hardwareMap.dcMotor.get("left_drive");
        dc_4link = hardwareMap.dcMotor.get("4link");
        dc_drive_right = hardwareMap.dcMotor.get("right_drive");
        dc_drive_right2 = hardwareMap.dcMotor.get("right_drive2");
        dc_drive_left2 = hardwareMap.dcMotor.get("left_drive2");
        dc_sweeper = hardwareMap.dcMotor.get("sweeper");
        dc_drive_left2.setDirection(DcMotor.Direction.REVERSE);
        dc_drive_left.setDirection(DcMotor.Direction.REVERSE);
        leftWheel = new TwoMotorDrive(dc_drive_left, dc_drive_left2);
        rightWheel = new  TwoMotorDrive(dc_drive_right, dc_drive_right2);
        dc_4link.setChannelMode
                ( DcMotorController.RunMode.RUN_USING_ENCODERS
                );

    }

    @Override
    public void loop() {

        //testing servos 1 by 1
        //test_pos = test.getPosition();
       // telemetry.addData("test position:",test_pos );
        if(gamepad2.y){
            servoDump.setPosition(0.5 );
        }
        if(gamepad2.x){
            servoDump.setPosition(0.33 );
        }
        if(gamepad2.b){
            servoDump.setPosition(0.66 );
        }
        servoThrow.setPosition(1 - gamepad2.left_trigger);


        dc_sweeper.setPower(gamepad2.left_stick_y);
        leftWheel.setPower(gamepad1.left_stick_y);
        rightWheel.setPower(gamepad1.right_stick_y);

        /*
        if (gamepad2.right_stick_y!=0.0){
             fourlink_pos = dc_4link.getCurrentPosition();
            telemetry.addData("encoder Ticks",fourlink_pos);
            if (gamepad2.right_stick_y > 0.0){
                if (fourlink_pos < fourLinkUpper){
                   dc_4link.setPower(gamepad2.right_stick_y);
                }else{
                    dc_4link.setPower(0.0);
                }
            }else{
                if (fourlink_pos > fourLinkLower){
                    dc_4link.setPower(gamepad2.right_stick_y);
                }else{
                    dc_4link.setPower(0.0);
                }
            }
        }else dc_4link.setPower(0.0);
        */
        dc_4link.setPower(gamepad2.right_stick_y);

    }
}
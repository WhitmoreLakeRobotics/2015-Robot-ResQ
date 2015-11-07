package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by techclub on 9/12/15.
 */
public class operatormode2 extends OpMode {
    private DcMotorController dc_drive_controller;

    private DcMotor dc_drive_left;
    private Servo servo1;
    private DcMotor dc_drive_right;
    private DcMotor dc_4link;
    private TwoMotorDrive rightWheel;
    private TwoMotorDrive leftWheel;
    private int fourLinkUpper = 1920;
    private int fourLinkLower = 0;
    private DcMotor dc_drive_right2;
    private DcMotor dc_drive_left2;
    @Override
    public void init() {
        dc_drive_controller = hardwareMap.dcMotorController.get("drive_controller");
        servo1 = hardwareMap.servo.get("servo1");
        dc_drive_left = hardwareMap.dcMotor.get("left_drive");
        dc_4link = hardwareMap.dcMotor.get("4link");
        dc_drive_right = hardwareMap.dcMotor.get("right_drive");
        dc_drive_right2 = hardwareMap.dcMotor.get("right_drive2");
        dc_drive_left2 = hardwareMap.dcMotor.get("left_drive2");
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
        if (gamepad2.a && servo1.getPosition()!=1.0) {
            servo1.setPosition(1.0);

        }
        if (gamepad2.a && servo1.getPosition()!=0.0) {
            servo1.setPosition(0.0);

        }
        if ( (dc_4link.getCurrentPosition()<=fourLinkLower && gamepad2.right_stick_y > 0.0 ) ||
        (dc_4link.getCurrentPosition()>=fourLinkUpper && gamepad2.right_stick_y < 0.0) ||
                (dc_4link.getCurrentPosition()< fourLinkUpper && dc_4link.getCurrentPosition()>fourLinkLower)){
            dc_4link.setPower(gamepad2.right_stick_y);

        }
        leftWheel.setPower(gamepad1.left_stick_y);
        rightWheel.setPower(gamepad1.right_stick_y);
    }
}
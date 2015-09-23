package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by techclub on 9/12/15.
 */
public class operatormode2 extends OpMode {
   private DcMotorController dc_drive_controller;

    private DcMotor dc_drive_left;

    private DcMotor dc_drive_right;

    @Override
    public void init() {
        dc_drive_controller = hardwareMap.dcMotorController.get("drive_controller");

        dc_drive_left = hardwareMap.dcMotor.get("left_drive");

        dc_drive_right = hardwareMap.dcMotor.get("right_drive");

        dc_drive_right.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        dc_drive_left.setPower(gamepad1.left_stick_y);

        dc_drive_right.setPower(gamepad1.right_stick_y);
    }
}

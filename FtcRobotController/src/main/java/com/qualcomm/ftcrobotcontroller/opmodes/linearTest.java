package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by techclub on 10/23/15.
 */
public class linearTest extends OpMode {
    double ticksPerRevolation = 1440;
    boolean reachedTicks = false;
    private DcMotorController dc_drive_controller;

    private DcMotor dc_drive_right;
    public void init(){
        dc_drive_controller = hardwareMap.dcMotorController.get("drive_controller");

        dc_drive_right = hardwareMap.dcMotor.get("right_drive");

        dc_drive_right.setChannelMode
                ( DcMotorController.RunMode.RUN_USING_ENCODERS
                );
    }
    @Override
    public void loop() {
        while (!reachedTicks) {
            dc_drive_right.setPower(0.25);
            if (Math.abs(dc_drive_right.getCurrentPosition())>= 1440){
                reachedTicks= true;
            }

        }

    }
}
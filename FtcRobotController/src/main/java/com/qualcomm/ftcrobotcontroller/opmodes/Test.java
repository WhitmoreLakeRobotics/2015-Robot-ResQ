package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by techclub on 11/12/15.
 */
public class Test extends OpMode {
    private DcMotor left1, left2, right1, right2;
    private DcMotorController left, right;
    private static int go =14400;
    private DcMotorController.DeviceMode devMode;
    private int numOpLoops = 1;
    private int left_pos;
    private int right_pos;

    @Override
    public void init () {
        left = hardwareMap.dcMotorController.get("left");
        right = hardwareMap.dcMotorController.get("right");

        left1 = hardwareMap.dcMotor.get("left_drive");
        left2 = hardwareMap.dcMotor.get("left_drive2");

        right2 = hardwareMap.dcMotor.get("right_drive2");
        right1 = hardwareMap.dcMotor.get("right_drive");

        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);

    }

    public void init_loop() {

        devMode = DcMotorController.DeviceMode.WRITE_ONLY;

        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);

        left1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        left2.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        right1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        right2.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    @Override
    public void loop() {

        if (allowedToWrite()) {
        // write the values to the motors
            left1.setPower(gamepad1.left_stick_y);
            left2.setPower(gamepad1.left_stick_y);

            right1.setPower(gamepad1.right_stick_y);
            right2.setPower(gamepad1.right_stick_y);

        }

        if (numOpLoops % 17 == 0){
            left.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            right.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        }

        // Every 17 loops, switch to read mode so we can read data from the NXT device.
        // Only necessary on NXT devices.
        if (left.getMotorControllerDeviceMode() == DcMotorController.DeviceMode.READ_ONLY) {

            // Update the reads after some loops, when the command has successfully propagated through.
            telemetry.addData("Text", "free flow text");
            telemetry.addData("left motor", left1.getPower());
            telemetry.addData("RunMode: ", left1.getChannelMode().toString());
            left_pos = left1.getCurrentPosition();
            // Only needed on Nxt devices, but not on USB devices
            left.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        }
        if (right.getMotorControllerDeviceMode() == DcMotorController.DeviceMode.READ_ONLY) {

            // Update the reads after some loops, when the command has successfully propagated through.
            telemetry.addData("right motor", right1.getPower());

            right_pos = right1.getCurrentPosition();

            telemetry.addData("encoder ticks right",right1.getCurrentPosition());


            telemetry.addData("encoder ticks left",left1.getCurrentPosition());

            // Only needed on Nxt devices, but not on USB devices
            right.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

            // Reset the loop
            numOpLoops = 0;
        }

        // Update the current devMode
        devMode = left.getMotorControllerDeviceMode();
        numOpLoops++;
    }

    // If the device is in either of these two modes, the op mode is allowed to write to the HW.
    private boolean allowedToWrite(){
        return (devMode == DcMotorController.DeviceMode.WRITE_ONLY);
    }
}

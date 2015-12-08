package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by hfans on 12/2/15.
 */
public class Test2 extends OpMode {

    private DcMotor left1, left2, right1, right2;
    private DcMotorController left, right;

    private static int go =14400;
    private DcMotorController.DeviceMode devMode;
    private int numOpLoops = 1;

    private TwoMotorDriveUnstable leftW, rightW;

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

        leftW = new TwoMotorDriveUnstable(left1,left2);
        rightW = new TwoMotorDriveUnstable(right1,right2);

    }

    @Override
    public void loop () {
        leftW.callme_atStartOfLoop();
        rightW.callme_atStartOfLoop();
        rightW.setPower(gamepad1.right_stick_y);
        leftW.setPower(gamepad1.left_stick_y);
        telemetry.addData("power left", leftW.getPower());
        telemetry.addData("encoder left", leftW.getCurrentPosition());
    }
}

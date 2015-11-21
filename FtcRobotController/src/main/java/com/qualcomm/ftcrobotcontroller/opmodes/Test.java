package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by techclub on 11/12/15.
 */
public class Test extends OpMode {
    private DcMotor left1, left2, right1, right2;
    private TwoMotorDrive left, right;
    private static int go =14400;

    @Override
    public void init () {
        left1 = hardwareMap.dcMotor.get("left_drive");
        left2 = hardwareMap.dcMotor.get("left_drive2");
        right2 = hardwareMap.dcMotor.get("right_drive2");
        right1 = hardwareMap.dcMotor.get("right_drive");
        right = new TwoMotorDrive(right1,right2);
        left = new TwoMotorDrive(left1,left2);
        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop () {

        left.areWeThereYet(go);
        right.areWeThereYet(go);

    }
}

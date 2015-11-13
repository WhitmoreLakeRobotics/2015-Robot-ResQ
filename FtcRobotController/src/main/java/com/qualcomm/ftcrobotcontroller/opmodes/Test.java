package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by techclub on 11/12/15.
 */
public class Test extends OpMode {
    private DcMotor left1, left2;
    private TwoMotorDrive left;
    private static int go =1440;

    @Override
    public void init () {
        left1 = hardwareMap.dcMotor.get("left_drive");
        left2 = hardwareMap.dcMotor.get("left_drive2");
        left = new TwoMotorDrive(left1,left2);

    }

    public void loop () {
        left.test(go);

    }
}

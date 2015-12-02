package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by barthak on 12/2/2015.
 */
public class TwoMotorDriveUnstable extends TwoMotorDrive {

    private int loop_counter = 0;

    public TwoMotorDriveUnstable(DcMotor motor1, DcMotor motor2) {
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.motorController = motor1.getController();
        this.setWriteMode();
    }
}

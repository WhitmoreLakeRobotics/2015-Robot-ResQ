package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;

/**
 * Created by techclub on 11/9/15.
 */
public class AutoOpmode extends OpMode {

    private DcMotor left2, left1, right1, right2;
    private LightSensor sensorOfLight;
    private TwoMotorDrive left, right;
    private boolean haveline=false;
    private double lightThreshold= .255;

    @Override
    public void init(){
        left2 = hardwareMap.dcMotor.get("left_drive2");
        left1 = hardwareMap.dcMotor.get("left_drive1");
        right2 = hardwareMap.dcMotor.get("right_drive2");
        right1 = hardwareMap.dcMotor.get("right_drive1");

        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);


        left1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        right1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        left = new TwoMotorDrive(left1, left2);
        right = new TwoMotorDrive(right1, right2);
        sensorOfLight = hardwareMap.lightSensor.get("LEDlightLED");

    }

    @Override
    public void loop(){

        if (!haveline){
            left.setPower(0.5);
            right.setPower(0.5); //goes untill line has be reatched
            if (sensorOfLight.getLightDetected()>lightThreshold ){
                haveline = true;
            }
        }else {

            left.setPower(0.0);
            right.setPower(0.0);

            left1.getCurrentPosition();
        }
        //want to turn

        //want to go till end

    }
}

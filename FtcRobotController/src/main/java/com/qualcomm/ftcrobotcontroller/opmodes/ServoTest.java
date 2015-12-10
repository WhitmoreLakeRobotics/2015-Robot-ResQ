package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by techclub on 12/9/15.
 */
public class ServoTest extends OpMode {

    private Servo the_servo;
    private double set_servo_value = 0.5;
    private double stepsize = 0.002;

    @Override
    public void init (){
        the_servo = hardwareMap.servo.get("servo4");

    }

    @Override
    public void loop (){
        if (gamepad1.x){
            set_servo_value += stepsize;
        }
        if (gamepad1.y){
            set_servo_value -= stepsize;
        }

        if (set_servo_value>1.0){
            set_servo_value = 1.0;
        }
        if (set_servo_value<0.0){
            set_servo_value = 0.0;
        }

        telemetry.addData("position-of-the-arm:",set_servo_value);
        the_servo.setPosition(set_servo_value);
    }
}

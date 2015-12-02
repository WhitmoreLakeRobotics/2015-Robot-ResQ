package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by barthak on 12/2/2015.
 */
public class TwoMotorDriveUnstable extends TwoMotorDrive {

    private DcMotorController.DeviceMode devMode;

    private int loop_counter = 0;
    private int encoder =0;
    private double power = 0.0;

    private boolean read_mode;
    private int CurrentPosition;

    private boolean allowedToWrite (){
        return (devMode == DcMotorController.DeviceMode.WRITE_ONLY);
    }

    public void callme_atStartOfLoop (){
        if (loop_counter % 0 == 0){
            motorController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        }
        // update stored values??

        //bump counter
        loop_counter ++;
    }
    @Override
    public double getPower () {
        return this.power;
    }

    @Override
    public int getCurrentPosition(){
        return encoder;
    }

    public TwoMotorDriveUnstable(DcMotor motor1, DcMotor motor2) {
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.motorController = motor1.getController();
        this.loop_counter = 0;
    }

    public TwoMotorDriveUnstable(TwoMotorDrive old){
        this.motor1 = old.motor1;
        this.motor2 = old.motor2;
        this.motorController = old.motorController;
        this.loop_counter = 0;
    }

    public boolean areWeThereYet (int goal_ticks) {

        if (read_mode) {
            //moving
            CurrentPosition = this.getCurrentPosition();
            if (closeEnough(CurrentPosition, goal_ticks)){
                //3
                this.setWriteMode();
                return true;
            }else{
                //2
                return false;
            }

        }else {
            //stop
            if (closeEnough(CurrentPosition, goal_ticks)){
                //4
                this.motor1.setPower(0.0);
                this.motor2.setPower(0.0);
                return true;
            }else{
                //1
                if (CurrentPosition > goal_ticks){
                    this.motor1.setPower(-1.0);
                    this.motor2.setPower(-1.0);
                }else {
                    this.motor1.setPower(1.0);
                    this.motor2.setPower(1.0);
                }
                this.setReadMode();
                return false;
            }
        }
    }

}

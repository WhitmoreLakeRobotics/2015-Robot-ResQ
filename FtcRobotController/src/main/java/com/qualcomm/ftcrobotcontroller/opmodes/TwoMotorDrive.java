package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by techclub on 10/28/15.
 */
public class TwoMotorDrive {
    protected DcMotor motor1, motor2;
    private DcMotorController motorController;
    private int CurrentPosition = 0;
    private boolean read_mode, moveing;

    public TwoMotorDrive(DcMotor motor1, DcMotor motor2) {
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.setWriteMode();
    }

    public String getDeviceName() {
        return "DC Double Motor";
    }

    public String getConnectionInfo() {
        return "Motor 1:" + this.motor1.getConnectionInfo() + "\nMotor 2:" + this.motor2.getConnectionInfo();
    }

    public int getVersion() {
        return 1;
    }

    public void close() {
        this.motor1.setPowerFloat();
        this.motor2.setPowerFloat();
    }

    //too complex, just returned motor1's controller
    public DcMotorController getController() {
        return this.motor1.getController();
    }

    public void setDirection(DcMotor.Direction direction) {
        this.motor1.setDirection(direction);
        this.motor2.setDirection(direction);
    }

    //directions should be the same
    public DcMotor.Direction getDirection() {
        return this.motor1.getDirection();
    }

    public int getPortNumber() {
        return this.motor1.getPortNumber();
    }

    public void setPower(double power) {
        this.motor1.setPower(power);
        this.motor2.setPower(power);
    }

    public double getPower() {
        return this.motor1.getPower();
    }

    public boolean isBusy() {
        return this.motor1.isBusy() ^ this.motor2.isBusy();
    }

    public void setPowerFloat() {
        this.motor1.setPowerFloat();
        this.motor2.setPowerFloat();
    }

    public boolean getPowerFloat() {
        return this.motor1.getPowerFloat();
    }

    public void setTargetPosition(int position) {
        this.motor1.setTargetPosition(position);
    }

    public int getTargetPosition() {
        return this.motor1.getTargetPosition();
    }

    public int getCurrentPosition() {
        return  this.CurrentPosition;
    }

    public void setChannelMode(DcMotorController.RunMode mode) {
        this.motor1.setChannelMode(mode);
        this.motor2.setChannelMode(mode);
    }

    public DcMotorController.RunMode getChannelMode() {
        return this.motor1.getChannelMode();
    }

    public void setReadMode () {
        this.motorController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        read_mode = true;
    }

    public void setWriteMode(){
        this.motorController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        read_mode = false;
    }
    private boolean closeEnough (int a, int b ) {
        int range = 1440;
        if ( Math.abs( a - b) >= range){
            return false;
        }else {
            return true;
        }
    }

    public boolean getReadMode () {return read_mode;}

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



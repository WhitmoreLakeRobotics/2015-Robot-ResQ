package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.ftcrobotcontroller.opmodes.encoderDistance;
import com.qualcomm.ftcrobotcontroller.opmodes.MathHelper.*;
/**
 * Created by techclub on 11/9/15.
 */
public class AutoOpmode2 extends OpMode {

    private int  PRE_MOVE = 0;
    private int  LEG_1_MOVE = 1;
    private int  TURN_1_MOVE = 2;
    private int  LEG_2_MOVE = 3;
    private int  TRIP_STOP = 4;
    private double right_pos = 0;
    private double left_pos = 0;
    private int legticks = 0;  // ticks of travel for this lef of the trip

    private boolean isPowerSet = false;
    private boolean posAchieved =false;
    
    private DcMotor leftMotor2, leftMotor1, rightMotor1, rightMotor2;
    
    private TwoMotorDrive leftW, rightW;

    private encoderDistance encoder_distance = new encoderDistance();
    private MathHelper mathHelper = new MathHelper();
    private DcMotorController leftControler, rightControler;

    private int tripState = 0;
    
    @Override
    public void init(){
        leftMotor2 = hardwareMap.dcMotor.get("left_drive2");
        leftMotor1 = hardwareMap.dcMotor.get("left_drive");
        rightMotor2 = hardwareMap.dcMotor.get("right_drive2");
        rightMotor1 = hardwareMap.dcMotor.get("right_drive");
        rightControler = hardwareMap.dcMotorController.get("right");
        leftControler = hardwareMap.dcMotorController.get("left");
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);

        leftMotor1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        leftW = new TwoMotorDrive(leftMotor1, leftMotor2);
        rightW = new TwoMotorDrive(rightMotor1, rightMotor2);

    }



    @Override
    public void loop(){

    
    
      //steps to be completed prior to our attempt to move
      if (tripState == PRE_MOVE) {

	       nextLeg();  //advance trip to the next level
    
      }
    
    
      //We have started moving
      if (tripState == LEG_1_MOVE) {
         
          if (!isPowerSet ) {
            //leg 1 is 48 inches long
            legticks=MathHelper.inches2ticks(48.0);
            leftW.setPower(1.0);
            rightW.setPower(1.0);
            isPowerSet = true;
          }
         
          posAchieved = rightW.areWeThereYet(legticks) && 
            leftW.areWeThereYet(legticks);

	       if (posAchieved ) {
          nextLeg();
        }
      }
      
      
      if (tripState == TURN_1_MOVE) {
        //setting one forward and one to reverse will cause us to turn
        if (!isPowerSet ) {
            // here we want each wheel to move 6 inches
            legticks=MathHelper.inches2ticks(6.0);
            leftW.setPower(-1.0);
            rightW.setPower(1.0);
            isPowerSet = true;
        }

        posAchieved = rightW.areWeThereYet(legticks) && 
            leftW.areWeThereYet(legticks);

	     if (posAchieved ) { 
        nextLeg();
        }
      }
      

      if (tripState == LEG_2_MOVE) {
        
        //setting one forward and one to reverse will cause us to turn
        if (!isPowerSet ) {
            legticks = MathHelper.inches2ticks(36);
            leftW.setPower(1.0);
            rightW.setPower(1.0);
            isPowerSet = true;
        }

        posAchieved = rightW.areWeThereYet(legticks) && 
            leftW.areWeThereYet(legticks);

       if (posAchieved ) {
          nextLeg();
        }
      }


      // 
      if (tripState == TRIP_STOP) {
        //motors Stop all motors
        if (!isPowerSet ) {	
          isPowerSet = true;
          leftW.setPowerFloat();
          rightW.setPowerFloat();
          }
      }
 
    }

    private void nextLeg () {
       tripState++;  //advance trip to the next level
       isPowerSet = false;
       leftW.setPowerFloat();
       rightW.setPowerFloat();
       //reset encoders so we can start each leg from 0.
       leftW.setChannelMode(DcMotorController.RunMode. RESET_ENCODERS);
       rightW.setChannelMode(DcMotorController.RunMode. RESET_ENCODERS);
       leftW.setReadMode();
       rightW.setReadMode();       
    }

}


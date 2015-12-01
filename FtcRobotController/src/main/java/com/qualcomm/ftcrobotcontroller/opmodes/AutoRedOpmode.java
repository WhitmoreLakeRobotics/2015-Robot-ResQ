package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.ftcrobotcontroller.opmodes.encoderDistance;

/**
 * Created by techclub on 11/9/15.
 */
public class AutoOpmode2 extends OpMode {

    private int final PRE_MOVE = 0;
    private int final LEG_1_MOVE = 1;
    private int final TURN_1_MOVE = 2;
    private int final LEG_2_MOVE = 3;
    private int final TRIP_STOP = 4;
    
    private bool isPowerSet = false;
    
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

        left1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        right1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        leftW = new TwoMotorDrive(left1, left2);
        rightW = new TwoMotorDrive(right1, right2);

    }

    @Override
    public void loop(){

    
    
      //steps to be completed prior to our attempt to move
      if (tripState == PREMOVE) {

	tripState++;  //advance trip to the next level
    
      }
    
    
      //We have started moving
      if (tripState == LEG_1_MOVE) {
      
        if (powerIsSet ) {
	  leftMotor.setPower(1.0);
	  rightMotor.setPower(1.0);
	  powerIsSet = true;
        }
	

	if (posAchieved () ) {
	  tripState++;  //advance trip to the next level
	  powerIsSet = false;
	  leftMotor.setPowerFloat();
	  rightMotor.setPowerFloat();
	}
      
      }
      
      
      if (tripState == TURN_1_MOVE) {
      
	tripState++;  //advance trip to the next level
      
      }
      

      if (tripState == LEG_2_MOVE) {
      
	tripState++;  //advance trip to the next level
      
      }


      // 
      if (tripState == TRIP_STOP) {
      //motors Stop all motors
	
      
      }

    
    }
}


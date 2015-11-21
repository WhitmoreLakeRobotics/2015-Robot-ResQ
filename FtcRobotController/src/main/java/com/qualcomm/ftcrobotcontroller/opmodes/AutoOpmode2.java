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

    private DcMotor left2, left1, right1, right2;
    //private LightSensor sensorOfLight;
    private TwoMotorDrive leftW, rightW;

    private boolean going_forward = true, need_to_turn = true;
    private static boolean turn_left = true;
    private static double distance_to_go = 72;
    private static double degrees_to_turn = 90;
    private boolean fwd1 = false;
    private encoderDistance encoder_distance = new encoderDistance();
    private MafHelper mafHelper = new MafHelper();
    private DcMotorController left, right;

    private int turn_dist, turn_goal_left, turn_goal_right;
    private double turn_dist_d = mafHelper.degreesToDistance(degrees_to_turn);

    private int dist_goal_tick = (int) encoder_distance.inchesToTicks(distance_to_go);



    @Override
    public void init(){
        left2 = hardwareMap.dcMotor.get("left_drive2");
        left1 = hardwareMap.dcMotor.get("left_drive");
        right2 = hardwareMap.dcMotor.get("right_drive2");
        right1 = hardwareMap.dcMotor.get("right_drive");
        right = hardwareMap.dcMotorController.get("right");
        left = hardwareMap.dcMotorController.get("left");
        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);

        left1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        right1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        leftW = new TwoMotorDrive(left1, left2);
        rightW = new TwoMotorDrive(right1, right2);

    }

    @Override
    public void loop(){
        if (going_forward){

          if ( leftW.areWeThereYet(dist_goal_tick) &&
            rightW.areWeThereYet(dist_goal_tick))
              fwd1 = true;

              if (fwd1){
                //we are done going forward
                going_forward = false;
                turn_dist = (int) encoder_distance.inchesToTicks(turn_dist_d);
                if (turn_left){
                    turn_goal_left = dist_goal_tick - turn_dist;
                    turn_goal_right = dist_goal_tick + turn_dist;
                }else{
                    turn_goal_left = dist_goal_tick + turn_dist;
                    turn_goal_right = dist_goal_tick - turn_dist;
                }
            }

        }else {
            if (need_to_turn){
                // turn to goal
                //goToValue(turn_goal_left,leftW);

                //goToValue(turn_goal_right,rightW);


                if (leftW.areWeThereYet(turn_goal_left) && rightW.areWeThereYet(turn_goal_right)){
                    //we are done turning
                    need_to_turn = false;
                }

            }else {
                //(insert code here)

            }
        }

    }
}


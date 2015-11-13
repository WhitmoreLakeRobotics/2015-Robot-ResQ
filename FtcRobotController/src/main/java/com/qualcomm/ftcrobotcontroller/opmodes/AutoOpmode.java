package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.ftcrobotcontroller.opmodes.encoderDistance;

/**
 * Created by techclub on 11/9/15.
 */
public class AutoOpmode extends OpMode {

    private DcMotor left2, left1, right1, right2;
    private LightSensor sensorOfLight;
    private TwoMotorDrive leftW, rightW;
    private boolean haveline=false, doneTurn = false;
    private double lightThreshold= .255;
    private int dist_to_line_right, dist_to_line_left;
    private double dist_to_line, angle_goal, distance_goal;
    private int distance_goal_ticks_right, distance_goal_ticks_left;
    private encoderDistance encoder_distance;
    private MafHelper mafHelper;
    private DcMotorController left, right;
    private int turn_dist, turn_goal_left, turn_goal_right;
    private boolean turn_left = true;
    private double turn_dist_d;



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
        sensorOfLight = hardwareMap.lightSensor.get("LEDlightLED");

    }

    @Override
    public void loop(){

        if (!haveline){
            leftW.setPower(0.5);
            rightW.setPower(0.5); //goes untill line has be reatched
            if (sensorOfLight.getLightDetected()>lightThreshold ){
                haveline = true;
                dist_to_line_left = left1.getCurrentPosition();
                dist_to_line_right = right1.getCurrentPosition();
                dist_to_line = dist_to_line_left + dist_to_line_right / 2.0;
                dist_to_line = encoder_distance.ticksToInches(dist_to_line);

                angle_goal = mafHelper.getAngleToEnd(dist_to_line);
                distance_goal = mafHelper.getDistanceToEnd(dist_to_line);

                turn_dist_d = mafHelper.degreesToDistance(angle_goal);
                turn_dist_d = encoder_distance.inchesToTicks(turn_dist_d);
                turn_dist = (int) turn_dist_d;
                if (turn_left){
                    turn_goal_left = dist_to_line_left - turn_dist;
                    turn_goal_right = dist_to_line_right + turn_dist;
                }else {
                    turn_goal_left = dist_to_line_left + turn_dist;
                    turn_goal_right = dist_to_line_right - turn_dist;
                }
            }
        }else {
            //want to turn

            if (!doneTurn) {
                goToValue(turn_goal_left, leftW);
                goToValue(turn_goal_right, rightW);
                if (leftW.getPower() == 0 && rightW.getPower() ==0 ){
                    doneTurn = true;
                    distance_goal_ticks_right=right1.getCurrentPosition() + (int)(encoder_distance.inchesToTicks(distance_goal));
                    distance_goal_ticks_left=left1.getCurrentPosition() + (int)(encoder_distance.inchesToTicks(distance_goal));}
            }else{
                goToValue(distance_goal_ticks_left,leftW);
                goToValue(dist_to_line_right,rightW);

            }
        }
    }

    private void goToValue(int tick, TwoMotorDrive motor) {
        //give a motor and a goal tick value
        //goes to tick value
        int current_dist = motor.getCurrentPosition();
        int slowdown_breakpoint = 1440;
        int stop_breakpoint = 144;
        double fullSpeed = 1;
        double slowSpeed = .25;
        if ( Math.abs(tick - current_dist) > slowdown_breakpoint ) {
            //if we are more than a full rotation from the goal
            //go full speed
            if (tick > current_dist){
                motor.setPower(fullSpeed);
            }else {
                motor.setPower(-fullSpeed);
            }
        }else {
            //if we are more than a 10th of a rotiaion from goal
            if (Math.abs(tick - current_dist) > stop_breakpoint){
                if (tick > current_dist){
                    motor.setPower(slowSpeed);
                }else {
                    motor.setPower(-slowSpeed);
                }
            }else{
                motor.setPower(0.0);
            }

        }
    }
}

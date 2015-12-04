package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by techclub on 11/30/15.
 */
public class AutoTest extends OpMode {

    private DcMotor left2, left1, right1, right2;
    //private LightSensor sensorOfLight;
    private TwoMotorDrive leftW, rightW;
    public HardwareMap hardwareMap = new HardwareMap();
    private boolean going_forward = true, need_to_turn = true;
    private static boolean turn_left = true;
    private static double distance_to_go = 72;
    private static double degrees_to_turn = 90;
    private boolean fwd1 = false;
    private encoderDistance encoder_distance = new encoderDistance();
    private MafHelper mafHelper = new MafHelper();
    private DcMotorController left, right;
    private int state = 0;
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

        if (state == 1){


        }



    }
}


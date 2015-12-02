package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by techclub on 11/7/15.
 */
public class MafHelper {
    private double end_goal_x = 42, end_goal_y = 31.4149;
    private double width_of_robutt = 15.75;

    public static final double  wheelDia = 4.0;
    public static final int ticksPerRev = 1440;


    public double getDistance(double x1, double y1, double x2, double y2 ){
        double out =0;
        out = Math.sqrt( Math.pow( x1 -x2 , 2) + Math.pow(y1 -y2 ,2) );
        return out;
    }

    public double getDistanceToEnd (double Z){
        double out, tmp_x, tmp_y;
        tmp_x = -1 * (72 -Z);
        tmp_y = 72 -Z;
        out = getDistance(end_goal_x, end_goal_y, tmp_x, tmp_y);
        return out;
    }
    public double getAngleToEnd (double Z){
        double out, tmp_x, tmp_y, w_x, w_y, o_dist, a_dist;
        tmp_x = -1 * (72 -Z);
        tmp_y = 72 -Z;
        w_x = (end_goal_y -end_goal_x) / (-2.0);
        w_y = (end_goal_y -end_goal_x) / (2.0);
        o_dist = getDistance(w_x,w_y, end_goal_x, end_goal_y);
        a_dist = getDistance(tmp_x, tmp_y, w_x, w_y);
        out = Math.atan(o_dist/a_dist);
        out = Math.toDegrees(out);
        out += 45;
        return out;
    }

    public double degreesToDistance ( double degrees) {
        double out;

        out = (width_of_robutt * Math.PI)/(degrees);
        return  out;
    }


    public double  inches2ticks (double inches){

    double retTicks = 0;

    retTicks =  (Math.PI * wheelDia) / (ticksPerRev);
    return (retTicks);

}

}
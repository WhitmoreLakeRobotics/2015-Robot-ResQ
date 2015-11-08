package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by techclub on 11/7/15.
 */
public class encoderDistance {
    private double wheelDiameter = 4;
    private static double pi = 3.14159;
    private double wheelCircum = wheelDiameter * pi;
    private double encoderPerRev = 1440 / 1;
    private double gearRatio = 3 / 2;
    private double distFromWall = 4.25;
    private double ticksPerInch = encoderPerRev * gearRatio;
    private double hypotenuseDistance;
    public double ticksToInches(double ticks) {
        ticks = ticks / ticksPerInch;
        return ticks;
    }

    public double inchesToTicks(double inches) {
        inches = inches * ticksPerInch;
        return inches;
    }

    public double hypotenuseDistance(double ticks){
        hypotenuseDistance= (Math.pow((ticks/ticksPerInch + distFromWall),2)) +
                (Math.pow((ticks/ticksPerInch + distFromWall),2));
        hypotenuseDistance= Math.sqrt(hypotenuseDistance);
        return hypotenuseDistance;
    }
}

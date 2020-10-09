package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.HashMap;
import java.util.Map;

public abstract class wrapper extends LinearOpMode {

    Map<DcMotor, Integer> posMap = new HashMap<>();

    @Override
    public void runOpMode() {
        onInit();
        waitForStart();
        while (opModeIsActive()) {
            running();
            telemetry.update();
        }
        onStop();
    }

    <T> T use(Class<? extends T> deviceType, String name) {
        return hardwareMap.get(deviceType, name);
    }

    ElapsedTime createTimer() {
        return new ElapsedTime(0);
    }

    void log(String caption, Object content) {
        telemetry.addData(caption, content);
    }

    void setMotorForward(DcMotor motor) {
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    void setMotorReversed(DcMotor motor) {
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void beginRecordMotorOffset(DcMotor motor) {
        posMap.put(motor, motor.getCurrentPosition());
    }

    int getMotorOffset(DcMotor motor) {
        int result;
        try {
            result = motor.getCurrentPosition() - posMap.get(motor);
        } catch (NullPointerException ex) {
            result = 0;
        }
        return result;
    }

    void waitForTargetOffset(DcMotor motor, int targetOffset) {
        while (Math.abs(motor.getCurrentPosition() - posMap.get(motor)) < targetOffset){}
    }

    public abstract void onInit();
    public abstract void running();
    public abstract void onStop();
}

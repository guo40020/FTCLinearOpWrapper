package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp(name = "wrapperExample")
public class wrapperExample extends wrapper {

    @Override
    public void onInit() {
        DcMotor motor1 = use(DcMotor.class, "myMotor");
        setMotorReversed(motor1);
        ElapsedTime timer = createTimer();
    }

    @Override
    public void running() {
        DcMotor motor1 = use(DcMotor.class, "myMotor");
        beginRecordMotorOffset(motor1);
        use(DcMotor.class, "myMotor").setPower(1);
        log("offset", getMotorOffset(motor1));
        waitForTargetOffset(motor1, 1000);
    }

    @Override
    public void onStop() {
        log("status", "Yay!");
    }
}

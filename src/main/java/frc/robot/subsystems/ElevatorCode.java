// TalonSRX motor
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class ElevatorCode extends SubsystemBase {
    private final WPI_TalonSRX elevatorMotor;
    private final Joystick joystick;

    // Motor ve joystick portları
    private static final int MOTOR_CAN_ID = 5;
    private static final int JOYSTICK_PORT = 0;

    // Manuel kontrol butonları
    private static final int UP_BUTTON = 1;
    private static final int DOWN_BUTTON = 2;

    // Preset butonları
    private static final int PRESET_1_BUTTON = 3;
    private static final int PRESET_2_BUTTON = 4;
    private static final int PRESET_3_BUTTON = 5;
    private static final int PRESET_4_BUTTON = 6;

    // Limit switch portları
    private static final int LIMIT_SWITCH_1_PORT = 0;
    private static final int LIMIT_SWITCH_2_PORT = 1;
    private static final int LIMIT_SWITCH_3_PORT = 2;
    private static final int LIMIT_SWITCH_4_PORT = 3;

    private final DigitalInput limitSwitch1;
    private final DigitalInput limitSwitch2;
    private final DigitalInput limitSwitch3;
    private final DigitalInput limitSwitch4;

    private int targetPreset = 0;

    public ElevatorCode() {
        elevatorMotor = new WPI_TalonSRX(MOTOR_CAN_ID);
        joystick = new Joystick(JOYSTICK_PORT);

        limitSwitch1 = new DigitalInput(LIMIT_SWITCH_1_PORT);
        limitSwitch2 = new DigitalInput(LIMIT_SWITCH_2_PORT);
        limitSwitch3 = new DigitalInput(LIMIT_SWITCH_3_PORT);
        limitSwitch4 = new DigitalInput(LIMIT_SWITCH_4_PORT);

        // Motor ayarları
        elevatorMotor.setNeutralMode(NeutralMode.Brake); // Motor freni aktif
        elevatorMotor.configFactoryDefault();
        elevatorMotor.setInverted(false); // Gerekirse ters yönde çalıştır
    }

    @Override
    public void periodic() {
        if (targetPreset == 0) {
            if (joystick.getRawButtonPressed(PRESET_1_BUTTON)) {
                targetPreset = 1;
            } else if (joystick.getRawButtonPressed(PRESET_2_BUTTON)) {
                targetPreset = 2;
            } else if (joystick.getRawButtonPressed(PRESET_3_BUTTON)) {
                targetPreset = 3;
            } else if (joystick.getRawButtonPressed(PRESET_4_BUTTON)) {
                targetPreset = 4;
            }
        }

        if (targetPreset != 0) {
            switch (targetPreset) {
                case 1:
                    if (!limitSwitch1.get()) {
                        elevatorMotor.set(-0.8);
                    } else {
                        elevatorMotor.set(0.0);
                        targetPreset = 0;
                    }
                    break;
                case 2:
                    if (!limitSwitch2.get()) {
                        elevatorMotor.set(0.8);
                    } else {
                        elevatorMotor.set(0.0);
                        targetPreset = 0;
                    }
                    break;
                case 3:
                    if (!limitSwitch3.get()) {
                        elevatorMotor.set(0.8);
                    } else {
                        elevatorMotor.set(0.0);
                        targetPreset = 0;
                    }
                    break;
                case 4:
                    if (!limitSwitch4.get()) {
                        elevatorMotor.set(0.8);
                    } else {
                        elevatorMotor.set(0.0);
                        targetPreset = 0;
                    }
                    break;
            }
        } else {
            if (joystick.getRawButton(UP_BUTTON)) {
                elevatorMotor.set(0.8);
            } else if (joystick.getRawButton(DOWN_BUTTON)) {
                elevatorMotor.set(-0.8);
            } else {
                elevatorMotor.set(0.0);
            }
        }
    }
}

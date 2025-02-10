package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX; // VictorSPX için
import edu.wpi.first.wpilibj.motorcontrol.Spark; // Spark MAX için
import edu.wpi.first.wpilibj.Joystick;

public class ElevatorCode extends SubsystemBase {
    private final Spark elevatorMotor; // Eğer Talon SRX kullanıyorsan WPI_TalonSRX ile değiştir

    private final Joystick joystick;
    
    private static final int MOTOR_PORT = 5; // Motorun bağlı olduğu port
    private static final int JOYSTICK_PORT = 0; // Joystick portu
    private static final int UP_BUTTON = 1;  // Joystick üzerindeki yukarı butonu (örneğin A butonu)
    private static final int DOWN_BUTTON = 2; // Joystick üzerindeki aşağı butonu (örneğin B butonu)

    public ElevatorCode() {
        elevatorMotor = new Spark(MOTOR_PORT); // Eğer Talon SRX kullanıyorsan TalonSRX nesnesi oluştur
        joystick = new Joystick(JOYSTICK_PORT);
    }

    @Override
    public void periodic() {
        // Butonları kontrol et
        if (joystick.getRawButton(UP_BUTTON)) {
            elevatorMotor.set(0.8); // Asansörü yukarı kaldır
        } else if (joystick.getRawButton(DOWN_BUTTON)) {
            elevatorMotor.set(-0.8); // Asansörü aşağı indir
        } else {
            elevatorMotor.set(0.0); // Buton basılmıyorsa motoru durdur
        }
    }
}

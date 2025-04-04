package magno;
import robocode.*;
import java.awt.*;
import robocode.util.Utils;

public class MagnoDaAdidas extends AdvancedRobot {
    private int moveDirection = 1;
    
    public void run() {
        setColors(Color.black, Color.white, Color.red);
        setAdjustRadarForGunTurn(true);
        setAdjustGunForRobotTurn(true);
        
        while (true) {
            setAhead(100 * moveDirection);
            setTurnRight(45);
            setTurnRadarRight(360);
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
        setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians()));
        
        double firePower = Math.min(3.0, e.getEnergy() / 4);
        if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10) {
            setFire(firePower);
        }
        
        if (e.getDistance() < 200) {
            moveDirection *= -1;
            setAhead(150 * moveDirection);
        }
        
        setTurnRadarRightRadians(Utils.normalRelativeAngle(absoluteBearing - getRadarHeadingRadians()) * 2);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        moveDirection *= -1;
        setAhead(100 * moveDirection);
        setTurnRight(e.getBearing() + 90);
    }

    public void onHitWall(HitWallEvent e) {
        moveDirection *= -1;
        setBack(100);
    }

    public void onWin(WinEvent e) {
        for (int i = 0; i < 10; i++) {
            turnRight(36);
            ahead(20);
        }
    }
}
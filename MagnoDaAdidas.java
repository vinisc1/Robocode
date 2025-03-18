import robocode.*;
import robocode.util.Utils;
import java.awt.Color;

public class MagnoDaAdidas extends AdvancedRobot {
    
    @Override
    public void run() {
        // Cores do robô preto, branco e amarelo
        setColors(Color.BLACK, Color.WHITE, Color.YELLOW);         
        // Movimentação aleatória
        while (true) {
            // Fazendo o robô girar aleatoriamente
            setTurnRadarRight(360);  // Gira o radar
            setAhead(100);           // Move para frente
            setTurnRight(45);        // Gira para a direita
            execute();               
        }
    }
    
    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        // Mira no inimigo e atira
        double bearing = event.getBearing();  // Ângulo do inimigo
        double distance = event.getDistance(); // Distância do inimigo

        // Tentar prever a próxima posição do inimigo
        double angleToEnemy = getHeading() + bearing;
        double predictedX = getX() + Math.sin(Math.toRadians(angleToEnemy)) * distance;
        double predictedY = getY() + Math.cos(Math.toRadians(angleToEnemy)) * distance;

        // Mira no inimigo
        setTurnGunRight(bearing);
        setFire(3); // Tiro com força 3
        execute();
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        // Quando o robô é atingido, muda de direção
        setTurnRight(90); // Muda a direção pra não ser atingido de novo
        setAhead(50);
        execute(); 
    }
}
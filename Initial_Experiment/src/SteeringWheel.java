import java.util.Objects;

/**
 * 方向盘
 */
public class SteeringWheel {
    SteeringWheel(){
        System.out.println("SteeringWheel Existing !");
    }
    public void Steering_Action(String action){            //转向
        if(Objects.equals(action, "right")){            //左转
            System.out.println("Turning right!");
        } else if (Objects.equals(action, "left")) {    //右转
            System.out.println("Turning left!");
        }
    }
}

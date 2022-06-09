import java.util.Objects;

/**
 * 车门
 */
public class Door {
    Door(){
        System.out.println("Door Existing !");
    }
    Part_Door left_door = new Part_Door("left");          //左门
    Part_Door right_door = new Part_Door("right");        //右门
    public void Door_Action(String action){
        if(Objects.equals(action, "Close")){                //关门
            System.out.println("Door closing !");
        } else if (Objects.equals(action, "Open")) {        //开门
            System.out.println("Door opening !");
        }
    }
    static Lock lock = new Lock();                             //锁
}

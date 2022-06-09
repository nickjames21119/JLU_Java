/**
 * 车灯
 */
public class Light {
    Light(){
        System.out.println("Light Existing !");
    }

    public void Warning(){                              //警告灯
        System.out.println("Warning Flashing !");
    }

    public void TurnRight(){                            //左转向灯
        System.out.println("RightLight Flashing !");
    }

    public void TurnLeft(){                             //右转向灯
        System.out.println("LeftLight Flashing !");
    }

    public void TurnBackLight(){                        //后退灯
        System.out.println("TurningBack Flashing !");
    }
    static Part_Light leftlight =new Part_Light("left");
    static Part_Light rightlight =new Part_Light("right");
    static Part_Light frontlight =new Part_Light("front");
    static Part_Light backlight =new Part_Light("back");
}

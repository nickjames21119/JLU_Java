/**
 * 车轮
 */
public class Wheel {
    Wheel(){
        System.out.println("Wheel Existing !");
    }
    public void WheelStart(){                    //车轮开始转动
        System.out.println("Start move !");
    }

    public void Moveforward(){                   //车轮前转
        System.out.println("Move Forward !");
    }

    public void MoveBack(){                      //车轮后转
        System.out.println("Move Back !");
    }

    public void WheelStop(){                     //车轮停止
        System.out.println("Wheel Stop !");
    }
}

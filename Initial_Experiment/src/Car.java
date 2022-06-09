public class Car {
    Car(){                                        //Car 与 Action_Car 组合关系，比较类与成员类创建关系
        System.out.println("Car Brand !");
    }
    Door door = new Door();                       //比较静态非静态成员初始化顺序
    public void Car_Start(){
        door.Door_Action("open");                 //开车门
        door.Door_Action("close");                //关车门
        engine.Seperate("start");          //引擎启动
        gear.Gear_Action('n');                    //挂挡
        wheel.WheelStart();                       //车轮转动
        wheel.Moveforward();                      //车轮向前
        light.TurnLeft();                         //左转向灯
        steeringWheel.Steering_Action("left");    //车轮左转
        light.TurnRight();                        //右转向灯
        steeringWheel.Steering_Action("right");   //车轮右转
        gear.Gear_Action('r');                    //倒挡
        light.TurnBackLight();                    //警告车灯
        wheel.MoveBack();                         //车轮后转


    }
    public void Car_Stop(){
        light.Warning();                          //警告车灯
        gear.Gear_Action('n');                    //空挡
        gear.Gear_Action('p');                    //停车
        engine.Flameout("flameout");        //熄火
        door.Door_Action("open");                 //开车门
        door.Door_Action("close");                //关车门


    }
    public static void main(String[] args) {
        Car car = new Car();
        car.Car_Start();
        car.Car_Stop();

    }

    //静态成员类
    static Engine engine = new Engine();
    static Gear gear = new Gear();
    static SteeringWheel steeringWheel = new SteeringWheel();
    static Wheel wheel = new Wheel();

    static Light light = new Light();
}
import java.util.Objects;

/**
 * 发动机类
 * 1.点火
 * 2。熄火
 */
public class Engine {
    Engine(){
        System.out.println("Engine Existing !");
    }
    public void Seperate(String action){                  //发动机点火
        if(Objects.equals(action, "start")){              //有效 action: start
            System.out.println("Engine action !");
        }else{
            System.out.println("Start Failed !");
        }
    }

    public void Flameout(String action){                    //发动机熄火
        if(Objects.equals(action, "flameout")){                             //有效 action Flameout
            System.out.println("Flameout successfully !");
        }
        else{
            System.out.println("Warning!!! Flameout failed!");
        }
    }
    static Gas gas = new Gas();
}

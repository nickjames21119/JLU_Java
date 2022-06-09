/**
 * Bird子类，继承基类Animal及接口Action
 */
public class Bird extends Animal implements Action{
    int weight;
    int length;
    int age;
    int height;
    final String identity = "Bird";

    Bird(){
        weight = 0;
        height = 0;
        age = 0;
        length = 0;
    }

    Bird(int a, int b, int c, int d){
        super(a, b, c, d);                   //子类构造函数默认先调用父类构造函数
        weight = a;
        length = b;
        age = c;
        height = d;
    }

    public void reFigure(){                 //重写Animal函数
        System.out.println("This Bird :"+"Weight: "+weight+"Height:"+ height+"Length:"+length);
    }

    public void MoveForward(){              //重写Action函数
        System.out.println("Bird fly forward !");
    }

    public void MoveBack(){                 //重写Action函数
        System.out.println("Bird fly backward !");
    }

    public void Sound(){                    //重写Action函数
        System.out.println("jiu~ jiu~ jiu~~ !");
    }

    private void Claim(){                   //私有成员函数，重写Claim
        System.out.println("I am a bird !");
    }


    public void ChangeFigure(int a, int b, int c, int d){
        System.out.println("Change weight to :"+a);
        System.out.println("Change length to :"+b);
        System.out.println("Change age to :"+c);
        System.out.println("Change height to :"+d);

        weight = a;
        length = b;
        age = c;
        height = d;
    }


}

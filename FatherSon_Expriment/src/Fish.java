public class Fish extends Animal implements Action{
    int weight;
    int length;
    int age;
    int height;
    final String identity = "Bird";

    Fish(){
        int weight = 0;
        int length = 0;
        int age = 0;
        int height = 0;
    }

    Fish(int a, int b, int c, int d){                      //子类构造函数默认先调用父类构造函数
        super(a, b, c, d);
        weight = a;
        length = b;
        age = c;
        height = d;
    }

    public void MoveForward(){                             //公有成员重写Action函数
        System.out.println("Fish swim forward !");
    }

    public void MoveBack(){                                //公有成员重写Action函数
        System.out.println("Fish swim backward !");
    }

    public void Sound(){                                   //公有成员重写Action函数
        System.out.println("~~~~~~~~~~~~~~~~~~~ !");
    }

    private void Claim(){                                   //私有成员声明身份
        System.out.println("I am a fish !");
    }
}

/**
 * Animal:基类
 * 动静态绑定体现构造顺序
 * 公有保护私有继承体现不同权限
 *程序绑定：绑定指的是一个方法的调用与方法所在的类(方法主体)关联起来。对java来说，绑定分为静态绑定和动态绑定；或者叫做前期绑定和后期绑定.
 * 静态绑定：在程序执行前方法已经被绑定（也就是说在编译过程中就已经知道这个方法到底是哪个类中的方法），此时由 编译器或其它连接程序实现。
 *         针对java简单的可以理解为程序编译期的绑定；这里特别说明一点，java当中的方法只有final，static，private和构造方法是前期绑定
 *动态绑定：后期绑定，在运行时根据具体对象的类型进行绑定。
 *        若一种语言实现了后期绑定，同时必须提供一些机制，可在运行期间判断对象的类型，并分别调用适当的方法。也就是说， 编译器此时依然不知道
 *        对象的类型，但方法调用机制能自己去调查，找到正确的方法主体。不同的语言对后期绑定的实现方法是有所区别的。但我们至少可以这样认为：
 *        它们都要在对象中安插某些特殊类型的信息。
 *动态绑定过程：
 *   1>虚拟机提取对象的实际类型的方法表；
 *   2>虚拟机搜索方法签名；
 *   3>调用方法。
 */
public class Animal{
    public int weight;
    public int height;
    public int length;
    public int age;
    final String identity = "animal";           //静态绑定类型（常量）
    Animal(){                                   //无参构造函数
        weight = 0;
        height = 0;
        length = 0;
    }
    Animal(int a, int b, int c, int d){         //有参重载构造函数
        weight = a;
        height = b;
        length = c;
        age = d;
    }

    public void Eating(){                      //Eating 公有成员函数
        System.out.println("Eating !");
    }

    protected void Growup(){
        System.out.println("Growup !");
    }

    private void Claim(){                      //Claim 私有成员函数
        System.out.println("I am an animal !");
    }

    public void reFigure(){                    //公有成员函数 声明自身特征
        System.out.println("This Animal :"+"Weight: "+weight+"Height:"+ height+"Length:"+length);
    }

    public void reClaim(){
        this.Claim();
    }
}

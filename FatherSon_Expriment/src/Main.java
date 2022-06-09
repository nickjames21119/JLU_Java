public class Main {
    public static void main(String[] args) {
        //====================Animal========================
        Animal animal = new Animal(1, 2, 3, 4);
        animal.reFigure();
        animal.Eating();

        //私有成员函数，但可间接利用公有成员函数进行配用
        animal.reClaim();

        //======================Fish=========================
        Fish fish = new Fish(1, 2, 3, 4);

        //子类重写函数
        fish.MoveForward();
        fish.MoveBack();
        fish.reFigure();
        fish.Sound();

        //子类调用父类公有成员函数
        fish.Eating();

        //子类调用父类保护成员函数
        fish.Growup();

        //======================Bird==========================
        Bird bird = new Bird(1, 2, 3, 4);

        //子类调用重写函数
        bird.MoveBack();
        bird.MoveForward();
        bird.Sound();
        bird.reFigure();

        //bird额外添加函数
        bird.ChangeFigure(5, 6, 7, 8);

        //子类调用父类保护成员函数
        bird.Growup();

        //子类调用父类公有成员函数
        bird.Eating();


        //=======================转型========================

        //向上转型，父类引用，调用子类方法
        Animal bird1 = new Bird(5, 6, 7, 8);
        //bird1.MoveForward();          //父类引用调用子类，不可调用子类中拓展函数
        bird1.Growup();
        //bird1.MoveBack();             //父类引用调用子类，不可调用子类中拓展函数

        Animal fish1 = new Fish(6, 7, 8, 9);
        Fish fish2 = (Fish)fish1;

        fish2.MoveForward();
        fish2.Eating();
        fish2.Growup();
        fish2.Sound();
        fish2.MoveBack();
    }
}
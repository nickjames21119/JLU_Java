/**
 * 挂档位
 */

public class Gear {
    Gear(){
        System.out.println("Gear Existing !");
    }
    public void Gear_Action(char action){
        if(action=='P'||action=='p'){                   //泊车档
            System.out.println("Paring !");
        } else if (action=='R'||action=='r') {          //倒车档
            System.out.println("Reverse !");
        } else if (action=='N'||action=='n') {          //空挡
            System.out.println("Neutral !");
        }else {
            System.out.println("Action Failed !");
        }
    }
}

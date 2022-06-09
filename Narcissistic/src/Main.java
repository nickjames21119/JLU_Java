public class Main {
    public static void main(String[] args) {
        for(int i=100;i<1000;i++){
            int sum = 0;
            sum += (i%10)*(i%10)*(i%10);
            sum += ((i/10)%10)*((i/10)%10)*((i/10)%10);
            sum += ((i/100)%10)*((i/100)%10)*((i/100)%10);
            if(sum==i){
                System.out.println(sum);
            }
        }
    }
}
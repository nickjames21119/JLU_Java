import java.util.Scanner;
import java.util.Stack;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();
        input.close();
        System.out.println(stack(expression));
    }
    public static Integer stack(String expression) {
        Stack<Integer> operand = new Stack<>();                     //操作数栈
        Stack<String> operator = new Stack<>();                     //操作符栈
        StringBuilder exp = new StringBuilder(expression);          //将表达式保存为可变字符串
        for (int i = 0; i < exp.length(); i++) {
            if(exp.charAt(i) == '+' || exp.charAt(i) == '-' ||
                    exp.charAt(i) == '*' || exp.charAt(i) == '/') {
                if(operator.empty()) {
                    operator.push(String.valueOf(exp.charAt(i)));
                }
                else if(priority(String.valueOf(exp.charAt(i))) > priority(operator.peek())) {
                    operator.push(String.valueOf(exp.charAt(i)));
                }
                else {                                              //运算符优先级低，弹出栈顶元素进行运算
                    Integer obj1 = operand.pop();
                    Integer obj2 = operand.pop();
                    Integer res = result(obj2, obj1, operator.pop());
                    operand.push(res);
                    operator.push(String.valueOf(exp.charAt(i)));
                }
            }
            else if(exp.charAt(i) == '(') {
                operator.push(String.valueOf(exp.charAt(i)));
            }
            else if(expression.charAt(i) == ')') {                //扫描到右括号则循环弹出栈顶符号和操作数栈中的两个数进行运算，结果入栈，知道栈顶符号为左括号为止
                while(!operator.peek().equals("(")) {
                    Integer obj1 = operand.pop();
                    Integer obj2 = operand.pop();
                    Integer res = result(obj2, obj1, operator.pop());
                    operand.push(res);
                }
                operator.pop();
            }
            else if(exp.charAt(i) >= '0' && exp.charAt(i) <= '9'){   //扫描到数字直接入操作数栈
                int j = i + 1;
                StringBuilder num = new StringBuilder(String.valueOf(exp.charAt(i))); //处理多位数的情况
                while(exp.charAt(j) >= '0' && exp.charAt(j) <= '9') {
                    num.append(exp.charAt(j));
                    exp.setCharAt(j, ' ');//将拼接过的数字置为空，以免重复入栈
                    j++;

                }
                operand.push(Integer.valueOf(num.toString()));

            }
            else {
                continue;
            }
        }
        if(!operator.empty()) {                                      //表达式扫描完运算符栈还未空继续计算
            while(!operator.empty()) {
                Integer obj1 = operand.pop();
                Integer obj2 = operand.pop();
                Integer res = result(obj2, obj1, operator.pop());
                operand.push(res);
            }
        }
        return operand.peek();
    }
    public static int priority(String operator) {   //运算符的优先级，数字越大，优先级越大
        if(operator.equals("+") || operator.equals("-"))
            return 1;
        else if(operator.equals("*") || operator.equals("/"))
            return 2;
        else
            return 0;
    }
    public static Integer result(int obj1, int obj2, String operator) {
        Integer res = 0;
        switch(operator) {
            case "+": res = obj1 + obj2;break;
            case "-": res = obj1 - obj2;break;
            case "*": res = obj1 * obj2;break;
            case "/": res = obj1 / obj2;
        }
        return res;
    }
}


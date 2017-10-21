
import java.io.*;

/*
将 1+2-3 这样只含加减法且操作数都是一位数字的中缀表达式转换为后缀表达式
这是表达式文法的形式化描述
----------------------------------------
    <list> -->  <list> + <digit>
            |   <list> - <digit>
            |   <digit>
    <digit> --> 0
            |   1
            |   2
            |   3
            |   4
            |   5
            |   6
            |   7
            |   8
            |   9
----------------------------------------
此示例以 面向文法翻译 为核心思想，采用并演示 递归下降分析法的使用，
这一分析法体现了递归、自顶向下、预测这三个关键点。
*/

public class Parser{
    // 向前看符号，即当前被扫描的终结符号
    private int lookahead;

    public Parser()throws IOException{
        // 开始时，向前看符号是输入串中第一个终结符号
        lookahead = System.in.read();
    }
    public void list()throws IOException{
        digit();
        while(true){
            if(lookahead == '+'){
                match('+');
                digit();
                System.out.write('+');
                continue;
            }else if (lookahead =='-'){
                match('-');
                digit();
                System.out.write('-');
                continue;
            }//
            break;
        }
    }
    public void digit()throws IOException{
        if(Character.isDigit(lookahead)){
            int t = lookahead;
            match(lookahead);
            System.out.write(t);
        }else{
            throw new Error("syntax error!");
        }
    }

    // 将参数t和向前看符号比较，如果匹配就进入到下一个终结符号
    // 因此，match会改变全局变量lookahead的值。
    public void match(int t)throws IOException{
        if(lookahead == t ){
                lookahead = System.in.read();
        }else{
            throw new Error("syntax error!");
        }
    }

    public static  void main(String[] args)throws IOException{
        Parser parse = new Parser();
        parse.list();System.out.println();
    }
}


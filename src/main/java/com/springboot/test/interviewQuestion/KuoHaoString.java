package com.springboot.test.interviewQuestion;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/***
 * Created with IntelliJ IDEA.
 * Description:给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * User: silence
 * Date: 2019-07-19
 * Time: 上午9:50
 */
public class KuoHaoString {

    public static void main(String[] args){
        String s = "{}()[]";
        char[] ch = s.toCharArray();
        for(char c : ch){
            System.out.println((int)c);
        }
    }

    public boolean isValid(String s) {
        if(s.length()%2==1){
            return false;
        }
        while(s.contains("()") || s.contains("{}") || s.contains("[]")){
            if(s.contains("[]")){
                s = s.replace("[]","");
            }
            if(s.contains("()")){
                s = s.replace("()","");
            }
            if(s.contains("{}")){
                s = s.replace("{}","");
            }
        }
        return s.isEmpty();
    }

    public boolean isValidList(String s) {
        if(s.length()%2==1){
            return false;
        }
        List<Character> list = new LinkedList<>();
        list.add('-');
        char[] ch = s.toCharArray();
        for(char c : ch){
            if(compare(list.get(list.size()-1),c)){
                list.remove(list.size()-1);
                continue;
            }
            list.add(c);
        }
        return list.size() == 1;
    }

    public boolean isValidStack(String s){
        if(s.length()%2==1){
            return false;
        }
        if(s.length() == 0){
            return true;
        }
        Stack<Character> stack = new Stack<>();
        stack.push('-');
        char[] ch = s.toCharArray();
        for(char c : ch){
            if((c == ')' && stack.peek() == '(') || (c == '}' && stack.peek() == '{') || (c == ']' && stack.peek() == '[')){
                stack.pop();
                continue;
            }
            if(c == ')' || c == '}' || c ==']'){
                return false;
            }
            stack.push(c);
        }
        return stack.size() == 1;
    }

    private static boolean compare(char a,char b){
        if((a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}')  ){
            return true;
        }
        return false;
    }

}

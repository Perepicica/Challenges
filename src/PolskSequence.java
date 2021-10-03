import java.util.*;
import java.util.stream.Collectors;


public class PolskSequence {
    static int calculate(String mathString) {
        int result = 0;
        boolean unary = false;
        List<Character> highPriority = "*/".chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> lowPriority = "+-".chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        char openBracket = '(';
        char closeBracket = ')';
        Queue<String> polsk = new LinkedList<>();
        Stack<Character> operands = new Stack<>();
        Stack<Integer> tmpResult = new Stack<>();
        String tmp = "";

        for (int i = 0; i < mathString.length(); i++) {

            if (Character.isDigit(mathString.charAt(i))) {
                tmp += mathString.charAt(i);
                if((i+1) == mathString.length() || (!Character.isDigit(mathString.charAt(i+1)))){
                    polsk.add(tmp);
                    tmp = "";
                }
            } else {
                if( i == 0 && mathString.charAt(i+1)==openBracket){
                    unary = true;
                } else if (i==0 || mathString.charAt(i-1)==openBracket){
                    tmp += "-";
                } else  if (operands.empty()
                        || mathString.charAt(i) == openBracket
                        || (operands.peek() == openBracket && mathString.charAt(i)!=closeBracket)) {
                    operands.push(mathString.charAt(i));
                } else {
                    if (mathString.charAt(i) == closeBracket) {
                        while (operands.peek() != openBracket) {
                            polsk.add(Character.toString(operands.pop()));
                        }
                        operands.pop();
                        if (unary) {
                            polsk.add("-");
                        }
                    } else if (highPriority.contains(mathString.charAt(i))
                            && lowPriority.contains(operands.peek())) {
                        operands.push(mathString.charAt(i));
                    } else {
                        polsk.add(Character.toString(operands.pop()));
                        operands.push(mathString.charAt(i));
                    }
                }
            }
        }
        while (!operands.empty()) {
            polsk.add(Character.toString(operands.pop()));
        }

        System.out.println(Arrays.toString(polsk.toArray()));
        int first = 0, second = 0;
        for (String item: polsk) {
            try {
                Integer numeric = Integer.parseInt(item);
                tmpResult.push(numeric);
            } catch (NumberFormatException nfe) {
                first = tmpResult.pop();
                if (tmpResult.empty()) {
                    second = 0;
                } else {
                    second = tmpResult.pop();
                }
                switch (item) {
                    case "+":
                        tmpResult.push(first + second);
                        break;
                    case "-":
                        tmpResult.push(second - first);
                        break;
                    case "*":
                        tmpResult.push(first * second);
                        break;
                    case "/":
                        tmpResult.push(second / first);
                        break;
                }
            }
        }
        return tmpResult.pop();
    }
}

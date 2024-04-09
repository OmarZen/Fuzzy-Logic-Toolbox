package Services;

import Models.Rule;
import Models.Variable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class InferenceRule {

    // get degree of membership of variables
    private Stack<Double> operands;
    private Stack<String> operators;

    public int getPriority(String operator){
        if(Objects.equals(operator, "or")){
            return 1;
        }
        else if(Objects.equals(operator, "and")){
            return 2;
        }
        else if(Objects.equals(operator, "not")){
            return 3;
        }
        return 0;
    }
    public void parseRule(ArrayList<Variable> variables, ArrayList<Rule> rules){
        operands = new Stack<>();
        operators = new Stack<>();
//        for(int i =0 ;i< rules.size();i++) {
//            for (Map.Entry<Variable, FuzzySet> entry : rules.get(i).getRuleVarSet().entrySet()) {
//                System.out.println("\nRule"+i+":"+"\n");
//                System.out.println("Key(variable): " + entry.getKey().getName() + ", Value(FuzzySet): " + entry.getValue().getName());
//            }
//        }
        for(Rule rule : rules) {
            for (Variable var : variables) {// 3 loops
                for(int i=0; i < var.getFuzzySet().size(); i++) { // for each var has 2 fuzzy sets
                    if (Objects.equals(rule.getRuleVarSet().get(var).getName(), var.getFuzzySet().get(i).getName())) { // main if
                        for(int j=0; j < rule.getOperators().size(); j++) {
                            if (var.getType() == Variable.Type.IN) {
                                double degMem = var.getFuzzySet().get(i).getDegreeOfMembership();
                                // degMem
//                                System.out.println("\n degInParseRule: " + degMem + "\n");
                                //special case of the not operator
                                if (Objects.equals(rule.getOperators().get(j), "not") && operators.isEmpty()) {
                                    operators.push("not");
                                    operands.push(degMem);
                                    if (getPriority(operators.peek()) >= getPriority(rule.getOperators().get(j))) {
                                        operators.pop();
                                        double TempResult = operands.peek();
                                        operands.pop();
                                        double result = 1 - TempResult;
                                        operands.push(result);
                                    }
                                } else { // trace
                                    operands.push(degMem); // 1 is top
                                    operators.push(rule.getOperators().get(j)); // and is top
                                    if (getPriority(operators.peek()) >= getPriority(rule.getOperators().get(j)) && Objects.equals(operators.peek(), "or")) { // j ? or j+1 ??
                                        if (operands.size() >= 2) {
                                            operators.pop();
                                            double TempResult1 = operands.peek();
                                            operands.pop();
                                            double TempResult2 = operands.peek();
                                            operands.pop();
                                            double TempResult3 = Math.max(TempResult1, TempResult2);
                                            operands.push(TempResult3);
                                        }
                                    } else if (getPriority(operators.peek()) >= getPriority(rule.getOperators().get(j)) && Objects.equals(operators.peek(), "and")) { // j ? or j+1 ??
                                        if (operands.size() >= 2) {
                                            operators.pop();
                                            double TempResult1 = operands.peek();
                                            operands.pop();
                                            double TempResult2 = operands.peek();
                                            operands.pop();
                                            double TempResult3 = Math.min(TempResult1, TempResult2);
                                            operands.push(TempResult3);
//                                                System.out.println("res: " + TempResult3);
                                        }
                                    } // else and_not - or_not
                                    else if (getPriority(operators.peek()) >= getPriority(rule.getOperators().get(j)) && Objects.equals(operators.peek(), "and_not")) {
                                        if (operands.size() >= 2) {
                                            operators.pop();
                                            double TempResult1 = operands.peek();
                                            operands.pop();
                                            double TempResult2 = operands.peek();
                                            operands.pop();
                                            double TempResult3 = Math.min(1 - TempResult1, TempResult2);
                                            operands.push(TempResult3);
                                        }
                                    } else if (getPriority(operators.peek()) >= getPriority(rule.getOperators().get(j)) && Objects.equals(operators.peek(), "or_not")) {
                                        if (operands.size() >= 2) {
                                            operators.pop();
                                            double TempResult1 = operands.peek();
                                            operands.pop();
                                            double TempResult2 = operands.peek();
                                            operands.pop();
                                            double TempResult3 = Math.max(1 - TempResult1, TempResult2);
                                            operands.push(TempResult3);
                                        }
                                    }
                                }
                            }
                            if (var.getType() == Variable.Type.OUT) {// 15/12/2023 the place of this if
//                            if(operators.isEmpty() && operands.size() == 1){
                                if (!operands.isEmpty()) {
                                    double result = operands.peek();
                                    operands.pop();
//                                double degreeMemShipOutput = result;// i have changed this part bec if the last operand is already poped from the satck
//                                operands.pop();
                                    //if ()
                                    if(var.getFuzzySet().get(i).getDegreeOfMembership() < result) {
                                        var.getFuzzySet().get(i).setDegreeOfMembership(result);
//                                        System.out.println("result from stack:" + result);
//                                        System.out.println("result from outFuzzyName: " + var.getFuzzySet().get(i).getName());
//                                        System.out.println("result from outFuzzyGetter: " + var.getFuzzySet().get(i).getDegreeOfMembership());
                                    }
//                                    else {
//                                        System.out.println("OLD result from outFuzzyGetter: " + var.getFuzzySet().get(i).getDegreeOfMembership());
//                                        System.out.println("NEW result" + result);
//                                    }
                                }
//                                else
//                                    System.out.println("the operands stack is empty ");
                            }
                        }


                    }
                }
            }
        }
    }
}

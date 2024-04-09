import Models.FuzzySet;
import Models.Rule;
import Models.Variable;
import Services.Defuzzification;
import Services.Fuzzification;
import Services.InferenceRule;
import static Services.Helper.*;

import java.util.*;

public class Controller {
    static Scanner input;
    static ArrayList<Variable> variables;
    static ArrayList<Rule> rules;
    static InferenceRule inferenceRule;
    // these booleans (flags) used to validate the main menu
    private static boolean variablesAdded = false;
    private static boolean fuzzySetsAdded = false;
    private static boolean rulesAdded = false;
    public static void runProgram() {
       executeFuzzyStarter();
       executeMainMenu();
    }
    public static void executeFuzzyStarter(){
        int userChoice;
        String briefDesc;
        while (true) {
            View.fuzzyLogicToolBox();
            userChoice = input.nextInt();
            switch (userChoice){
                case 1 -> {
                    Scanner sc = new Scanner(System.in);
                    View.description();
                    briefDesc = sc.nextLine(); // should be stored in something
                    variables = new ArrayList<>();
                    executeMainMenu();
                }
                case 2 -> System.exit(0);
                default -> View.displayErrorMsg();
            }
        }
    }
public static void executeMainMenu() {
    String userChoice;
    boolean exitLoop = false;
    while (!exitLoop) {
        View.mainMenu();
        userChoice = input.next();
        switch (userChoice) {
            case "1" -> {
                if (!variablesAdded) {
                    View.enterVariableData();
                    addVariables();
                    variablesAdded = true;
                } else {
                    View.varException();
                }
            }
            case "2" -> {
                    addFuzzySets();
                    fuzzySetsAdded = true;
            }
            case "3" -> {
                if (variablesAdded && fuzzySetsAdded && !rulesAdded) {
                    View.enterRulesData();
                    rules = new ArrayList<>();
                    addRules();
                    rulesAdded = true;
                } else {
                    View.rulesException();
                }
            }
            case "4" -> {
                if (variablesAdded && fuzzySetsAdded && rulesAdded) {
                    runSimulation();
                } else {
                    View.errorMessage();
                }
            }
            case "Close" -> {
                executeFuzzyStarter();
                exitLoop = true; // Exit loop if 'Close' is selected
            }
            default -> View.invalidChoice();
        }
    }
}
    public static void addVariables(){
        Scanner in = new Scanner(System.in);
        while(input.hasNextLine()){
            String userInput = in.nextLine();
            if(Objects.equals(userInput, "x")) break;
            String[] line = userInput.split(" ");
            if(!validateVarInput(line)){
                View.displayVarWarningMsg();
                continue;
            }
            String varName = line[0], varType = line[1];
            int lower = Integer.parseInt(line[2].substring(1,line[2].length()-1));
            int upper = Integer.parseInt(line[3].substring(0,line[3].length()-1));
            Variable var = new Variable(varName, varType, lower, upper);
            variables.add(var);
        }
//        for (Variable variable : variables) { //
//            System.out.println(variable);
//        }
    }
    public static void addFuzzySets(){
        if(variables.isEmpty()){
//            View.displayEnterVarMsg();
            executeMainMenu();
        }
        // take var name u added in the previous step and compare it
        Scanner in = new Scanner(System.in);
        View.inputName();
        String varName = in.nextLine();
        // loop on variables list and if any name matches the given varName
        for(Variable var : variables){
            Scanner in2 = new Scanner(System.in);
            if(Objects.equals(varName, var.getName())){
                View.enterFuzzySetData();
                while(input.hasNextLine()){
                    String userInput = in2.nextLine();
                    if(Objects.equals(userInput, "x")) break;
                    String[] line = userInput.split(" ");
                    if(!validateFuzzySetInput(line)){
                        View.displaySetWarningMsg();
                        continue;
                    }
                    String setName = line[0], setType = line[1];
                    ArrayList<Integer> setValues = new ArrayList<>();
                    for(int i = 2; i<line.length; ++i) {
                        setValues.add(Integer.parseInt(line[i]));
                    }
                    FuzzySet fuzzySet = new FuzzySet(setName, setType, setValues);
                    var.getFuzzySet().add(fuzzySet);//    !!!!!!!!!!!!!! works
                }
            }else{
                // display error msg, varName given != var name in variables
//                View.displayInvalidVar();
//                continue;
            }
//            for(FuzzySet fuzzySet : var.getFuzzySet()) { //
//                System.out.println(fuzzySet);
//            }
        }
    }
    public static void addRules(){
        Scanner in = new Scanner(System.in);

        while(input.hasNextLine()){// loop on every line in the rules lines
            String userInput = in.nextLine();
            if(Objects.equals(userInput, "x")) break;
            String[] line = userInput.split(" ");
//            if(!validateRulesInput(line)){
//                continue;
//            }
            ArrayList<String> operators = new ArrayList<>();
            Map<Variable, FuzzySet> TempRuleVarSet= new HashMap<>();////////////// this line was outside the while loop
            for(int i = 0; i<line.length-1; ++i) { //loop in the line
                if(Objects.equals(line[i], "=>")){
                    continue;
                }
                else if(Objects.equals(line[i], "or") || Objects.equals(line[i], "and") || Objects.equals(line[i], "not") || Objects.equals(line[i], "and_not") || Objects.equals(line[i], "or_not")){
                    operators.add(line[i]);
                }else {
//                    Variable var = new Variable();
//                    FuzzySet fuzzySet = new FuzzySet();
//                    var.setName(line[i]);
                    String VarNameTemp = line[i];
                    String VarSetTemp= " ";
                    if (i + 1 < line.length) // checker for line i+1    to be in range and avoid errors
                         VarSetTemp=line[i + 1];
                    for (int j =0; j<variables.size();j++)
                    {
                        if (VarNameTemp.equals(variables.get(j).getName()))
                        {
                            for (int k =0 ; k< variables.get(j).getFuzzySet().size();k++)
                            {
                                if (VarSetTemp.equals(variables.get(j).getFuzzySet().get(k).getName()))// cheak the name of the fuzzySet
                                {
                                    TempRuleVarSet.put(variables.get(j),variables.get(j).getFuzzySet().get(k));
                                }
                            }
                        }
                    }
                }
            }
            try {
                Rule rule = new Rule();
                rule.setRuleVarSet(TempRuleVarSet);
                rule.setOperators(operators);
                rules.add(rule);
//                for (Map.Entry<Variable, FuzzySet> entry : TempRuleVarSet.entrySet()) {
//                    System.out.println("Key(variable): " + entry.getKey().getName() + ", Value(FuzzySet): " + entry.getValue().getName());
//                }
//                for(FuzzySet fuzzySet : var.getFuzzySet()){
//                    System.out.println(fuzzySet.getName() + " " + fuzzySet.getDegreeOfMembership());
//                }
//                System.out.println("after the first Rule there are "+rule.getRuleVarSet().size()+" variables used in this Rule " +"\n"); //
//                System.out.println("after the first Rule there are "+operators.size()+" variables used in this Rule " +"\n"); //
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void runSimulation(){
        //check if step 1,2,3 is applied
        // call fuzzification functions to calculate the membership values for each set
        // every time i make this step i should create a new object
        int crispValue;
        View.enterCrispValues();
        for(Variable var : variables){
            if(var.getType() == Variable.Type.IN){
                // take crisp values from user
                View.enterVarCrisps(var);
                crispValue = input.nextInt();
                var.setCrispValue(crispValue);
                Fuzzification fuzzification = new Fuzzification();
                fuzzification.detectXPoints(var); // should be renamed to detectPoints
//                for(FuzzySet fuzzySet : var.getFuzzySet()){ //
//                    System.out.println(fuzzySet.getName() + " " + fuzzySet.getDegreeOfMembership());
//                }
            }
        }
        // get the rules output and assign it to the output degree of memships
        inferenceRule = new InferenceRule();
        inferenceRule.parseRule(variables, rules);
        View.runSimulationView(); // sleep between steps , should be after crisp input
        for(Variable var : variables) {
            if(var.getType() == Variable.Type.OUT){
                Defuzzification.calculateCentroid(var);
                Defuzzification.calculateZ_Star(var);
                Defuzzification.locateOutputCrispSet(var);
                View.viewRiskOutput(var.getName(), var.getOutputSetName(), var.getOutputCrispValue());
                break;
            }
        }
    }
    static { input = new Scanner(System.in);}
}

import Models.Variable;

public class View {
    public static void fuzzyLogicToolBox(){
        System.out.print("""
                           Fuzzy Logic Toolbox:
                           ===================
                           1- Create a new fuzzy system
                           2- Quit
                           """);
    }
    public static void description(){
        System.out.print("""
                Enter the system's name and a brief description:\s
                ------------------------------------------------
                """);
    }
    public static void mainMenu(){
        System.out.print("""
                           Main Menu:
                           ==========
                           1- Add variables.
                           2- Add fuzzy sets to an existing variable
                           3- Add rules.
                           4- Run the simulation on crisp values.
                           """);
    }
    public static void errorMessage(){
        System.out.print("""
                CAN’T START THE SIMULATION! Please add the fuzzy sets and rules first.\s
                """);
    }
    public static void enterVariableData(){
        System.out.print("""
                Enter the variable's name, type (IN/OUT)
                and range ([lower, upper]):
                (Press x to finish)\s
                ------------------------------------------------
                """);
    }
    public static void inputName(){
        System.out.print("""
                        Enter the variable's name:\s
                        --------------------------
                        """);
    }
    public static void enterFuzzySetData(){
        System.out.print("""
                Enter the fuzzy set name, type (TRI/TRAP)
                and values: (Press x to finish)\s
                ------------------------------------------------
                """);
    }
    public static void enterRulesData(){
        System.out.print("""
                Enter the rules in this format: (Press x to finish)\s
                IN_variable set operator IN_variable set => OUT_variable set\s
                ------------------------------------------------------------
                """);
    }
    public static void enterCrispValues(){
        System.out.print("""
                        Enter the crisp values:\s
                        --------------------------
                        """);
    }
    public static void enterVarCrisps(Variable var){
        System.out.print(var.getName() + ": ");
    }
    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void runSimulationView(){
        sleep(500);
        System.out.println("Running the simulation…");

        sleep(1000);
        System.out.println("Fuzzification => done");

        sleep(1500);
        System.out.println("Inference => done");

        sleep(1500);
        System.out.println("Defuzzification => done");
    }
    public static void viewRiskOutput(String varName, String fuzzySetName, double z){
        System.out.println();
        System.out.println("The predicted "+ varName +" is " + fuzzySetName + " (" + z + ")");
    }
    public static void displayErrorMsg(){
        System.out.println("You entered an invalid number, try again");
    }
    public static void displayVarWarningMsg(){
        System.out.println("Wrong Variable Input, Please try again");
    }
    public static void displaySetWarningMsg(){
        System.out.println("Wrong Fuzzy Set Input, Please try again");
    }
    public static void displayInvalidVar(){
        System.out.println("Wrong Variable Input");
    }
    public static void rulesException(){System.out.println("Please enter rules after variables and fuzzy sets!");}
    public static void varException(){System.out.println("Please enter variables first!");}
    public static void invalidChoice(){System.out.println("Invalid choice!");}
}

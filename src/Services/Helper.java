package Services;

public class Helper {
    public static boolean validateVarInput(String[] userInput){
        // check the input goes like given return true
        // proj_funding  IN      [0,    100]   length = 4
        //  line[0]    line[1] line[2] line[3]
        if(!userInput[0].isEmpty() && (userInput[1].equals("IN") || userInput[1].equals("OUT")) && userInput.length == 4){
            if(userInput[2].charAt(0) == '[' && userInput[2].charAt(userInput[2].length() - 1) == ','){
                return userInput[3].charAt(userInput[3].length() - 1) == ']'; // return true
            }
        }
        return false;
    }
    public static boolean validateFuzzySetInput(String[] userInput){
        // check the input goes like given return true
        // very_low TRAP 0 0 10 20  length = 6
        if(userInput[0].isEmpty()){
            return false;
        }
        if(userInput[1].equals("TRAP") && userInput.length == 6){
            try{
                Integer.parseInt(userInput[2]);
                Integer.parseInt(userInput[3]);
                Integer.parseInt(userInput[4]);
                Integer.parseInt(userInput[5]);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }else if(userInput[1].equals("TRI") && userInput.length == 5){
            try{
                Integer.parseInt(userInput[2]);
                Integer.parseInt(userInput[3]);
                Integer.parseInt(userInput[4]);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }
        return false;
    }
    public static boolean validateRulesInput(String[] userInput){
        // check the input goes like given return true
        // proj_funding  high     or    exp_level   expert     =>      risk low
        //  line[0]    line[1]  line[2]  line[3]   line[4]  line[5]    line[6]
//        for(int i=0; i<userInput.length; i++){
//            if(!userInput[i].isEmpty()){
//
//            }
//        }
        return true;
    }
}

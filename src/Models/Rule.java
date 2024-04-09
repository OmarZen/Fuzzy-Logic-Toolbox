package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rule {

   private Map<Variable, FuzzySet> ruleVarSet;
   private ArrayList<String> operators;
   public Rule(){
      ruleVarSet = new HashMap<>();
      operators = new ArrayList<>();
      if (ruleVarSet == null || operators == null) { // check the attributes of Rule are not null
         throw new IllegalArgumentException("RuleVarSet and Operators cannot be null.");
      }
   }

   public void setRuleVarSet(Map<Variable, FuzzySet> ruleVarSet) {
      this.ruleVarSet = ruleVarSet;
   }
   public Map<Variable, FuzzySet> getRuleVarSet() {
      return ruleVarSet;
   }
   public void setOperators(ArrayList<String> operators) {
      this.operators = operators;
   }
   public ArrayList<String> getOperators() {
      return operators;
   }

}

package Services;

import Models.FuzzySet;
import Models.Variable;

import java.util.ArrayList;

public class Defuzzification {
    //Stateless class
    public static void calculateCentroid(Variable var) { // handle var type == OUT
        for(FuzzySet fuzzySet : var.getFuzzySet()){
            int sum = 0;
            for(int i=0; i<fuzzySet.getValues().size(); i++){
                sum += fuzzySet.getValues().get(i);
            }
//            System.out.println("Sum of each fuzzy set: " + sum);
            if(fuzzySet.isTrap()){
                double centroidSum = (double) sum / 4;
                fuzzySet.setCentroid(centroidSum);
            }else{ // TRI
                double centroidSum = (double) sum / 3;
                fuzzySet.setCentroid(centroidSum);
//                System.out.println("Centroid sum for each fuzzy set: " + centroidSum);
            }
        }
    }
    public static void calculateZ_Star(Variable var) { // handle var type == OUT
        double outputZ, sumOfCentroidMemship = 0.0, sumOfMemberships = 0.0;
        for(FuzzySet fuzzySet : var.getFuzzySet()) {
//            System.out.println("Defuzz degOfMem: " + fuzzySet.getDegreeOfMembership());
            sumOfCentroidMemship += fuzzySet.getCentroid() * fuzzySet.getDegreeOfMembership();
            sumOfMemberships += fuzzySet.getDegreeOfMembership();
        }
//        System.out.println("Numerator: " + sumOfCentroidMemship);
//        System.out.println("Denimenator: " + sumOfMemberships);
        outputZ = sumOfCentroidMemship / sumOfMemberships;
        // set outputZ
//        System.out.println("OutputZ: " + outputZ);
        var.setOutputCrispValue(outputZ);
    }
    public static void locateOutputCrispSet(Variable var){ // handle var type == OUT
        // we should detect Z belong to which sets, then take the set with higher degree of memships
        // to assign its output setName and set it to a var attribute
        ArrayList<FuzzySet> fuzzySets = new ArrayList<>();
        for(FuzzySet fuzzySet : var.getFuzzySet()){
            for(int i=0; i < fuzzySet.getValues().size() - 1; i++){
                if(var.getOutputCrispValue() >= fuzzySet.getValues().get(i)){
                    if(var.getOutputCrispValue() <= fuzzySet.getValues().get(i+1)){
                        fuzzySets.add(fuzzySet);
                        break; // i already know it belongs to the set
                    }
                }
            }
        }
        double largeMemship = -1.0;
        for(FuzzySet fuzzySet : fuzzySets){
            if(fuzzySet.getDegreeOfMembership() >= largeMemship){
                largeMemship = fuzzySet.getDegreeOfMembership();
                var.setOutputSetName(fuzzySet.getName());
            }
        }
    }
}

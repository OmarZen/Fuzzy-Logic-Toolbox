package Models;

import java.util.ArrayList;

public class Variable {
    private String name;
    public enum Type{IN, OUT}
    private Type type;
    private int lowerBound, upperBound;// range
    private ArrayList<FuzzySet> fuzzySets;
    private int crispValue;//       //////////////////////
    private double outputCrispValue;
    private String outputSetName;

    //setters and getters

    public Variable(String name, String type, int lowerBound, int upperBound) {
        this.name = name;
        this.type = type.equals("IN") ? Type.IN : Type.OUT;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.crispValue =0;
        this.outputCrispValue = 0.0;
        fuzzySets = new ArrayList<>();
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }
    public void getLowerBound(int lowerBound){
        this.lowerBound = lowerBound;
    }
    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
    public int getUpperBound() {
        return upperBound;
    }
    public void setType(Type type){
        this.type = type;
    }
    public Type getType(){
        return type;
    }
    public void setFuzzySet(ArrayList<FuzzySet> fuzzySet) {
        this.fuzzySets = fuzzySet;
    }
    public ArrayList<FuzzySet> getFuzzySet() {
        return fuzzySets;
    }
    public void setCrispValue(int crispValue) {
        this.crispValue = crispValue;
    }
    public int getCrispValue() {
        return crispValue;
    }
    public void setOutputCrispValue(double outputCrispValue) {
        this.outputCrispValue = outputCrispValue;
    }
    public double getOutputCrispValue() {
        return outputCrispValue;
    }
    public void setOutputSetName(String outputSetName) {
        this.outputSetName = outputSetName;
    }
    public String getOutputSetName() {
        return outputSetName;
    }
    public String toString() {
        return "Variable" + '\n' +
                "{" +
                "name='" + name + '\n' +
                ", type=" + type + '\n' +
                ", lowerBound=" + lowerBound + '\n' +
                ", upperBound=" + upperBound + '\n' +
                '}';
    }
}

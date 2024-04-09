package Models;

import java.util.ArrayList;
import java.util.Objects;

public class FuzzySet {
    private String name;
    public enum FuzzySetType{TRI,TRAP};
    private FuzzySetType fuzzySetType;
    private ArrayList<Integer> values;//    x - values
    private double centroid;
    private double degreeOfMembership;

    public FuzzySet(String name, String type, ArrayList<Integer> values) {
        this.name = name;
        this.fuzzySetType = type.equals("TRAP") ? FuzzySetType.TRAP : FuzzySetType.TRI;
        this.values = new ArrayList<>(values);
        centroid = 0.0;
        degreeOfMembership = 0.0;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setValues(ArrayList<Integer> values) {
        this.values = values;
    }
    public ArrayList<Integer> getValues() {
        return values;
    }
    public void setFuzzySetType(FuzzySetType fuzzySetType){
        this.fuzzySetType = fuzzySetType;
    }
    public FuzzySetType getFuzzySetType() {
        return fuzzySetType;
    }
    public boolean isTri(){
        return Objects.equals(fuzzySetType.toString(), "TRI");
    }
    public boolean isTrap() {
        return Objects.equals(fuzzySetType.toString(), "TRAP");
    }
    public void setDegreeOfMembership(double degreeOfMembership) {
        this.degreeOfMembership = degreeOfMembership;
    }
    public double getDegreeOfMembership() {
        return degreeOfMembership;
    }
    public void setCentroid(double centroid) {
        this.centroid = centroid;
    }
    public double getCentroid() {
        return centroid;
    }
    public String toString() {
        return "Fuzzy Set" + '\n' +
                "{" +
                "name='" + getName() + '\n' +
                ", type=" + getFuzzySetType() + '\n' +
                ", values =" + getValues() +
                '}';
    }
}

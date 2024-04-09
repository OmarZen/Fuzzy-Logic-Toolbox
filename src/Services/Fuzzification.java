package Services;

import Models.Line;
import Models.Variable;

public class Fuzzification {
    private final Line line;
    public Fuzzification(){
        line = new Line();
    }
    public void detectXPoints(Variable variable){
        for(int j=0; j<variable.getFuzzySet().size(); j++) {
            for (int i = 0; i < variable.getFuzzySet().get(j).getValues().size() - 1; ++i) {
                if (variable.getCrispValue() >= variable.getFuzzySet().get(j).getValues().get(i)) {
                    if (variable.getCrispValue() <= variable.getFuzzySet().get(j).getValues().get(i + 1)) {
                        line.getPoint1().setX(variable.getFuzzySet().get(j).getValues().get(i));
                        line.getPoint2().setX(variable.getFuzzySet().get(j).getValues().get(i+1));
                        detectYPoints(i, variable, j);
                        calculateDegreeOfMembership(variable, j);
                        break;
                    }
                }
            }
        }
    }
    public void detectYPoints(int i, Variable variable, int fuzzySetIndex) {
        // check trap or tri for sets that crisp value intersects it
        if (variable.getFuzzySet().get(fuzzySetIndex).isTrap()) {
            if (i == 0) {
                line.getPoint1().setY(0);
                line.getPoint2().setY(1);
            } else if (i == 1) {
                line.getPoint1().setY(1);
                line.getPoint2().setY(1);
            } else if (i == 2) {
                line.getPoint1().setY(1);
                line.getPoint2().setY(0);
            }
        } else if (variable.getFuzzySet().get(fuzzySetIndex).isTri()) {
            if (i == 0) {
                line.getPoint1().setY(0);
                line.getPoint2().setY(1);
            } else if (i == 1) {
                line.getPoint1().setY(1);
                line.getPoint2().setY(0);
            }
        }
    }
    public double calculateSlope(){
        return (double) (line.getPoint2().getY() - line.getPoint1().getY()) / (line.getPoint2().getX() - line.getPoint1().getX());
    }
    public double calculate_b(){
        // y-ax = b
        return (line.getPoint1().getY() - (calculateSlope() * line.getPoint1().getX()));
    }
    public void calculateDegreeOfMembership(Variable variable, int j){
        double degMemShip = (calculateSlope() * variable.getCrispValue()) + calculate_b();
        // bec if you have 2 membership degrees you take the max between them
        if(degMemShip>variable.getFuzzySet().get(j).getDegreeOfMembership()) {
            variable.getFuzzySet().get(j).setDegreeOfMembership(degMemShip);

        }
    }

}

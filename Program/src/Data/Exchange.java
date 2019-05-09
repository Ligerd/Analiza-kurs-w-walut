package Data;

public class Exchange {
    private Currency from;
    private Currency to;
    private double course;
    private double percent;
    private double constant;
    public Exchange(Currency from, Currency to, double course, double percent, double constant){
        this.from=from;
        this.to=to;
        this.course=course;
        this.percent=percent;
        this.constant=constant;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public double getConstant() {
        return constant;
    }

    public double getCourse() {
        return course;
    }

    public double getPercent() {
        return percent;
    }
}

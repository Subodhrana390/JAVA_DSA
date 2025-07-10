package UnKnown;
class Pen {

    String color;
    String type;

    public void write() {
        System.out.println("Writing Something");
    }

    public void printColor() {
        System.out.println(this.color);
    }
}

public class OOPS {

    public static void main(String[] args) {

        Pen pen1 = new Pen();
        pen1.color = "red";
        pen1.type = "ball";
        pen1.write();
        pen1.printColor();
        
    }
}

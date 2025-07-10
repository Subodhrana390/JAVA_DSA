package UnKnown;
interface Animal {
    void walk();
}

//
//abstract class Animal {
//    abstract void walk();
//}

//class Horse extends Animal {
//    void walk() {
//        System.out.println("Walk on 4 legs");
//    }
//}

class Horse implements Animal {
    public void walk() {
        System.out.println("Walk on 4 legs");
    }
}

//class Chicken extends Animal {
//    void walk() {
//        System.out.println("Walk on 2 legs");
//    }
//}

class Chicken implements Animal {
    public void walk() {
        System.out.println("Walk on 2 legs");
    }
}


class Abstration {
    public static void main(String[] args) {
        Horse h = new Horse();
        h.walk();
    }
}

package UnKnown;
public class BitManipulation {
    public static void main(String[] args) {
        int n=5;
        int pos=1;
        int bitmask = 1<<pos;
        int newBitMask= ~bitmask;
        if((newBitMask & n)==0){
            System.out.println("bit is zero");
        }else {
            System.out.println("Bit is 1");
        }
    }
}

import java.util.HashMap;

/**
 * Code class as specified in Nand2Tetris.org course chapter 6 - Assembler
 * This class will be part of a main program called "Assembler" also as described
 * Author : Ayushka Partohap
 * E-mail : partohap.ayushka@gmail.com
 */

public class Code {
    private HashMap<String,Integer> destMap;
    private HashMap<String,Integer> jumpMap;
    private HashMap<String,Integer> compMap;

    public Code() {
        generateDestMap();
        generateJumpMap();
        generateCompMap();
    }

    public int dest (String s){
        return destMap.get(s);
    }

    private void generateDestMap(){
        destMap = new HashMap<>();
        destMap.put(null,  0x000);
        destMap.put("M",   0x001);
        destMap.put("D",   0x010);
        destMap.put("MD",  0x011);
        destMap.put("A",   0x100);
        destMap.put("AM",  0x101);
        destMap.put("AD",  0x110);
        destMap.put("AMD", 0x111);
    }

    private void generateJumpMap() {
    }

    private void generateCompMap() {
    }
}

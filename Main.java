
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine().trim();

            //  galvenais cikls

            if (line.equals("exit")) {
                break;

            } else if (line.equals("about")) {
                System.out.println("251RDB285 Artjoms Dilgers 10.grupa");
            } else if (line.startsWith("comp ")) {

                // comp komandas
                String[] input = line.split("\\s+");
                if (input.length < 2) {
                    System.out.println("wrong command format");
                } else {
                    comp(input[1]);
                }
            } else if (line.startsWith("decomp ")) {

                // decomp komandas
                String[] input = line.split("\\s+");
                if (input.length < 3) {
                    System.out.println("wrong command format");
                } else {
                    try {
                        int byteCount = Integer.parseInt(input[1]);

                        // Pārbauda vai baitu skaits atbilst komandas formātam
                        if (input.length != byteCount + 2) {
                            System.out.println("wrong command format");
                        } else {
                            byte[] bytes = new byte[byteCount];
                            // ielasa baitus no komandas
                            for (int i = 0; i < byteCount; i++) {
                                bytes[i] = Byte.parseByte(input[i + 2]);
                            }
                            decomp(bytes);
                        }
                    } catch (Exception e) {
                        System.out.println("wrong command format");
                    }
                }
            } else {
                System.out.println("wrong command");
            }
        }
        sc.close();
    }

    // kompresijas funkcija
    public static void comp(String dnaStr) {
        String dna = dnaStr.toUpperCase();

        // parbaude
        if (!dna.matches("[ACGT]+")) {
            System.out.println("wrong command format");
            return;
        }

        int length = dna.length();
        //  baitu skaitu (4 nukleotidi uz 1 baitu)
        int byteCount = (length + 3) / 4;
        byte[] result = new byte[byteCount + 1];
        result[0] = (byte) length; // pirmajā baitā glabā

        int poss = 0;
        for (int i = 1; i < result.length; i++) {
            int value = 0; //

            // iepako vienā baitā
            for (int j = 0; j < 4; j++) {
                value <<= 2; // pabida bitus pa kreisi
                if (poss < length) {
                    char c = dna.charAt(poss++);
                    //  nukleotīdu uz 2 bitu
                    value |= (c == 'A') ? 0 : (c == 'C') ? 1 : (c == 'G') ? 2 : 3;
                }
            }
            result[i] = (byte) value; //  rezultāts baitā
        }

        //  comp heksa formata
        for (int i = 0; i < result.length; i++) {
            if (i > 0) System.out.print(" ");
            System.out.printf("%X", result[i] & 0xFF);
        }
        System.out.println();
    }

    // dekompresijas funkcija
    public static void decomp(byte[] bytes) {
        //baiti heksadecimalā formātā
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) System.out.print(" ");
            System.out.printf("%X", bytes[i] & 0xFF);
        }
        System.out.println();

        int length = bytes[0] & 0xFF; // iegust sekvences garumu no pirmā baita
        int print = 0; //

        // baitu apstrāde
        for (int i = 1; i < bytes.length && print < length; i++) {

            for (int shift = 6; shift >= 0 && print < length; shift -= 2) {
                int bits = (bytes[i] >> shift) & 0b11; // izolē 2 bitus
                // konvertē 2 bitus atpakaļ uz nukleotīdu
                char c = (bits == 0) ? 'A' : (bits == 1) ? 'C' : (bits == 2) ? 'G' : 'T';
                System.out.print(c);
                print++;
            }
        }
        System.out.println();
    }
}
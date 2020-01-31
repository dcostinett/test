package net.cozz.bitset;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class HackerRankBitset {
    enum Operator {
        AND,
        OR,
        XOR,
        FLIP,
        SET
    }
    public static void main(String[] args) {
        int N = 0; // length of bitsets
        int M = 0; // number of operations

        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();

        BitSet b1 = new BitSet(N);
        BitSet b2 = new BitSet(N);
        BitSet[] bitSets = new BitSet[] {null, b1, b2};
        List<int[]> setcounts = new ArrayList<>(M);

        for (int i = 0; i < M; i++) {
            String opStr = scanner.next();
            int setSwitch = scanner.nextInt();
            int param2 = scanner.nextInt();

            if (Operator.valueOf(opStr).equals(Operator.FLIP) || Operator.valueOf(opStr).equals(Operator.SET)) {
                doOp(Operator.valueOf(opStr), bitSets[setSwitch], null, param2);
            } else {
                doOp(Operator.valueOf(opStr), bitSets[setSwitch], bitSets[param2], 0);
            }
            setcounts.add(new int[]{b1.cardinality(), b2.cardinality()});
        }
        scanner.close();
        for (int[] setcount : setcounts) {
            System.out.println(String.format("%d %d", setcount[0], setcount[1]));
        }
    }

    static void doOp(Operator op, BitSet lhs, BitSet rhs, int i) {
        switch (op) {
            case AND:
                lhs.and(rhs);
                break;
            case OR:
                lhs.or(rhs);
                break;
            case XOR:
                lhs.xor(rhs);
                break;
            case FLIP:
                lhs.flip(i);
                break;
            case SET:
                lhs.set(i);
                break;
        }
    }
}

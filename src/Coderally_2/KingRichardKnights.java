package Coderally_2;

import java.io.*;
import java.util.*;

class Abn {
    long a; // Using long to support unsigned 64-bit values
    long b;
    int angle;
}

public class KingRichardKnights {

    // Main function to execute the program
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(reader.readLine().trim());
        long s = Long.parseLong(reader.readLine().trim());

        List<long[]> commands = new ArrayList<>();
        for (int commandsRowItr = 0; commandsRowItr < s; commandsRowItr++) {
            String[] commandsRowTemp = reader.readLine().trim().split(" ");
            long[] commandsRow = Arrays.stream(commandsRowTemp).mapToLong(Long::parseLong).toArray();
            if (commandsRow.length != 3) {
                throw new IllegalArgumentException("Bad input");
            }
            commands.add(commandsRow);
        }

        long v = Long.parseLong(reader.readLine().trim());
        List<Long> knights = new ArrayList<>();
        for (long i = 0; i < v; i++) {
            knights.add(Long.parseLong(reader.readLine().trim()));
        }

        List<long[]> result = kingRichardKnights(n, s, knights, commands);

        for (int resultRowItr = 0; resultRowItr < result.size(); resultRowItr++) {
            long[] rowItem = result.get(resultRowItr);
            for (int resultColumnItr = 0; resultColumnItr < rowItem.length; resultColumnItr++) {
                writer.write(Long.toString(rowItem[resultColumnItr]));

                if (resultColumnItr != rowItem.length - 1) {
                    writer.write(" ");
                }
            }

            if (resultRowItr != result.size() - 1) {
                writer.newLine();
            }
        }

        writer.newLine();
        writer.flush();
    }

    public static List<long[]> kingRichardKnights(long n, long s, List<Long> knights, List<long[]> commands) {
        Abn[] pos = new Abn[knights.size()];
        List<long[]> result = new ArrayList<>();
        long[][] tcom = new long[commands.size()][3];
        long[] incom = {0, 0};
        long[] dcom = {1, 1, n};

        // Initialize positions
        for (int i = 0; i < knights.size(); i++) {
            pos[i] = new Abn();
            pos[i].a = (knights.get(i) / n) + 1; // 1-based index
            pos[i].b = (knights.get(i) % n) + 1; // 1-based index
        }

        // Process commands for transformations
        for (int i = 0; i < commands.size(); i++) {
            long x, y, ksx, ksy, res1, res2;
            if (i != 0) {
                x = commands.get(i)[0] - commands.get(i - 1)[0];
                y = commands.get(i)[1] - commands.get(i - 1)[1];
                ksx = commands.get(i - 1)[2] - commands.get(i)[2] - x;
                ksy = commands.get(i - 1)[2] - commands.get(i)[2] - y;

                switch (i % 4) {
                    case 0:
                        res1 = tcom[i - 1][0] + x;
                        res2 = tcom[i - 1][1] + y;
                        break;
                    case 1:
                        res1 = tcom[i - 1][0] + ksy;
                        res2 = tcom[i - 1][1] + x;
                        break;
                    case 2:
                        res1 = tcom[i - 1][0] + ksx;
                        res2 = tcom[i - 1][1] + ksy;
                        break;
                    case 3:
                        res1 = tcom[i - 1][0] + y;
                        res2 = tcom[i - 1][1] + ksx;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid command index");
                }
            } else {
                x = commands.get(i)[0] - 1;
                y = commands.get(i)[1] - 1;
                ksx = n - commands.get(i)[2] - x;
                ksy = n - commands.get(i)[2] - y;

                res1 = x;
                res2 = y;
            }
            tcom[i][0] = res1;
            tcom[i][1] = res2;
        }

        // Analyze knight positions with respect to commands
        for (int ii = 0; ii < pos.length; ii++) {
            int gr = commands.size();
            int sm = 0;
            int sgg = -1;

            for (int g = 0; g < 30; g++) {
                boolean oi = true;
                int ss = (gr + sm) / 2;
                boolean sd;
                long[] analysisResult = analyse(pos[ii], tcom[ss], commands.get(ss)[2]);

                sd = analysisResult[0] == 1;

                if (sd) {
                    gr = ss;
                    sgg = ss;
                } else {
                    sm = ss;
                }

                if (ss == 0 || ss == commands.size() - 1) {
                    if (!oi) {
                        break;
                    }
                    oi = false;
                }
            }

            int lenc = commands.size() - 1;

            if (sgg == 0) {
                long[] analysisResult = analyse(pos[ii], incom, n);
                pos[ii].a = analysisResult[1];
                pos[ii].b = analysisResult[2];
                pos[ii].angle = 0;
            } else if (sgg == -1) {
                long[] analysisResult = analyse(pos[ii], tcom[lenc], commands.get(lenc)[2]);
                pos[ii].a = analysisResult[1];
                pos[ii].b = analysisResult[2];
                pos[ii].angle = (commands.size()) % 4;
            } else {
                long[] analysisResult = analyse(pos[ii], tcom[sgg - 1], commands.get(sgg - 1)[2]);
                pos[ii].a = analysisResult[1];
                pos[ii].b = analysisResult[2];
                pos[ii].angle = sgg % 4;
            }

            long[] transformedPosition = new long[2];
            if (sgg == 0) {
                transformedPosition = transform(pos[ii], dcom);
            } else if (sgg == -1) {
                transformedPosition = transform(pos[ii], commands.get(lenc));
            } else {
                transformedPosition = transform(pos[ii], commands.get(sgg - 1));
            }
            result.add(transformedPosition);
        }

        return result;
    }

    // Function to analyze the position and determine its validity
    public static long[] analyse(Abn k, long[] com, long c) {
        long a = k.a - com[0];
        long b = k.b - com[1];

        if (a <= 0 || b <= 0 || a > c + 1 || b > c + 1) {
            return new long[]{1, a, b}; // True, invalid position
        }

        return new long[]{0, a, b}; // False, valid position
    }

    // Function to transform the position based on the angle
    public static long[] transform(Abn k, long[] com) {
        long[] result = new long[2];
        switch (k.angle) {
            case 0:
                result[0] = com[0] + k.a - 1;
                result[1] = com[1] + k.b - 1;
                break;
            case 1:
                result[0] = com[0] + k.b - 1;
                result[1] = com[2] + com[1] - k.a + 1;
                break;
            case 2:
                result[0] = com[2] + com[0] - k.a + 1;
                result[1] = com[2] + com[1] - k.b + 1;
                break;
            case 3:
                result[0] = com[2] + com[0] - k.b + 1;
                result[1] = com[1] + k.a - 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid angle");
        }
        return result;
    }
}

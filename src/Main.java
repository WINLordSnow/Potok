import java.io.*;

class arrTest {
    private int arrLength;
    private int mod;
    private int arr[];

    arrTest(int[] arr, int mod) {
        arrLength = arr.length;
        this.mod = mod;
        this.arr = setmod(mod, arr);
    }

    private int[] setmod(int modTemp, int[] arr) {
        int k = 0;
        int arrTemp[] = new int[arrLength];
        for (int i : arr)
            if (i%modTemp == 0) {
                if (k == 0) {
                    arrTemp[k++] = i;
                } else{
                    int j = 0;
                    do {
                        if (i < arrTemp[j]) {
                            for (int y = k; y > j; y--)
                                arrTemp[y] = arrTemp[y-1];
                            arrTemp[j] = i;
                            j = ++k;
                        }
                        j++;
                    } while (k > j);
                    if (j == k) {
                        arrTemp[k++] = i;
                    }
                }
            }
        int arrTemp1[] = new int[k];
        for (int i = 0; i < k; i++)
            arrTemp1[i] = arrTemp[i];
        return arrTemp1;
    }

    void arrInit() {
       // arr = {3, 6, 8, 12, 21, 140};
        System.out.println("+"+ arr[2]);
    }
    void arrPrint() {


        for (int i : arr)
            System.out.print(i + " ");
        System.out.println("");
       // arr = setmod(mod);
        for (int i : arr)
            System.out.print(i + " ");
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;

        int[] massiv = {5, 3, 8, 12, 6, 21, 140, 9};
        arrTest arrX = new arrTest(massiv, 3);
        arrTest arrS = new arrTest(massiv, 7);
        arrTest arrM = new arrTest(massiv, 21);
            do {
                choice = br.readLine();
                System.out.println(choice);
                if (!true & (!choice.equals("exit")&&!choice.equals("help"))) {
                    System.out.println("Сперва создай массив, для справки введи help");
                    choice = "error";
                } else {
                    switch (choice) {
                        case "init array": {
                            arrX.arrInit();
                            System.out.println();
                        } break;
                        case "print": arrX.arrPrint(); break;
                        case "anyMore": break;
                        case "clear": break;
                        case "merge": break;
                        case "help": choice="exit"; break;
                    }
                }

            } while (!choice.equals("exit"));
    }
}

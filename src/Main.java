import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

enum lists {
    x(2)/*, m(7), s(21)*/;
    private int mod;

    lists(int l) {
        mod = l;
    }

    int getMod() {
        return mod;
    }

    static int getSize() {
        return lists.values().length;
    }
}

class arrTest {
    private int arrLength;
    private final int mod;
    private int arr[];
    private final String name;
    boolean listEmpty = true;

    arrTest(String name, int mod) {
        this.name = name;
        this.mod = mod;
    }

    void arrSet(int[] arr) {
        arrLength = arr.length;
        this.arr = setmod(mod, arr);
    }

    private int[] setmod(int modTemp, int[] arr) {
        int k = 0;
        int arrTemp[] = new int[arr.length];
        for (int i : arr)
            if (i%modTemp == 0) {
                listEmpty = false;
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
        if (k==0)
            listEmpty = true;
        int arrTemp1[] = new int[k];
        for (int i = 0; i < k; i++)
            arrTemp1[i] = arrTemp[i];
        return arrTemp1;
    }

    void arrPrint() {
        if (!listEmpty) {
            for (int i : arr)
                System.out.print(i + " ");
            System.out.println("\n");
        } else
            System.out.println("Список " + name + " Пуст");
    }

    void arrClear() {
        int[] arrTempClear = new int[0];
        arr = arrTempClear;
        listEmpty = true;
    }

    static void arrClearAll(arrTest[] listArrTemp) {
        for (arrTest i : listArrTemp)
            i.arrClear();
    }

    static boolean arrAnyMore(int[] arrTemp) {
        int[] arrTemp1;
        arrTemp1 = arrTemp;
        for (int i = 0; i < arrTemp1.length; i++)
            for (lists j : lists.values()) {
                if ((arrTemp1[i]%j.getMod()) == 0)
                    arrTemp1[i] = j.values()[0].getMod();
            }
        int k = 0;
        for (int i = 0; i < arrTemp1.length; i++)
            if (arrTemp1[i] == lists.values()[0].getMod())
                k++;
        return !(k == arrTemp1.length);
    }

    int[] merge(arrTest[] listArrTemp) {
        int sum = 0;
        int k = 0;
        for (arrTest i : listArrTemp)
            sum += i.arr.length;
        int[] arrTemp = new int[sum];
        for (arrTest i : listArrTemp) {
            for (int j : i.arr) {
                arrTemp[k] = j;
                k++;
            }
            i.arrClear();
        }
        return arrTemp;
    }
}

public class Main {

    static int[] inNumeric(String str) {
        int indexBegin = 0, indexEnd = 0;
        int k = 0;
        int[] arrTemp1;

        ArrayList<String> arrTemp = new ArrayList<>();
        char[] arrChar = str.toCharArray();
        for (int i = 0; i < str.length()-1; i++) {
            if ((arrChar[i] == ' ')&(arrChar[i+1] != ' '))
                indexBegin = i+1;
            if ((arrChar[i] != ' ')&(arrChar[i+1] == ' ')) {
                indexEnd = i + 1;
                k++;
                arrTemp.add(str.substring(indexBegin, indexEnd));
            }
        }
        arrTemp1 = new int[k];
        for (int j = 0; j < k; j++)
            arrTemp1[j] =  Integer.parseInt(arrTemp.get(j));
        return arrTemp1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        boolean initBool;

        lists listmod;
        int[] massiv = {9, 3, 8,5,34,7,8,132,6};
        arrTest[] listArr = new arrTest[lists.getSize()];

        for (lists p :lists.values()) {
            listArr[p.ordinal()] = new arrTest(p.name(), p.getMod());
            listArr[p.ordinal()].arrSet(massiv);
        }

        //String[] listName = {"x", "m", "s"};
        //int[] listMod = {3, 7, 21};



        /*arrTest[] listArr = new arrTest[listMod.length];
        for (int i = 0; i < listMod.length; i++) {
            listArr[i] = new arrTest(listName[i], listMod[i]);
            listArr[i].arrSet(massiv);
        }*/
        do {
            choice = br.readLine().toLowerCase();
            initBool = false;
            if (choice.length() > 5) {
                if (choice.substring(0, 4).equals("init")) {
                    try {
                        initBool = true;
                        arrTest.arrClearAll(listArr);
                        massiv = inNumeric(choice.substring(4, choice.length()) + " ");
                    } catch (NumberFormatException e) {
                        System.out.println("Массив должен состоять из целых чисел. Набери help!!!");
                    }
                    for (int i = 0; i < lists.getSize(); i++)
                        listArr[i].arrSet(massiv);
                }
            }
            switch (choice) {
                case "print":
                    for (arrTest i : listArr)
                        i.arrPrint();
                    break;
                case "print x":
                case "print s":
                case "print m":
                    try {
                        lists p = lists.valueOf(
                                choice.substring(choice.length() - 1));
                        listArr[p.ordinal()].arrPrint();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Набери help");
                    }
                    break;
                case "anymore":
                    System.out.println(arrTest.arrAnyMore(massiv));
                    break;
                case "clear":
                    arrTest.arrClearAll(listArr);
                    break;
                case "clear x":
                case "clear s":
                case "clear m":
                    try {
                        lists p;
                        p = lists.valueOf(choice.substring(choice.length() - 1).toLowerCase());
                        listArr[p.ordinal()].arrClear();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Набери help");
                    }
                    break;
                case "merge":
                    arrTest arrMerge = new arrTest("Merge", 1);
                    arrMerge.arrSet(arrMerge.merge(listArr));
                    arrMerge.arrPrint();
                    break;
                case "help":
                    System.out.println("init array\t- инициализация списков набором значений array");
                    System.out.println("print \t\t- печать всех списков ");
                    System.out.println("print type \t- печать конкретного списка, где type принимает значения X,S,M");
                    System.out.println("anyMore\t\t- выводит на экран были ли значения не вошедшие " +
                            "ни в один список, возможные значения true, false");
                    System.out.println("clear type\t- чистка списка , где type принимает значения X,S,M");
                    System.out.println("merge\t\t- слить все списки в один вывести на экран и очистить все списки");
                    System.out.println("help\t\t- вывод справки по командам ");
                    break;
                default:
                    if (!initBool)
                        System.out.println("Введи help для справки");
            }
        } while (!choice.equals("exit"));
    }
}

import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;

public class WordFrequencies {
    public static void main(String[] args) {
        boolean isCaseSense = false;
        boolean isClean = false;
        for (int i = 0; i < args.length; i++) {
            isCaseSense |= args[i].equals("-s");
            isClean |= args[i].equals("-c");
            if (args[i].equals("-sc")) {
                isClean = true;
                isCaseSense = true;
            }
        }
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashSet<String> wordSet = new HashSet<String>(); 
        while (scan.hasNext()) {
            String string = scan.next();
            string = string.replaceAll("-","");
            string = string.replaceAll("[^a-zA-Z0-9]"," ");
            String[] arr = string.split(" ");
            for (int j = 0; j < arr.length; j++) {
                if (!(arr[j].equals(""))) {
                    arr[j] = (!isCaseSense ? arr[j].toUpperCase() : arr[j]);
                    Integer count = map.get(arr[j]);
                    map.put(arr[j], (count == null ? 1 : count + 1));
                    wordSet.add(arr[j]);
                }
            }
        }
        for (String word : wordSet) {
            System.out.println(word + (isClean ? "" : " " + map.get(word)));
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MyTree {

    static class Node {
        char ch;
        HashMap<Character, Node> children = new HashMap<Character, Node>() ;
        boolean isWord ;
        public Node(char c) {
            this.ch = c ;
        }
    }

    static HashMap<Character, Node> MyMap = new HashMap<Character, Node>() ;

    public static void getWordsStartingWith(String input){
        List<String> result = new ArrayList<String>() ;
        String s="";
        char currentChar;
        Node currentNode = null ;
        HashMap<Character, Node> currentChildren = MyMap ;
        for(int i = 0 ; i < input.length(); i++){//input node ulaşma
            currentChar = input.charAt(i) ;
            s+=currentChar;
            currentNode = currentChildren.get(currentChar);
            currentChildren = currentNode.children ;
        }
        if(currentNode != null){//cocuklarını rec ile çekme
            getAll(currentNode, input, result) ;
        }
        for(String words:result){
            System.out.println(words);
        }
    }

    public static void getAll(Node currentNode, String input, List<String> result){

        HashMap<Character, Node> currentChildren = currentNode.children ;
        Node currentChild = null ;
        for(Map.Entry<Character, Node> entry : currentChildren.entrySet()){
            currentChild = entry.getValue() ;
            if(currentChild.isWord){
                result.add(input + currentChild.ch) ;
            }
            getAll(currentChild, input + currentChild.ch, result);
        }
    }

    public static void insert(String word) {
        Node current = null;
        char c;
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (i == 0) {
                if (!MyMap.containsKey(c)) {
                    MyMap.put(c, new Node(c));
                }
                current = MyMap.get(word.charAt(i));
            } else {
                if (!current.children.containsKey(word.charAt(i))) {
                    current.children.put(c, new Node(c));
                }
                current = current.children.get(word.charAt(i));
            }

            if (i == word.length() - 1) {
                current.isWord = true;
            }
        }
    }

    public static void main(String[] argv) throws IOException {
        FileReader fr = new FileReader(argv[0]);
        BufferedReader br = new BufferedReader(fr);
        String buffer;
        System.out.println("Sozluk Yukleniyor. Lutfen Bekleyin...");
        while ((buffer = br.readLine()) != null) {
            insert(buffer);
        }
        System.out.println("Sozluk Yuklendi.");
        br.close();
        fr.close();
        int count = 1;
        String word="";
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("Bir Kelime Yazip Enter Tusuna Basiniz");
            word=input.next();
            System.out.println("Olasi Kelimeler");
            getWordsStartingWith(word);
            System.out.println();
        }
        while(count==1);
    }
}

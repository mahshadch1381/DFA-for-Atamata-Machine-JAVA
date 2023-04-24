import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

class yal{
    public String name;
    public List<String> ebteda=new ArrayList<>();
    public List<String> enteha=new ArrayList<>();
    public yal(String name){
        this.name=name;
    }
}
 class My_scanner {
    StringTokenizer stringTokenizer;
    BufferedReader bufferedReader;
    public My_scanner() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }
     long nextLong() { return Long.parseLong(next()); }
    int nextInt() { return Integer.parseInt(next()); }
     double nextDouble() { return Double.parseDouble(next()); }
    String nextLine() {
        String string = "";
        try {
            string = bufferedReader.readLine();
        }
        catch (Exception e) { }
        return string;
    }
     String next(){
         while (stringTokenizer == null || !stringTokenizer.hasMoreElements()) {
             try {
                 stringTokenizer = new StringTokenizer(bufferedReader.readLine());
             }
             catch (Exception e) { }
         }
         return stringTokenizer.nextToken();
     }
}
public class DFA2 {
    public static void main(String[] args) {
        My_scanner scanner = new My_scanner();
        String line = scanner.nextLine();
        String[] num = line.split(" ");
        int n = Integer.parseInt(num[0]);
        int m = Integer.parseInt(num[1]);
        int s = Integer.parseInt(num[2]);
        int q = Integer.parseInt(num[3]);
        int[] shenasayi = new int[n + 1];
        String sh = scanner.nextLine();
        String[] shen = sh.split(" ");
        for (int i = 1; i < n + 1; i++) {
            shenasayi[i] = Integer.parseInt(shen[i - 1]);
        }
        List<yal> list=new ArrayList<yal>();
        for (int i = 0; i < m; i++) {
            String khat = scanner.nextLine();
            String[] array = khat.split(" ");
            String masir = array[0];
            if(list.size()==0){
                yal y=new yal(masir);
                y.ebteda.add(array[1]);
                y.enteha.add(array[2]);
                list.add(y);
            }
            else {
               List<String> names=new ArrayList<>();
               for (int f=0;f<list.size();f++){
                   names.add(list.get(f).name);
               }
                int x=0;
              in:  for (int k=0;k<names.size();k++){
                    if (names.get(k).equals(masir)){
                        list.get(k).ebteda.add(array[1]);
                        list.get(k).enteha.add(array[2]);
                        break in; }
                    else {
                        x++;
                    }
                }
                if(x== names.size()){
                    yal y=new yal(masir);
                    y.ebteda.add(array[1]);
                    y.enteha.add(array[2]);
                    list.add(y);
                }
            }
        }
        String[] reshteha = new String[q];
        boolean[] nahayi=new boolean[q];
        for (int i = 0; i < q; i++) {
            String baresi = scanner.nextLine();
            reshteha[i] = baresi;
        }

        List<String> [] way=new ArrayList[q];
        List<String> names=new ArrayList<>();
        for (int f=0;f<list.size();f++){
            names.add(list.get(f).name);
        }
       out: for (int i=0;i<q;i++){
            int vaziat=s;
            way[i]=new ArrayList<>();
            int j=0;
          mid:  for (;j<reshteha[i].length();j++){
                String harf=reshteha[i].charAt(j)+"";
                if (names.size()>0){
                    String vaziat_st=vaziat+"";
                    if (!names.contains(harf)){
                        continue out;
                    }
                    int p=names.indexOf(harf);
                        if(list.get(p).name.equals(harf)){
                            if(list.get(p).ebteda.contains(vaziat_st)){
                                int f=list.get(p).ebteda.indexOf(vaziat_st);
                                vaziat=Integer.parseInt(list.get(p).enteha.get(f));
                                way[i].add(vaziat+"");
                                if(j==reshteha[i].length()-1&&shenasayi[vaziat]==1){
                                    nahayi[i]=true;
                                    continue out;
                                }
                                if(j==reshteha[i].length()-1&&shenasayi[vaziat]==0){
                                    nahayi[i]=false;
                                    continue out;
                                }
                                continue mid;
                            }
                            else {
                                nahayi[i]=false;
                                continue out;
                            }
                        }

                }
            }
        }
        for (int h=0;h<q;h++){
            String result=" ";
            if(nahayi[h]){
                result="Yes ";
            }
            if(!nahayi[h]){
                result="No ";
            }
            String rah="";
            for (int g=0;g<way[h].size();g++){
                rah=rah+way[h].get(g)+" ";
            }
            System.out.println(result+rah);
        }

    }
}

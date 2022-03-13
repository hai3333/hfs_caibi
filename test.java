import java.io.*;
import java.util.*;

public class test {
    public static void main(String[] args) throws IOException {
        String string=new String();
        int a=0;
        HashMap<Integer, Collection> Sites = new HashMap<>();
        ArrayList<String> sb = new ArrayList<>();
        ArrayList<String> coll = new ArrayList<>();
        HashMap<Integer, String> Sites1 = new HashMap<>(); //存储省份名
        try {
            // create a reader instance
            BufferedReader br = new BufferedReader(new FileReader("E:/软件工程/yq_in.txt"));
            // read until end of file
            String line;
            while ((line = br.readLine()) != null) {
                coll.clear(); //每次开始前清零coll
                coll.add(String.valueOf(a));//给省份编号
                //  遍历line 将数据分开储存到hashmap
                String [] arr = line.split("\\s+",3);
                for(String ss : arr){
                    coll.add(ss);
                }

                /*
                把arrayList里面的值转到hashMap 一个存储编号+ 省份 另一个存储编号+市＋人数
                 */

                String NO1= (String) ((ArrayList<?>) coll).get(0);
                int NO= Integer.valueOf(NO1).intValue();
                String sheng=(String) ((ArrayList<?>) coll).get(1);
                String  city=(String) ((ArrayList<?>) coll).get(2);
                String num= (String) ((ArrayList<?>) coll).get(3);
                sb.add(city+"   "+ num);
                Sites1.put(NO,sheng);
                Sites.put(NO, Collections.singleton(sb.get(a)));
                a++;
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /*
        输出结果
         */
        System.out.println(Sites.values());
        StringBuilder stringBuilder=new StringBuilder(); //格式化输出并保存在字符串中
        for(int i=0;i<Sites1.size();i++) {
            // 输出每一个value
            if(!Objects.equals(Sites1.get(i), Sites1.get(i -1))) {
                System.out.println();
                System.out.println(Sites1.get(i));
                stringBuilder.append(Sites1.get(i)+"\n");
            }
            System.out.println(Sites.get(i));
            stringBuilder.append(Sites.get(i)+"\n");
        }
        //写文件 并且替换[]
        String content = stringBuilder.toString();
        content=content.replace("[","");
        content=content.replace("]","");
        File file = new File("E:/软件工程/yq_out2.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(content);
        bw.close();
        System.out.println("finish");

    }
}

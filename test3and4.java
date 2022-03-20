package SETest;

import java.io.*;
import java.util.*;

public class test3and4 {

//    public static void TreeMapSortByValue(HashMap<String, Integer> map) {
//        // 将map.entrySet()转换成list
//        ArrayList<Map.Entry<String, Integer>> list1 = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
//        // 通过比较器来实现排序
//        list1.sort(new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                // 升序排序
//                Integer h1 = o1.getValue();
//                Integer h2 = o2.getValue();
//                return -Integer.compare(h1, h2);//o1.getValue().compareTo(o2.getValue());
//            }
//        });
//        for (Map.Entry<String, Integer> mapping : list1)
//            System.out.println(mapping.getKey() + "  " + mapping.getValue());
//
//
//    }

    // 返回省份排名
    public static List listSortByValue(HashMap<String, Integer> map) {
        // 将map.entrySet()转换成list
        List list2 = new ArrayList();
        ArrayList<Map.Entry<String, Integer>> list1 = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        // 通过比较器来实现排序
        list1.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // 升序排序
                Integer h1 = o1.getValue();
                Integer h2 = o2.getValue();
                return -Integer.compare(h1, h2);//o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Map.Entry<String, Integer> mapping : list1) {
            //System.out.println(mapping.getKey() + ":" + mapping.getValue());
            list2.add(mapping.getKey());
        }

        return list2;

    }


    public static int Sum(HashMap<String, Integer> map) {
        ArrayList<Map.Entry<String, Integer>> list1 = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        // 通过比较器来实现排序
        int sum = 0;
        for (Map.Entry<String, Integer> mapping : list1) {
            sum += mapping.getValue();
        }
        //System.out.println(sum);
        return sum;
    }


    public static void main(String[] args) throws IOException {
        String string = "";
        int a = 0;
        HashMap<Integer, HashMap<String, Integer>> Sites = new HashMap<>();
        ArrayList<String> sb = new ArrayList<>();
        ArrayList<String> coll = new ArrayList<>();
        HashMap<Integer, String> Sites1 = new HashMap<>(); //存储省份名
        try {
            // create a reader instance
            BufferedReader br = new BufferedReader(new FileReader("E:/软件工程/yq_in.txt"));
            // read until end of file
            String line;
            while ((line = br.readLine()) != null) {
                HashMap SitTest = new HashMap();
                SitTest.clear();
                coll.clear(); //每次开始前清零coll
                coll.add(String.valueOf(a));//给省份编号
                //  遍历line 将数据分开储存到hashmap
                String[] arr = line.split("\\s+", 3);
                for (String ss : arr) {
                    coll.add(ss);
                }

                /*
                把arrayList里面的值转到hashMap 一个存储编号+ 省份 另一个存储编号+市＋人数
                 */

                String NO1 = (String) ((ArrayList<?>) coll).get(0);
                int NO = Integer.valueOf(NO1).intValue();
                String sheng = (String) ((ArrayList<?>) coll).get(1);
                String city = (String) ((ArrayList<?>) coll).get(2);
                String num = (String) ((ArrayList<?>) coll).get(3);
                int num1 = Integer.valueOf(num).intValue();
                SitTest.put(city, num1);
                sb.add(city + "   " + num);
                Sites1.put(NO, sheng);
                Sites.put(NO, SitTest);
                a++;
            }
            //System.out.println(Sites1);
            //System.out.println(Sites);
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        List list = new ArrayList();
        for (int i = 0; i < Sites.size(); i++) {
            if (!list.contains(Sites1.get(i))) {
                list.add(Sites1.get(i));
            }
        }
        HashMap<String, Integer> mapSheng = new HashMap<String, Integer>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < list.size(); i++) {
            map.clear();
            // System.out.println( list.get(i));


            for (int j = 0; j < Sites1.size(); j++) {
                if (Objects.equals(list.get(i), Sites1.get(j))) {
                    map.putAll(Sites.get(j));
                    // System.out.println("1" + Sites.get(j));
                }

            }
            //System.out.println(map.values());
            //TreeMapSortByValue(map);
            int sum = Sum(map);
            //System.out.println("该省份总人数"+sum);
            mapSheng.put(list.get(i).toString(), sum);
            //System.out.println();
        }
        //

        //排省份
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList list1 = new ArrayList();

        list1 = (ArrayList) listSortByValue(mapSheng);

        //System.out.println("sb"+list1);


        for (int i = 0; i < Sites.size(); i++) {
            if (!list1.contains(Sites1.get(i))) {
                list1.add(Sites1.get(i));
            }
        }
        HashMap<String, Integer> mapSheng1 = new HashMap<String, Integer>();
        HashMap<String, Integer> map1 = new HashMap<String, Integer>();
        for (int i = 0; i < list1.size(); i++) {
            map1.clear();
            System.out.println(list1.get(i));
            stringBuilder.append(list1.get(i) + "\n");


            for (int j = 0; j < Sites1.size(); j++) {
                if (Objects.equals(list1.get(i), Sites1.get(j))) {
                    map1.putAll(Sites.get(j));
                    // System.out.println("1" + Sites.get(j));
                }

            }
            //System.out.println(map.values());
            //TreeMapSortByValue(map1);

            ArrayList<Map.Entry<String, Integer>> list10 = new ArrayList<Map.Entry<String, Integer>>(map1.entrySet());
            // 通过比较器来实现排序
            list10.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    // 升序排序
                    Integer h1 = o1.getValue();
                    Integer h2 = o2.getValue();
                    return -Integer.compare(h1, h2);//o1.getValue().compareTo(o2.getValue());
                }
            });
            for (Map.Entry<String, Integer> mapping : list10) {
                System.out.println(mapping.getKey() + "  " + mapping.getValue());
                stringBuilder.append(mapping.getKey() + "  " + mapping.getValue() + "\n");
            }


            int sum1 = Sum(map1);
            System.out.println("该省份总人数" + sum1);
            stringBuilder.append("该省份总人数" + sum1 + "\n");
            mapSheng1.put(list1.get(i).toString(), sum1);
            System.out.println();
            stringBuilder.append("\n");


//

        }
        System.out.println("这是输出" + stringBuilder);


        String content = stringBuilder.toString();
        File file = new File("E:/软件工程/yq_out35555.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(content);
        bw.close();
        System.out.println("finish");

    }
}






package test;

import java.util.HashMap;
import java.util.Map;

public class Temp {
    public static void main(String[] args) {
        String PAY_BSN_TP_CD = "";
        StringBuffer line = new StringBuffer();
        Map<String, String> map = new HashMap<>() {
            {
                put("a", "a");
                // put("", "");
            }
        };
        String payString = map.get(PAY_BSN_TP_CD);
        System.out.println(payString.toString()); // 这里会报空指针null.toString()非法
        System.out.println("payString:" + payString);
        line.append(PAY_BSN_TP_CD); //不会报错，输出null
        System.out.println("line:" + line);
    }
}

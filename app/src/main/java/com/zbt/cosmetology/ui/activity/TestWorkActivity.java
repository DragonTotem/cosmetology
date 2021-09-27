package com.zbt.cosmetology.ui.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestWorkActivity {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int FirstNotRepeatingChar(String str) {
        if(str.length() == 0) return -1;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }else{
                map.put(c, 1);
            }
        }
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(map.get(c) == 1){
                return i;
            }
        }
        return -1;

    }

    public int Add(int num1,int num2) {
        while(num2 != 0){
            int c = ((0xffff)&(num1 & num2)) << 1;
            num1 ^= num2;
            num2 = c;
        }
        return num1;
    }
}

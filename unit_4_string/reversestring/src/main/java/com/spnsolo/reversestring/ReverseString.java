package com.spnsolo.reversestring;

import com.spnsolo.reversestring.exept.InvalidIndex;

public class ReverseString {
    private static String rev(String src){
        char[] chars = src.toCharArray();
        char buffer;

        for(int i = 0; i < src.length()/2;++i){
            buffer = chars[i];
            chars[i] = chars[(src.length() - 1)-i];
            chars[(src.length() - 1)-i] = buffer;
        }
        src = String.valueOf(chars);
        return src;
    }
    public static String reverse(String src){
        String[]substrings = src.split(" ");
        for(int i = 0; i<substrings.length;++i){
            src = src.replace(substrings[i], rev(substrings[i]));
        }
       return src;
    }
    public static String reverse(String src, String dest){
        if(src.contains(dest)){

            String reversed = reverse(dest);
            src = src.replace(dest,reversed);
            return src;

        }else{
            return "There is not such substring";
        }
    }
    public static String reverse(String src, int firstIndex, int lastIndex) throws InvalidIndex {

        if(firstIndex < 0 && lastIndex > src.length())throw new InvalidIndex();
        String substring = src.substring(firstIndex,lastIndex+1);
        substring = reverse(substring);

        char[] chars = src.toCharArray();
        for (int i = firstIndex;i <= lastIndex;i++){
            chars[i] = substring.charAt(i - firstIndex);
        }

        String out = String.valueOf(chars);
        return out;
    }
}

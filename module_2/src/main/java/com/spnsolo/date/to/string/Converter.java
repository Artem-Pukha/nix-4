package com.spnsolo.date.to.string;

public class Converter {

    public static String changeAvailableFormattedDate(String date){
        String changedDate = "";
        int slashCounter = 0;
        int hyphenCounter = 0;
        for (int i = 0; i < date.length(); i++) {
            if(date.charAt(i) == '/')slashCounter++;
            if(date.charAt(i)=='-')hyphenCounter++;
            if(slashCounter == 2 && hyphenCounter == 0)
            {
                changedDate = stringWithSlash(date);
                break;
            }
            if(hyphenCounter == 2 && slashCounter == 0){
                changedDate = stringWithHyphen(date);
                break;
            }
        }
        return changedDate;
    }

    private static String stringWithSlash(String date){
        String[] splittedDate = date.split("[/ ]");

        String year = "";
        String day = "";
        String month = "";

        int sizeOfFirstPart = splittedDate[0].length();
        if(sizeOfFirstPart == 4)year = splittedDate[0];
        else if(sizeOfFirstPart == 2)day = splittedDate[0];
        else return "";

        if(splittedDate[1].length()==2)month=splittedDate[1];
        else return "";

        if(Integer.parseInt(month) < 0 && Integer.parseInt(month) > 12)return "";

        if(!year.equals(""))day = splittedDate[2];
        else year = splittedDate[2];

        return year+month+day;
    }

    private static String stringWithHyphen(String date){
        String[] splittedDate = date.split("[- ]");

        String year = "";
        String day = "";
        String month = "";

        if(splittedDate[0].length() != 2) return "";
        else month = splittedDate[0];

        if(splittedDate[1].length() != 2) return "";
        else day = splittedDate[1];

        if(splittedDate[2].length() != 4) return "";
        else year = splittedDate[2];

        return year + month + day;
    }

}

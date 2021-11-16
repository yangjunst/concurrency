package com.maomao.learn;

/********************************************
 * 文件名称: Demo.java
 * 功能说明:
 * 开发时间: 2021/3/20 2:39
 * 开发人员: 雪域青竹
 *********************************************/


public class Demo {

    public static void main(String[] args) {
        System.out.println(printHeart("*"));
    }

    private static String printHeart(String input){

        int[] array = {0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 4, 5, 2, 3, 4, 1, 0, 1,0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < array.length; i++) {

            if(i % 7 == 0)

                sb.append("\n");

            if(array[i] == 0)

                sb.append("   ");

            else if(array[i] == 4)

                sb.append("  ");

            else if(array[i] == 5)

                sb.append(" I ");

            else if(array[i] == 2)

                sb.append("love ");

            else if(array[i] == 3)

                sb.append("you");

            else

                sb.append("  "+input);

        }

        return sb.toString();

    }

}

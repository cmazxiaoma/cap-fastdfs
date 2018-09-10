package com.cmazxiaoma.fastdfs;



public class Test {

    public static void main(String[] args) {
        String path0 = "group1/M00/00/00/wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg";
        String path1 = "wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg";


        System.out.println(path0.substring(path0.lastIndexOf(".") + 1));
        System.out.println(path0.substring(path0.lastIndexOf("/") + 1,
                path0.lastIndexOf(".")));
        System.out.println(path0.substring(path0.lastIndexOf("/") + 1));
    }
}

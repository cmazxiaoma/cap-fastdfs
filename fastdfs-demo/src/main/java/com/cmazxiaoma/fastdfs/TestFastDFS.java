package com.cmazxiaoma.fastdfs;

import main.java.org.csource.common.NameValuePair;

import com.cmazxiaoma.fastdfs.core.FastDFSUtil;

public class TestFastDFS {

    public static String BASE_UPLOAD_PATH = TestFastDFS.class.getProtectionDomain().getCodeSource()
            .getLocation().getPath();

    public static String MAYDAY_IMG = BASE_UPLOAD_PATH + "upload/mayday_life.jpg";

    public static String BASE_DOWNLOAD_PATH =
            "D:/myeclipse_workspace/fastdfs_demo/src/main/resources/download";

    public static void main(String[] args) {
        NameValuePair[] metaDataList = new NameValuePair[] {
                new NameValuePair("bound_name", "mayday"),
                new NameValuePair("masterpiece", "juejiang")
        };

        //tests upload mayday_life.jpg
        //FastDFSUtil.getInstance().upload(MAYDAY_IMG, "jpg", metaDataList);

        //upload success
        //group:group1
        //path:M00/00/00/wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg
        //group:group2
        //path:M00/00/00/wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg
        //group:group1
        //path:M00/00/00/wKgMLFpLKEiAb-0eAAd8hCbLY3Y478.jpg
        //group1 M00/00/00/wKgMLFpLO-GAR41SAAd8hCbLY3Y765.jpg

        //download group1/M00/00/00/wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg
        //FastDFSUtil.getInstance().download("group1", "M00/00/00/wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg"
                //, BASE_DOWNLOAD_PATH);

        //gets group1 "M00/00/00/wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg" file info
        //FastDFSUtil.getInstance().getFileInfo("group1", "M00/00/00/wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg");


        //gets group2 "M00/00/00/wKgMN1pK-pyAUmU5AAd8hCbLY3Y335.jpg" file info
        //FastDFSUtil.getInstance().getFileInfo("group2", "M00/00/00/wKgMN1pK-pyAUmU5AAd8hCbLY3Y335.jpg");

        //gets "M00/00/00/wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg" file metadata
        //NameValuePair[] metaLists = FastDFSUtil.getInstance().getFileMataData("group1", "M00/00/00/wKgMLFpHPe-AOvAYAAd8hCbLY3Y881.jpg");

        //for (NameValuePair nvp : metaDataList) {
            //System.out.println(nvp.getName() + ":" + nvp.getValue());
        //}

        //detele group1 "M00/00/00/wKgMLFpLO-GAR41SAAd8hCbLY3Y765.jpg" file
        FastDFSUtil.getInstance().delete("group1", "M00/00/00/wKgMLFpLO-GAR41SAAd8hCbLY3Y765.jpg");

    }
}

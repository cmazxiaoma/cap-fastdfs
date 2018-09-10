package com.cmazxiaoma.fastdfs.core;

import java.io.File;
import java.io.FileOutputStream;

import main.java.org.csource.common.NameValuePair;
import main.java.org.csource.fastdfs.ClientGlobal;
import main.java.org.csource.fastdfs.FileInfo;
import main.java.org.csource.fastdfs.StorageClient;
import main.java.org.csource.fastdfs.StorageServer;
import main.java.org.csource.fastdfs.TrackerClient;
import main.java.org.csource.fastdfs.TrackerServer;

import org.apache.commons.io.IOUtils;

public class FastDFSUtil {

    private static String classPath = FastDFSUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    private static String FASTDFS_CLIENT_CONF = classPath + "fdfs_client.conf";

    private static TrackerClient trackerClient = null;

    private static TrackerServer trackerServer = null;

    private static StorageClient storageClient = null;

    private static StorageServer storageServer = null;

    private FastDFSUtil () {
        FastDFSUtil.init();
    }

    private static void init() {
        try {
            ClientGlobal.init(FASTDFS_CLIENT_CONF);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (Exception e) {
            throw new RuntimeException("init exception");
        }
    }

    public static FastDFSUtil getInstance() {
        return FastDFSUtilHolder.INSTANCE;
    }

    private static class FastDFSUtilHolder {
        private static final FastDFSUtil INSTANCE = new FastDFSUtil();
    }

    public static String upload(String maydayImg, String fileExtName, NameValuePair[] metaDataList) {
        try {
            String fileIds[] = storageClient.upload_file(maydayImg, fileExtName, metaDataList);

            System.out.println(fileIds.length);
            System.out.println("group:" + fileIds[0]);
            System.out.println("path:" + fileIds[1]);

            return fileIds[0] + fileIds[1];
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("upload exception");
        }
    }

    public static void download(String group, String file, String storePath) {
        try {
            byte[] data = storageClient.download_file(group, file);
            IOUtils.write(data, new FileOutputStream(new File(storePath
                    + "/" + file.substring(file.lastIndexOf("/") + 1))));
            System.out.println("download success");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("download exception");
        }
    }

    public static FileInfo getFileInfo(String group, String file) {
        try {
            FileInfo fileInfo = storageClient.get_file_info(group, file);
            System.out.println("source ip=" + fileInfo.getSourceIpAddr());
            System.out.println("file sizes=" + fileInfo.getFileSize());
            System.out.println("timestamp=" + fileInfo.getCreateTimestamp());
            System.out.println("crc32=" + fileInfo.getCrc32());

            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("gets file info exception");
        }
    }

    public static NameValuePair[] getFileMataData(String group, String file) {
        try {
            NameValuePair[] metas = storageClient.get_metadata(group, file);
            return metas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("gets file metadata exception");
        }
    }

    public static void delete(String group, String file) {
        try {
            storageClient.delete_file(group, file);
            System.out.println("detele file success");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("deletes file exception");
        }
    }
}

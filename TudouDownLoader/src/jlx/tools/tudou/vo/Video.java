package jlx.tools.tudou.vo;

/**
 * 视频的对象
 */
public class Video {
    private String id;
    private String uploadFileMd5;
    private String fileSize;
    private String baseUrl;
    private String fileSha1;
    private String fullUrl;
    // 质量，清晰度,(原画:99；：5；4：3；：2)
    private String playerType;
    //编码方式  f4v;mp4
    private String encodeType;
    /**
     * Constructors.
     */
    public Video() {
    }

    /**
     * @return id - {return content description}
     */
    public String getId() {
        return id;
    }

    /**
     * @param id - {parameter description}.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return uploadFileMd5 - {return content description}
     */
    public String getUploadFileMd5() {
        return uploadFileMd5;
    }

    /**
     * @param uploadFileMd5 - {parameter description}.
     */
    public void setUploadFileMd5(String uploadFileMd5) {
        this.uploadFileMd5 = uploadFileMd5;
    }

    /**
     * @return fileSize - {return content description}
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize - {parameter description}.
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return baseUrl - {return content description}
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl - {parameter description}.
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return fileSha1 - {return content description}
     */
    public String getFileSha1() {
        return fileSha1;
    }

    /**
     * @param fileSha1 - {parameter description}.
     */
    public void setFileSha1(String fileSha1) {
        this.fileSha1 = fileSha1;
    }

    /**
     * @return fullUrl - {return content description}
     */
    public String getFullUrl() {
        return fullUrl;
    }

    /**
     * @param fullUrl - {parameter description}.
     */
    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    /**
     * @return playerType - {return content description}
     */
    public String getPlayerType() {
        return playerType;
    }

    /**
     * @param playerType - {parameter description}.
     */
    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    /**
     * @return encodeType - {return content description}
     */
    public String getEncodeType() {
        return encodeType;
    }

    /**
     * @param encodeType - {parameter description}.
     */
    public void setEncodeType(String encodeType) {
        this.encodeType = encodeType;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Video [id=" + id + ", uploadFileMd5=" + uploadFileMd5 + ", fileSize=" + fileSize
                + ", baseUrl=" + baseUrl + ", fileSha1=" + fileSha1 + ", fullUrl=" + fullUrl
                + ", playerType=" + playerType + ", encodeType=" + encodeType + "]";
    }

}

package jlx.tools.tudou.vo;


/**
 * 视频清晰度 <br>
 * <p>
 * Create on : 2012-11-9<br>
 * <p>
 * </p>
 * <br>
 * 
 * @author jianglinxue<br>
 * @version TudouDownLoader v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public enum Definition {
    原画("99"), 超清("5"), 高清("3"), 流畅("2");
    // 成员变量
    private String code;

    // 构造方法
    private Definition(String code) {
        this.code = code;
    }

    /**
     * @return code - {return content description}
     */
    public String getCode() {
        return code;
    }
}

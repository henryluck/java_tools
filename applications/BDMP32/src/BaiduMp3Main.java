import org.watij.webspec.dsl.Tag;
import org.watij.webspec.dsl.WebSpec;

/**
 * {class description} <br>
 * <p>
 * Create on : 2013-2-1<br>
 * <p>
 * </p>
 * <br>
 * 
 * @author jianglinxue<br>
 * @version BDMP32 v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class BaiduMp3Main {

    /**
     * {method description}.
     * 
     * @param args
     */
    public static void main(String[] args) {
        WebSpec spec = new WebSpec().ie();

        spec.open("http://music.baidu.com/top/dayhot");
        // 高品质的按钮
        Tag highAs = spec.jquery("a[class=high-rate-icon]");
        int j = 6;
        while (true) {

            spec.browser(0); // 每次循环，要回到默认也上

            Tag highA = highAs.at(j++);
            if (highA == null || !highA.exists()) {
                break;
            }
            try {
                // 点击高品质按钮
                highA.click();
                spec.browser(1);

                spec.jquery("input[id=bit192]").click();
                spec.jquery("a[id=download]").click();
                // 关闭当前弹出窗口
                spec.browser(1).close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("第" + j + "个出错！");
            }

        }
        spec.browser().closeAll();
        spec.closeAll();

    }

}

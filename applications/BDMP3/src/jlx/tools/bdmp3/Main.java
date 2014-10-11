package jlx.tools.bdmp3;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2013-1-26<br>
 * <p>
 * </p>
 * <br>
 * @author jianglinxue<br>
 * @version BDMP3 v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class Main {

    /**
     * {method description}.
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
            final WebClient webClient = new WebClient();
            
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.setJavaScriptTimeout(3600*1000);
            webClient.getOptions().setRedirectEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(3600*1000);

            webClient.waitForBackgroundJavaScript(600*1000);
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());


            // Get the first page
            final HtmlPage page1 = webClient.getPage("http://music.baidu.com/top/dayhot");
            
            System.out.println(page1.asXml());
//            IOUtils.write(page1.asXml(),new FileOutputStream("g:/temp/bdmp3.html"));
            
            List<HtmlAnchor> anchors =   (List<HtmlAnchor>)page1.getByXPath("//a[@class='high-rate-icon']");
            
            
            for (HtmlAnchor htmlAnchor : anchors) {
                HtmlPage p = htmlAnchor.click();
                webClient.closeAllWindows();
                HtmlPage p2 = webClient.getPage(p.getUrl());
                //歌曲名称
                HtmlAnchor songName = (HtmlAnchor)p2.getFirstByXPath("//a[@class='song-link-hook']");
                String name = songName.getTextContent();
                
                //320k input框
                HtmlRadioButtonInput input =(HtmlRadioButtonInput)p2.getElementById("bit320");
                input.click();
                //下载按钮
                HtmlAnchor download = (HtmlAnchor)p2.getElementById("download");
                HtmlPage o = (HtmlPage)download.click();
                InputStream inputStream = o.getWebResponse().getContentAsStream();
                IOUtils.copy(inputStream, new FileOutputStream("g:/temp/"+name+".mp3"));
                System.out.println(o);
                //IOUtils.write(p2.asXml(),new FileOutputStream("g:/temp/bdmp3-sub.html"));
                break;
            }
            
            
            System.out.println(anchors);
            
            /*

            // Get the form that we are dealing with and within that form, 
            // find the submit button and the field that we want to change.
            final HtmlForm form = page1.getFormByName("myform");

            final HtmlSubmitInput button = form.getInputByName("submitbutton");
            final HtmlTextInput textField = form.getInputByName("userid");

            // Change the value of the text field
            textField.setValueAttribute("root");

            // Now submit the form by clicking the button and get back the second page.
            final HtmlPage page2 = button.click();

            webClient.closeAllWindows();
            */
        }

    }

package jlx.tools.research;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import jlx.util.RegexUtils;
import jlx.util.WebUtil;

import org.apache.commons.io.FileUtils;

public class Test {

    /**
     * {method description}.
     * @param args
     * @throws Exception 
     */
    public static void main(final String[] args) throws Exception {
        String pre = "http://tj.58.com/sou/jh_";
        String pix = "/final_1/?from=home-search";
        
//        String comName = "天津泰新丰商贸有限公司";
        
        List<String> comlist = FileUtils.readLines(new File("f:/list.txt"),"gbk");
        StringBuffer buffer = new StringBuffer();
        for (String comName : comlist) {

            String encodeName = URLEncoder.encode(comName, "UTF-8");
            
            String url = pre + encodeName + pix;
            
            String content = WebUtil.getWebPageContent(url);
            
            //System.out.println(content);
            //第一层url，招聘信息链接
            String l1Url = RegexUtils.getMatchString(content, "<a class=\"t\" href=\"([^\"]*)\"");
            if(l1Url == null){
                continue;
            }
            String l1content = WebUtil.getWebPageContent(l1Url);
            String comUrl = RegexUtils.getMatchString(l1content, " <a href=\"([^\"]*)\" target=\"_blank\">[^<]*</a>");
            if(comUrl ==null || !comUrl.startsWith("http")){
                continue;
            }
            
            String comPageContent = WebUtil.getWebPageContent(comUrl);
            
//            System.out.println(comPageContent);
            
            String personName = RegexUtils.getMatchString(comPageContent, "<th>联系人</th>[^<]*<td> ([^<]*)</td>");
            
//            String telImgUrl = RegexUtils.getMatchString(comPageContent, "<td class=\"telNum\"> <img src=\"([^\"]*)\"");
//            String emailImgUrl = RegexUtils.getMatchString(comPageContent,"<th>邮箱</th>[^<]*<td><img src=\"([^\"]*)\"");
//            telImgUrl = telImgUrl.replaceAll("amp;", "");
//            emailImgUrl = emailImgUrl.replaceAll("amp;", "");
////            System.out.println(telImgUrl);
////            String userDir = System.getProperty("");
//            
//            WebUtil.downloadFile(telImgUrl, "F:/tel.gif");
//            WebUtil.downloadFile(emailImgUrl,  "f:/email.gif");
//            
//            OCR ocr = new OCR();
//            
//            String telString = ocr.recognizeText("F:/tel.gif", "gif");
//            telString = telString.replaceAll("B", "13");
//            String emailString = ocr.recognizeText("f:/email.gif", "gif");
            
//            System.out.println(personName);
//            System.out.println(telString);
//            System.out.println(emailString);
//            System.out.println(comUrl);
            buffer.append(comName).append(",").append(comUrl).append(",").append(personName).append("\n");
        }
        System.out.println(buffer.toString());
    }
    
    public static void main1(final String[] args) {
        
    }

}

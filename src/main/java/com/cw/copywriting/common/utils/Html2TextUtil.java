package com.cw.copywriting.common.utils;

import org.springframework.web.util.HtmlUtils;

import java.util.regex.Pattern;

/**
 * @author LiaoZiYang
 * @version 1.0
 * @date 2020/5/24 16:18
 * @Desc
 */
public class Html2TextUtil {

    public static void main(String[] args) {
        StringBuffer htmlStr = new StringBuffer();
        htmlStr.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>")
                .append("<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en'><head><title>aaa</title><mce:script type='text/javascript'></mce:script>")
                .append("<link href=\"static_files/help.css\" mce_href=\"static_files/help.css\" rel='stylesheet' type='text/css' media='all' />")
                .append("</head><body><ul><li>XXXX</li></ul></body></html>");
        System.out.println(HtmlUtils.htmlEscape(htmlStr.toString()));
        System.out.println(Html2Text(htmlStr.toString()));

        String str = "<了>考虑卢卡斯打翻了卡萨丁上了课大风\n" +
                "\n" +
                "\n" +
                "\t<img src=\"/images/newsContent/3.jpg\" alt=\"\" />\n" +
                "\n" +
                "\n" +
                "\t卢卡斯大风了卡萨丁飞卢卡斯大风卢卡斯大风洛克斯";
        System.out.println(HtmlUtils.htmlEscape(str));
        String t = Html2TextUtil.Html2Text(str);
        System.out.println("t = " + t);

    }
    public static String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        java.util.regex.Pattern p_html1;
        java.util.regex.Matcher m_html1;

        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2TextUtil: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }
}

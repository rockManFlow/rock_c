package com.kuark.tool.base.reptile;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by cc on 2016/5/24.
 * 用来获得网页中的超链接（包括a标签，frame中的src等等），即为了得到子节点的URL。
 * 需要引入htmlparser.jar---html解析器
 */
public class HtmlParserTool {
    // 获取一个网站上的链接,filter 用来过滤链接
    public static Set<String> extracLinks(String url, LinkFilter filter) {

        Set<String> links = new HashSet<String>();
        try {
            Parser parser = new Parser(url);
            //parser.setEncoding("utf-8");
            // 过滤 <frame >标签的 filter，用来提取 frame 标签里的 src 属性所表示的链接
            NodeFilter frameFilter = new NodeFilter() {
                public boolean accept(Node node) {
                    if (node.getText().startsWith("<frame")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            // OrFilter 来设置过滤 <a> 标签，和 <frame> 标签
            OrFilter linkFilter = new OrFilter(new NodeClassFilter(
                    LinkTag.class), frameFilter);
            // 得到所有经过过滤的标签
            NodeList list = parser.extractAllNodesThatMatch(linkFilter);
            for (int i = 0; i < list.size(); i++) {
                Node tag = list.elementAt(i);
                if (tag instanceof LinkTag)// <a> 链接标签
                {
                    LinkTag link = (LinkTag) tag;
                    String linkUrl = link.getLink();// 链接url
                    if (filter.accept(linkUrl))
                        links.add(linkUrl);
                } else// <frame> 标签
                {
                    // 提取 frame 里 src 属性的链接如 <frame src="test.html" />
                    String frame = tag.getText();
                    int start = frame.indexOf("src=");
                    frame = frame.substring(start);
                    int end = frame.indexOf(" ");
                    if (end == -1)
                        end = frame.indexOf(">");
                    String frameUrl = frame.substring(5, end - 1);
                    if (filter.accept(frameUrl))
                        links.add(frameUrl);
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return links;
    }
}

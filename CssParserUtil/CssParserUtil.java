package cn.com.demos.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.dom.CSSStyleRuleImpl;
import com.steadystate.css.dom.CSSStyleSheetImpl;
import com.steadystate.css.format.CSSFormat;
import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;

/**
 * 
 * @author 13521
 *
 */
public class CssParserUtil {

	/**
	 * 打印样式文件内容
	 * @param filePath 样式本地文件路径
	 * @param selectorText 属性名称
	 * @return
	 */
	public static boolean showCssText(InputStream inStream) {
		try {
			InputSource source = new InputSource();
			source.setByteStream(inStream);
			source.setEncoding("UTF-8");
			final CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			CSSStyleSheet sheet = parser.parseStyleSheet(source, null, null);
			CSSRuleList rules = sheet.getCssRules();
			if (rules.getLength() == 0) {
				return false;
			}

			for (int i = 0; i < rules.getLength(); i++) {
				final CSSRule rule = rules.item(i);
				// 获取样式名称
				//String selectorText_ = ((CSSStyleRule) rule).getSelectorText();
				// 获取样式内容
				String cssText = ((CSSStyleRule) rule).getCssText();
				System.out.println(cssText);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 检查样式属性是否存在 
	 * @param filePath 样式本地文件路径
 	 * @param selectorText 属性名称 
 	 * @return
	 */
	public static boolean checkSelectorText(InputStream inStream, String selectorText) {
		try {
			InputSource source = new InputSource();
			source.setByteStream(inStream);
			source.setEncoding("UTF-8");
			final CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			CSSStyleSheet sheet = parser.parseStyleSheet(source, null, null);
			CSSRuleList rules = sheet.getCssRules();
			if (rules.getLength() == 0) {
				return false;
			}

			for (int i = 0; i < rules.getLength(); i++) {
				final CSSRule rule = rules.item(i);
				// 获取样式名称
				String selectorTextInner = ((CSSStyleRule) rule).getSelectorText();
				// 获取样式内容
				//String cssText = ((CSSStyleRule) rule).getCssText();
				if (selectorText.equals(selectorTextInner)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 *查询样式文件中选择器名称下面的属性是否存在 
	 * @param inStream css文件输入流 
	 * @param selectorText 选择器名称
	 * @param property属性名称
	 * @return
	 */
	public static boolean checkCssProperty(InputStream inStream, String selectorText, String property) {
		try {
			InputSource source = new InputSource();
			source.setByteStream(inStream);
			source.setEncoding("UTF-8");
			final CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			CSSStyleSheet sheet = parser.parseStyleSheet(source, null, null);
			CSSRuleList rules = sheet.getCssRules();
			if (rules.getLength() == 0) {
				return false;
			}
			for (int i = 0; i < rules.getLength(); i++) {
				final CSSRule rule = rules.item(i);
				// 获取选择器名称
				String selectorTextInner = ((CSSStyleRule) rule).getSelectorText();
				// 获取样式内容
				//String cssText = ((CSSStyleRule) rule).getCssText();

				if (selectorText.equals(selectorTextInner)) {
					CSSStyleDeclaration ss = ((CSSStyleRule) rule).getStyle();
					String propertyValue = ss.getPropertyValue(property);
					if ("".equals(propertyValue) || propertyValue == null) {
						return false;
					}
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 添加新样式 
	 * @param inStream css文件输入流
	 * @param rule 增加的一段css样式
	 * @return
	 */
	public static CSSStyleSheet insertRule(InputStream inStream, String rule) {
		CSSStyleSheet sheet = null;
		try {
			InputSource source = new InputSource();
			source.setByteStream(inStream);
			source.setEncoding("UTF-8");
			final CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			sheet = parser.parseStyleSheet(source, null, null);
			CSSRuleList rules = sheet.getCssRules();
			if (rules.getLength() == 0) {
				return null;
			}
			sheet.insertRule(rule, rules.getLength());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sheet;
	}

	/**
	 *  更新选择器样式
	 * @param inStream css文件输入流
	 * @param selectorText 选择器名称
	 * @param ruleText 更新选择器下所有样式内容 
	 * @return
	 */
	public static CSSStyleSheet updateSelectorAllRuleText(InputStream inStream, String selectorText, String ruleText) {
		CSSStyleSheet sheet = null;
		try {
			InputSource source = new InputSource();
			source.setByteStream(inStream);
			source.setEncoding("UTF-8");
			final CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			sheet = parser.parseStyleSheet(source, null, null);
			CSSRuleList rules = sheet.getCssRules();
			if (rules.getLength() == 0) {
				return null;
			}
			for (int i = 0; i < rules.getLength(); i++) {
				final CSSRule rule = rules.item(i);
				// 获取选择器名称
				String selectorTextInner = ((CSSStyleRule) rule).getSelectorText();
				// 获取样式内容
				//String cssText = ((CSSStyleRule) rule).getCssText();
				if (selectorText.equals(selectorTextInner)) {
					((CSSStyleRule) rule).getStyle().setCssText(ruleText);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sheet;
	}

	/**
	 * 删除选择器样式 
	 *@param inStream css文件输入流
	 *@param selectorText 选择器名称
     *@return
	 */
	public static CSSStyleSheet deleteRule(InputStream inStream, String selectorText) {
		CSSStyleSheet sheet = null;
		try {
			InputSource source = new InputSource();
			source.setByteStream(inStream);
			source.setEncoding("UTF-8");
			final CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			sheet = parser.parseStyleSheet(source, null, null);
			CSSRuleList rules = sheet.getCssRules();
			if (rules.getLength() == 0) {
				return null;
			}
			for (int i = 0; i < rules.getLength(); i++) {
				final CSSRule rule = rules.item(i);
				// 获取选择器名称
				String selectorTextInner = ((CSSStyleRule) rule).getSelectorText();
				// 获取样式内容
				//String cssText = ((CSSStyleRule) rule).getCssText();

				if (selectorText.equals(selectorTextInner)) {
					sheet.deleteRule(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sheet;
	}

	/**
	 * 添加或者更新选择器属性 
	 * @param inStream文件输入流 
	 * @param selectorText 选择器名称
	 * @param propertyName 添加或者更新的属性名称 
	 * @param propertyValue 属性值
	 * @param priority优先级例如  "important",或者空 
	 * @return
	 */
	public static CSSStyleSheetImpl addOrUpdateRuleProperty(InputStream inStream, String selectorText, String propertyName,
			String propertyValue, String priority) {
		CSSStyleSheetImpl sheet = null;
		try {
			InputSource source = new InputSource();
			source.setByteStream(inStream);
			source.setEncoding("UTF-8");
			//final CSSOMParser parser = new CSSOMParser();
			final CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			//sheet = parser.parseStyleSheet(source, null, null);
			sheet = (CSSStyleSheetImpl) parser.parseStyleSheet(source, null, null);
			sheet.getCssText(new CSSFormat().setRgbAsHex(true));
			CSSRuleList rules = sheet.getCssRules();
			if (rules.getLength() == 0) {
				return null;
			}
			for (int i = 0; i < rules.getLength(); i++) {
				final CSSRule rule = rules.item(i);
				// 获取选择器名称
				String selectorTextInner = ((CSSStyleRuleImpl) rule).getSelectorText();
				// 获取样式内容
				//String cssText = ((CSSStyleRule) rule).getCssText();
				if (selectorText.equals(selectorTextInner)) {
					CSSStyleDeclaration cd = ((CSSStyleRuleImpl) rule).getStyle();
					cd.setProperty(propertyName, propertyValue, priority);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sheet;
	}
	
	
	public static void main(String[] args) throws IOException {
		/**
		 URL url = new URL("网络地址");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		InputStream inStream = connection.getInputStream();
		*/
		 InputStream inStream = new FileInputStream(new File("d:/custom_theme_1563415199578.css"));
		 //System.out.println("length: "+inStream.available());
		 //byte[] buffer = new byte[inStream.available()];
		 //inStream.read(buffer);
		//InputStream inStream = new FileInputStream(new File("D:/test.css"));
		CSSStyleSheetImpl sheet = addOrUpdateRuleProperty(inStream,".ivu-menu-dark.ivu-menu-vertical .ivu-menu-item:hover, .ivu-menu-dark.ivu-menu-vertical .ivu-menu-submenu-title:hover","background-color","#777777",null);

		//CSSStyleSheet sheet = insertRule(inStream, ".div{width:100px;height:200px;}");
		
		//CSSStyleSheet sheet = updateSelectorAllRuleText(inStream,".div","height:100px;");
		//String cssRule = sheet.getCssRules().toString();
		String cssRule =sheet.getCssText(new CSSFormat().setRgbAsHex(true));
		System.out.println("cssRule:\n"+cssRule);
		FileOutputStream out = new FileOutputStream("D://test1.css");
		out.write(cssRule.getBytes());
		out.close();
		/**
		InputSource source = new InputSource(new StringReader("h1{background:rgb(7,42,0)}"));
		CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
		CSSStyleSheetImpl sheetImpl = (CSSStyleSheetImpl) parser.parseStyleSheet(source, null, null);
		*/
		//checkSelectorText(inStream,".table");
		//checkCssProperty(inStream,".table","height");
	}
}
package com.example.administrator.shadowapplication.db;

import android.util.Log;

import com.example.administrator.shadowapplication.crash_log.LogUtil;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/3/7
 *     desc   :
 * </pre>
 */


public class SaxHandle extends DefaultHandler {

    private StringBuilder id, name, version;
    private String nodeName;

    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();

    }

    @Override
    public void endDocument() throws SAXException {

    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        nodeName = localName;


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("app")) {
            LogUtil.d("hh", "sax id is " + id.toString().trim());
            LogUtil.d("hh", "sax name is " + name.toString().trim());
            LogUtil.d("hh", "sax version is " + version.toString().trim());
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (nodeName.equals("id")) {
            id.append(ch, start, length);
        } else if (nodeName.equals("name")) {
            name.append(ch, start, length);
        } else if (nodeName.startsWith("version")) {
            version.append(ch, start, length);
        }
    }

}

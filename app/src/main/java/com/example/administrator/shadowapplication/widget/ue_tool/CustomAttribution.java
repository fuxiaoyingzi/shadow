package com.example.administrator.shadowapplication.widget.ue_tool;

import java.util.ArrayList;
import java.util.List;

import me.ele.uetool.base.Element;
import me.ele.uetool.base.IAttrs;
import me.ele.uetool.base.item.Item;
import me.ele.uetool.base.item.TextItem;

/**
 * 自定义实现你的 View 的属性
 */
public class CustomAttribution implements IAttrs {

    @Override
    public List<Item> getAttrs(Element element) {
        List<Item> items = new ArrayList<>();
        if (element.getView() instanceof CustomUEView) {
            CustomUEView view = (CustomUEView) element.getView();
            items.add(new TextItem("More", view.getMoreAttribution()));
        }
        return items;
    }
}

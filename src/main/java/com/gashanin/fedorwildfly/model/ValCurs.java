package com.gashanin.fedorwildfly.model;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Data
@Root(name = "ValCurs")
public class ValCurs {
    @ElementList(inline = true, required = false)
    public List<Valute> valute;
    @Attribute(name = "Date")
    public String date;
    @Attribute(name = "name")
    public String name;
}

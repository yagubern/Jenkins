package hello;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "ValCurs")

public class Valutes {

    @JacksonXmlProperty(isAttribute = true, localName = "Date")
    @Getter
    private String Date;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    @Getter
    private List<Valute> valutes = new ArrayList<>();
}

package hello;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;

public class Valute {

    @Getter
    @JacksonXmlProperty(localName = "NumCode")
    private String numCode;

    @Getter
    @JacksonXmlProperty(localName = "CharCode")
    private String charCode;

    @Getter
    @JacksonXmlProperty(localName = "Nominal")
    private int nominal;

    @Getter
    @JacksonXmlProperty(localName = "Name")
    private String name;

    @Getter
    @JacksonXmlProperty(localName = "Value")
    private String value;
}
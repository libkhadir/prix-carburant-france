package com.price.explorer.priceexplorer.util;

import com.price.explorer.priceexplorer.model.fuel.DataStationList;
import lombok.SneakyThrows;

import javax.xml.bind.JAXBContext;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;

public class XmlUtils {

    @SneakyThrows
    public static DataStationList unmarshal(final String inputFile) {
        var jaxbContext = JAXBContext.newInstance(DataStationList.class);
        var unmarshaller = jaxbContext.createUnmarshaller();
        FileInputStream is = null;
        try {
            is = new FileInputStream(inputFile);
            var result = unmarshaller.unmarshal(new StreamSource(is), DataStationList.class);
            return result.getValue();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}

package com.project1Test.data;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DataReader {

    public void getJsonDataToMap() throws IOException {
       String jsonContent =  FileUtils.readFileToString(new File("/Users/aishwaryaguledgudda/Projects/SeleniumProject1/src/test/java/com/project1Test/data/PurchaseOrder.json"));

    }
}

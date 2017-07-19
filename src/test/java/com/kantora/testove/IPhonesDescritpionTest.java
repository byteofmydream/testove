package com.kantora.testove;

import com.kantora.testove.POs.ProductListingPO;
import org.junit.Assert;
import org.junit.Test;
import utils.DBUtil;

public class IPhonesDescritpionTest {
    @Test
    public void testIPhoneDescriptions(){
        ProductListingPO productListingPO = new ProductListingPO();
        productListingPO.closePopup();
        productListingPO.searchForItem("Apple iPhone 7");

        String[] iphoneSpecs = productListingPO.getIPhone7Details().split("/");
        String[] iphonePlusSpecs = productListingPO.getIPhone7PlusDetails().split("/");

        Assert.assertTrue(iphonePlusSpecs[0].contains("4.7\""));
        Assert.assertTrue(iphoneSpecs[0].contains("5.5\""));

        Assert.assertTrue(iphonePlusSpecs[3].contains("RAM 3"));
        Assert.assertTrue(iphoneSpecs[3].contains("RAM 2"));

        //Still missing weight assertion

        DBUtil.storeResult(productListingPO.getIPhone7Price(), productListingPO.getIPhone7PlusPrice());
    }
}

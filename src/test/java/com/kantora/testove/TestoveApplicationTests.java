package com.kantora.testove;

import com.kantora.testove.POs.ConfirmMailPO;
import com.kantora.testove.POs.RegistrationPO;
import com.kantora.testove.components.TempMailApiRestHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utils.StringUtils;
import utils.TestDataProvider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestoveApplicationTests {

    @Autowired
    private TempMailApiRestHandler tempMailApiRestHandler;

    private String password;
    private String email;
    private String name;

    @Before
    public void precondition() {
        email = TestDataProvider.getData().getEmail();
        password = TestDataProvider.getData().getPassword();
        name = TestDataProvider.getData().getName();
    }

    @Test
    public void test1() {

        RegistrationPO registrationPO = new RegistrationPO();
        registrationPO.closePopup();
        registrationPO.enterRegistrationData(name, email, password)
                .submitRegistration();

        String confirmMailLink = StringUtils.getConfirmMailLink(tempMailApiRestHandler.getConfirmLinkFromMail());

        ConfirmMailPO confirmMailPO = new ConfirmMailPO(confirmMailLink);
        confirmMailPO.closePopup();
        String emailFromPage = confirmMailPO.getEmail();

        Assert.assertEquals(emailFromPage, email);
    }
}

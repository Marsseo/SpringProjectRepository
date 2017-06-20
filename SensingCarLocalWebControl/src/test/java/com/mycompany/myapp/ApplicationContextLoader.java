package com.mycompany.myapp;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({
	"file:WebContent/WEB-INF/spring/applicationContext.xml", 
	"file:WebContent/WEB-INF/spring/dispatcher-servlet.xml"
})

// @WebAppConfiguration -> 웹컨텐트가 src/main/webapp/WEB-INF에 존재 (STS의 경우 이 경로로 지정하기 때문)
@WebAppConfiguration("WebContent")
public class ApplicationContextLoader {
	
}

package gdsc.netwalk;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
 * web.xml에 DispatcherServlet 등록 작업을 대신해
 * web.xml 설정을 WebApplicationInitializer 를 구현
 * (SpringBootServletInitializer 클래스는 WebApplicationInitializer 인터페이스의 구현체)
 * */
public class ServletInitializer extends SpringBootServletInitializer {

	private static final String CONFIG_LOCATION = "gdsc.netwalk.common.config";
	private static final String MAPPING_URL = "/";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// web.xml 파일 업시 war 확장자로 배포
		return application.sources(NetwalkApplication.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(MAPPING_URL);
	}

	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(CONFIG_LOCATION);
		return context;
	}
}

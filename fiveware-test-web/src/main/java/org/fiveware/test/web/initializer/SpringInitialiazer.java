package org.fiveware.test.web.initializer;

public class SpringInitialiazer { //
	/*
	extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {		
		return new Class<?>[] { SpringMVCConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {		
		return new String[]{"/*"};
	}
	*/

	/*
	implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setServletContext(servletContext);
		context.register(SpringMVCConfiguration.class);
		Dynamic servlet = servletContext.
				addServlet("dispatcherServlet", new DispatcherServlet(context));
		servletContext.addListener(new ContextLoaderListener(context));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		System.out.println("Inicializando Spring MVC via Codificação...");
		
	}
	*/
}

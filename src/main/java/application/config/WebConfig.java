package application.config;


import application.data.service.*;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;


/**
 * Created by ManhNguyen on 1/17/18.
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LogManager.getLogger(WebConfig.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Autowired
    private ThymeleafProperties properties;

    @Value("${spring.thymeleaf.templates_root:}")
    private String templatesRoot;

    @Bean
    public ITemplateResolver defaultTemplateResolver() {
        logger.debug("run defaultTemplateResolver");

        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());
        return resolver;
    }

    @Bean
    public OrderService getOrderService(){
        return new OrderService();
    }

    @Bean
    public MaterialService getMaterialService(){
        return new MaterialService();
    }

    @Bean
    public ContactService getContactService() {
        return new ContactService();
    }

    @Bean
    public MaterialHistoryService getMaterialHistoryService(){
        return new MaterialHistoryService();
    }

    @Bean
    public CartProductService getCartProductService(){
        return new CartProductService();
    }

    @Bean
    public TableService getTableService() {
        return new TableService();
    }

    @Bean
    public CartService getCartService(){
        return new CartService();
    }

    @Bean
    public ProductService getProductService() {
        return new ProductService();
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public ProductImageService getProductImageService(){
        return new ProductImageService();
    }

    @Bean
    public BookerService getBookerService(){
        return new BookerService();
    }

    @Bean(name = "userService")
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    public ProductCategoryService ProductCategoryService(){
        return new ProductCategoryService();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry ) {
        logger.debug("run addResourceHandlers");

        registry.addResourceHandler(
                "/webjars/**",
                "/static/img/**",
                "/static/css/**",
                "/static/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }

    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }

    /**
     * Fix bean cookie
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer customizer() {
        return container -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
                tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));
            }
        };
    }

}

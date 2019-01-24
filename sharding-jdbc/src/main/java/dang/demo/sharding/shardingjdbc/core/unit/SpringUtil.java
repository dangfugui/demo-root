package dang.demo.sharding.shardingjdbc.core.unit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Description: Spring 工具类
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取spring 上下文
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名称获取Spring Bean
     *
     * @param name bean 名称
     * @param <T>
     * @return Bean
     */
    public static <T> T getBean(String name) {
        try {
            return (T) applicationContext.getBean(name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据class 类型获取bean
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> tClass) {
        try {
            return applicationContext.getBean(tClass);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }
}

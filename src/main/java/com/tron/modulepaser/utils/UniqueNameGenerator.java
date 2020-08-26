package com.tron.modulepaser.utils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

public class UniqueNameGenerator extends AnnotationBeanNameGenerator {
    public UniqueNameGenerator() {
    }

    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String beanName = definition.getBeanClassName();
        return beanName;
    }
}
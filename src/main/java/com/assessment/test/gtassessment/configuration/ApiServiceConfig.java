package com.assessment.test.gtassessment.configuration;


import com.assessment.test.gtassessment.repository.DepartmentRepository;
import com.assessment.test.gtassessment.repository.EmployeeRepository;
import com.assessment.test.gtassessment.service.DepartmentService;
import com.assessment.test.gtassessment.service.DepartmentServiceImpl;
import com.assessment.test.gtassessment.service.EmployeeService;
import com.assessment.test.gtassessment.service.EmployeeServiceImpl;
import com.assessment.test.gtassessment.utils.i18.api.MessageService;
import com.assessment.test.gtassessment.utils.i18.impl.MessageServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ApiServiceConfig {


    @Bean(name = "customMessageSource")
    public MessageSource customMessageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    @DependsOn("customMessageSource")
    MessageService messageService(MessageSource customMessageSource) {
        return new MessageServiceImpl(customMessageSource);
    }

    @Bean
    public DepartmentService departmentService(DepartmentRepository departmentRepository, ModelMapper mapper){
        return new DepartmentServiceImpl(departmentRepository, mapper);
    }
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    @Bean
    public EmployeeService employeeService(EmployeeRepository employeeRepository, ModelMapper mapper){
        return new EmployeeServiceImpl(employeeRepository, mapper);
    }

}

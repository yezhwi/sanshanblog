package com.sanshan.web.config.javaconfig.auxiliary;

import com.sanshan.web.controller.index.MultipartFileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MultipartFileBucketValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFileBucket.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        MultipartFileBucket multipartFileBucket = (MultipartFileBucket) obj;
        if (multipartFileBucket.getMultipartFile() != null) {
            if (multipartFileBucket.getMultipartFile().getSize() == 0) {
                errors.rejectValue("multipartFile",
                        "MultipartFileBucketValidator.multipartFileBucket.multipartFile",
                        "请选择需上传的文件");
            }
        }
    }
}
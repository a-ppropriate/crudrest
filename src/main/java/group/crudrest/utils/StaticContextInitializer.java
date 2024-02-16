package group.crudrest.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class StaticContextInitializer { // TODO: check/ask if this sht is safe to use in such way

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        MappingUtil.setMapper(modelMapper);
    }
}

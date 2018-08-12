package com.wgdetective.projectstartdemo.converter;

public interface DtoConverter<T,B> {
    T convertToDto(final B dbo);
    B convertToDbo(final T dto);
}

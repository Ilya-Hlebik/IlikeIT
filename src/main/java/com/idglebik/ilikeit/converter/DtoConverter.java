package com.idglebik.ilikeit.converter;

public interface DtoConverter<T,B> {
    T convertToDto(final B dbo);
    B convertToDbo(final T dto);
}

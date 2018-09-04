package com.idglebik.ilikeit.converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface DtoConverter<T, B> {
    T convertToDto(final B dbo);

    B convertToDbo(final T dto);

    default Set<T> convertToDto(final Set<B> dbo) {
        return dbo != null ? dbo.stream().map(this::convertToDto).collect(Collectors.toSet()) : null;
    }

    default Set<B> convertToDbo(final Set<T> dto) {
        return dto != null ? dto.stream().map(this::convertToDbo).collect(Collectors.toSet()) : null;
    }

    default List<T> convertToDto(final List<B> dbo) {
        return dbo != null ? dbo.stream().map(this::convertToDto).collect(Collectors.toList()) : null;
    }
}

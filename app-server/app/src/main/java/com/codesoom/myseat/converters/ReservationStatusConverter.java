package com.codesoom.myseat.converters;

import com.codesoom.myseat.enums.ReservationStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/** DB에 저장된 값과, 정의한 ReservationStatus Enum을 매핑합니다. */
@Converter
public class ReservationStatusConverter 
        implements AttributeConverter<ReservationStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(
            ReservationStatus attribute
    ) {
        return attribute.getNum();
    }

    @Override
    public ReservationStatus convertToEntityAttribute(
            Integer dbData
    ) {
        return ReservationStatus.ofNum(dbData);
    }

}

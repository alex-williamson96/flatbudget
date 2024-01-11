package com.caliber.flatbudget.dtos.payee;

import com.caliber.flatbudget.models.Payee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PayeeMapper {

    PayeeMapper INSTANCE = Mappers.getMapper(PayeeMapper.class);

    Payee payeeDtoToPayee(PayeeDto payeeDto);

    PayeeDto payeeToPayeeDto(Payee payee);
}

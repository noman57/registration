package com.starter.registration.annotation.validator;

import com.starter.registration.annotation.ValueOfEnum;
import lombok.SneakyThrows;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class EnumClassValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {

   private List<String> acceptedValues;
   public void initialize(ValueOfEnum annotation){
      this.acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
        .map(Enum::toString)
        .collect(Collectors.toList());
   }

   @SneakyThrows
   @Override
   public boolean isValid(CharSequence choice , ConstraintValidatorContext context) {
      return this.acceptedValues.contains(choice.toString());
   }
}

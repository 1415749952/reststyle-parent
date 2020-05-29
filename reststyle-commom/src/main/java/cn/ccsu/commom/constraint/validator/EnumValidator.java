package cn.ccsu.commom.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import cn.ccsu.commom.constraint.validator.enums.ValidateEnum;
/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 16:39
 */
public class EnumValidator implements ConstraintValidator<ValidateEnum, Object>
{

    private ValidateEnum annotation;

    @Override
    public void initialize(ValidateEnum constraintAnnotation)
    {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context)
    {
        if (value == null)
        {
            return false;
        }

        Object[] objects = annotation.clazz().getEnumConstants();
        try
        {
            Method method = annotation.clazz().getMethod(annotation.method());
            for (Object o : objects)
            {
                if (value.equals(method.invoke(o)))
                {
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return false;
    }
}
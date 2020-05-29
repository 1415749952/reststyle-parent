package cn.ccsu.commom.constraint.validator;

import cn.ccsu.commom.constraint.validator.enums.ValidCorn;
import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 17:17
 */
public class CornValidator implements ConstraintValidator<ValidCorn, String>
{

    @Override
    public void initialize(ValidCorn constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if (!CronExpression.isValidExpression(value))
        {
            return false;
        }
        return true;
    }
}

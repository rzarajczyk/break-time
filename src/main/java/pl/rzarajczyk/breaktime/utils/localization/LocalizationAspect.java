package pl.rzarajczyk.breaktime.utils.localization;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rafalz
 */
@Aspect
public class LocalizationAspect {

    @Autowired
    private Localization localization;

    @Before(value="@annotation(pl.rzarajczyk.breaktime.utils.localization.LocalizationPoint)")
    public void localize(JoinPoint joinPoint) {
        if ( shouldLocalize(joinPoint) ) {
            Localizable localizable = (Localizable) joinPoint.getThis();
            localization.localize(localizable);
        }
    }

    private boolean shouldLocalize(JoinPoint joinPoint) {
        Object self = joinPoint.getThis();
        return self instanceof Localizable;
    }

}

package redis.cep.dao.jedis;

import static java.lang.annotation.ElementType.FIELD;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface CepConnection {

}

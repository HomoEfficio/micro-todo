package homo.efficio.micro.todo.member.util;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 5. 4.
 */
public class ConverterUtils {

    public static <M, D> List<D> getDtosFrom(List<M> models, Function<M, D> converter) {

        if (models.isEmpty())
            return Collections.emptyList();

        List<D> dtos = models.stream()
                .map(converter)
                .collect(Collectors.toList());

        return dtos;
    }
}

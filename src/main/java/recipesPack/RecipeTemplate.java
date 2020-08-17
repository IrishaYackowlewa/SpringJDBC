package recipesPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class RecipeTemplate {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeTemplate(DataSource dataSource){
        this.jdbcTemplate =
                new NamedParameterJdbcTemplate(dataSource);
    }

    public void testDb(Recipe recipe){
        BeanPropertySqlParameterSource params =
                new BeanPropertySqlParameterSource(recipe);
        jdbcTemplate.update(
                "insert into recipes(ID, DESCRIPTION, RECIPE)" +
                        "VALUES(:id, :description, :recipe)",
                params
        );
    }
}

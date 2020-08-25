package recipesPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                "insert into recipes(DESCRIPTION, RECIPE)" +
                        "VALUES(:description, :recipe)",
                params
        );
    }

    public void batchInsert(ArrayList<Recipe> recipes){
        Object[] arrayRecipe = recipes.toArray();
        BeanPropertySqlParameterSource[] params = Arrays.stream(arrayRecipe)
                .map(BeanPropertySqlParameterSource::new)
                .toArray(BeanPropertySqlParameterSource[]::new);
        jdbcTemplate.batchUpdate(
                "insert into recipes(DESCRIPTION, RECIPE)" +
                        "VALUES(:description, :recipe)",
                params
        );
    }

    public List<Recipe> getByListDescription(String description){
        List<Recipe> recipes = jdbcTemplate.query(
                "select Id, Description, Recipe from RECIPES where description like :description",
                new MapSqlParameterSource("description", "%"+description +"%"),
                (rs, rn)-> new Recipe(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
        return recipes;
    }

    public void deleteById(int id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update("delete from recipes where id = :id", params);
    }
}

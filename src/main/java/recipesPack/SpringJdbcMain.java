package recipesPack;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringJdbcMain {

    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(Config.class);

        RecipeTemplate recipeService = ctx.getBean(RecipeTemplate.class);
        recipeService.testDb(new Recipe(1, "Яичница", "Яйцо - 2 шт.," ));
    }
}

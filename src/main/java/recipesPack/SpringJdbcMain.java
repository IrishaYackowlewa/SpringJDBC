package recipesPack;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class SpringJdbcMain {

    public static void main(String[] args){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Recipe> listRecipe = new ArrayList<>();
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(Config.class);

        RecipeTemplate recipeService = ctx.getBean(RecipeTemplate.class);
        while (true){

            System.out.println("Выберите действие\n" +
                    "Введите 1 - если хотите добавить рецепт,\n" +
                    "Введите 2 - если хотите вывести рецепт\n" +
                    "Введите 3 - если хотите удалить рецепт\n" +
                    "Введите 0 - если хотите закончить");
            try {
                int selectedService = Integer.parseInt(bufferedReader.readLine());
                if (selectedService == 0) break;
                switch (selectedService) {
                    case (1):
                        while (true) {
                            System.out.println("Введите название блюда или \"конец\" - ");
                            String description = bufferedReader.readLine();

                            if (description.equals("конец"))
                                break;

                            System.out.println("Введите рецепт - ");
                            String recipe = bufferedReader.readLine();
                            listRecipe.add(new Recipe(description, recipe));
                        }

                        try {
                            recipeService.batchInsert(listRecipe);
                        } catch (Exception e) {
                            System.out.println("Я сломалься 1");
                        }

                        break;

                    case (2):
                        System.out.println("Введите название блюда или его часть - ");
                        String description = bufferedReader.readLine();
                        try {
                            List<Recipe> recipes = recipeService.getByListDescription(description);
                            if (recipes.size() == 0)
                                System.out.println("Нет такого блюда");
                            for (Recipe recipe : recipes) {
                                System.out.println(recipe);
                            }
                        } catch (Exception e) {
                            System.out.println("Я сломалься 2");
                        }
                        break;

                    case (3):
                        System.out.println("Введите название блюда, которое нужно удалить - ");
                        String descripDel = bufferedReader.readLine();
                        try {
                            List<Recipe> recipes = recipeService.getByListDescription(descripDel);
                            if (recipes.size() > 0) {
                                System.out.println("Выберите id блюда, которое нужно удалить");
                                for (Recipe recipe : recipes) {
                                    System.out.println(recipe);
                                }
                                int id = Integer.parseInt(bufferedReader.readLine());
                                recipeService.deleteById(id);
                            }
                        } catch (Exception e) {
                            System.out.println("Я сломалься 3");
                        }
                        break;

                    default:
                        System.out.println("Пожалуйста, введите корректное число");
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("ошибка ввода");
            }
        }
    }
}

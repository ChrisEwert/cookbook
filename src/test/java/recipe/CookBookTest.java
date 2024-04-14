//package recipe;
//
//import recipe.CookBook;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class CookBookTest {
//
//    @Test
//    void testCreateNewRecipe() {
//        // ARRANGE
//        CookBook cookBook = new CookBook();
//        Recipe recipe = new Recipe();
//
//        // ACT
//        cookBook.createNewRecipe(recipe);
//
//        // ASSERT
//        assertThat(recipe.name()).isEqualTo("cookbook.Recipe name");
//        assertThat(recipe.author()).isEqualTo("Author");
//        assertThat(recipe.dateOfCreation()).isEqualTo(LocalDate.now());
//        assertThat(recipe.lastUpdated()).isNull();
//        assertThat(recipe.content()).isEqualTo("cookbook.Recipe content");
//        assertThat(recipe.categories()).isEqualTo(List.of());
//        assertThat(recipe.cookingTime()).isEqualTo(LocalTime.of(0, 0));
//        assertThat(recipe.rating()).isEqualTo(0);
//    }
//
//    @Test
//    void testUpdateRecipe() {
//        // ARRANGE
//        CookBook cookBook = new CookBook();
//        Recipe recipe = new Recipe();
//
//        // ACT
//        cookBook.createNewRecipe(recipe);
//        cookBook.updateRecipe(recipe);
//
//        // ASSERT
//        Recipe newRecipe = cookBook.getRecipe(recipe.id());
//        assertThat(newRecipe.lastUpdated()).isNotNull();
//    }
//}
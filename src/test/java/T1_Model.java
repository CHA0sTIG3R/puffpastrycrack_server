import com.cha0stig3r.recipe.server.model.Recipe;
import com.cha0stig3r.recipe.server.repository.RecipeRepository;
import com.cha0stig3r.recipe.server.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class T1_Model {
    @Autowired
    RecipeService service;

    private RecipeRepository repo;

    @Test
    void t1(){
        Iterable<Recipe> list = repo.findAll();
        list.forEach(System.out::println);
    }

}

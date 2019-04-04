package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "GROUND BEEF", Type.PROTEIN),
                new Ingredient("CARN", "CARNITAS", Type.PROTEIN),
                new Ingredient("TMTO", "DICED TOMATOES", Type.VEGGIES),
                new Ingredient("LETC", "LETTUCE", Type.VEGGIES),
                new Ingredient("CHED", "CHEDDAR", Type.CHEESE),
                new Ingredient("JACK", "MONTERREY JACK", Type.CHEESE),
                new Ingredient("SLSA", "SALSA", Type.SAUCE),
                new Ingredient("SRCR", "SOUR CREAM", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            //model.addAllAttributes(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }
}

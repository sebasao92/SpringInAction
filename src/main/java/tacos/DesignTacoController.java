package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import tacos.Ingredient.Type;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "FLOUR TORTILLA", Type.WRAP),
                new Ingredient("COTO", "CORN TORTILLA", Type.WRAP),
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
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors){

        if(errors.hasErrors()) {
            return "design";
        }

        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients
                .stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}

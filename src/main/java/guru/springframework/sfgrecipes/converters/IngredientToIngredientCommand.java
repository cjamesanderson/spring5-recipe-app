package guru.springframework.sfgrecipes.converters;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) return null;

        //todo: implement recipe parameter conversion
        IngredientCommand command = new IngredientCommand()
                .setId(source.getId())
                .setDescription(source.getDescription())
                .setAmount(source.getAmount())
                .setUom(unitOfMeasureToUnitOfMeasureCommand.convert(source.getUom()));

        if (source.getRecipe() != null) command.setRecipeId(source.getRecipe().getId());

        return command;
    }
}

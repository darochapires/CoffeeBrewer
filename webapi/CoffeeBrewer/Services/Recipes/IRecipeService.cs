using CoffeeBrewer.Models;
using ErrorOr;

namespace CoffeeBrewer.Services.Recipes;

public interface IRecipeService
{
    ErrorOr<Created> CreateRecipe(Recipe recipe);
    ErrorOr<Recipe> GetRecipe(Guid id);
    ErrorOr<UpsertedRecipe>  UpsertRecipe(Recipe recipe);
    ErrorOr<Deleted> DeleteRecipe(Guid id);
}
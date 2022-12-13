using CoffeeBrewer.Models;
using ErrorOr;

namespace CoffeeBrewer.Services.Recipes;

public interface IRecipeService
{
    ErrorOr<Created> CreateRecipe(Recipe recipe);
    ErrorOr<Recipe> GetRecipe(int id);
    ErrorOr<List<Recipe>> GetRecipesByMethod(int methodId);
    ErrorOr<Updated>  UpsertRecipe(Recipe recipe);
    ErrorOr<Deleted> DeleteRecipe(int id);
}
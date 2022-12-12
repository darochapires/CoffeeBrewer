using CoffeeBrewer.Models;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Services.Recipes
{
    public class RecipeService : IRecipeService
    {
        private static readonly Dictionary<int, Recipe> _recipes = new();

        public ErrorOr<Created> CreateRecipe(Recipe recipe)
        {
            _recipes.Add(recipe.Id, recipe);
            return Result.Created;
        }

        public ErrorOr<Recipe> GetRecipe(int id)
        {
            if(_recipes.TryGetValue(id, out var recipe))
            {
                return recipe;
            }
            return Errors.Recipe.NotFound;
        }

        public ErrorOr<UpsertedRecipe> UpsertRecipe(Recipe recipe)
        {
            var IsNewlyCreated = !_recipes.ContainsKey(recipe.Id);
            _recipes[recipe.Id] = recipe;
            return new UpsertedRecipe(IsNewlyCreated);
        }

        public ErrorOr<Deleted> DeleteRecipe(int id)
        {
            _recipes.Remove(id);
            return Result.Deleted;
        }
    }
}
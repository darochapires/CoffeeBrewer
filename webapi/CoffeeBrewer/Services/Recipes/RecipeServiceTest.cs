using CoffeeBrewer.Models;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Services.Recipes
{
    public class RecipeServiceTest : IRecipeService
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

        public ErrorOr<List<Recipe>> GetRecipesByMethod(int methodId)
        {
            return _recipes.Values.Where(r => r.Method.Id == methodId).ToList();
        }

        public ErrorOr<Updated> UpsertRecipe(Recipe recipe)
        {
            if(!_recipes.ContainsKey(recipe.Id))
            {
                return Errors.Method.NotFound;
            }
            _recipes[recipe.Id] = recipe;
            return Result.Updated;
        }

        public ErrorOr<Deleted> DeleteRecipe(int id)
        {
            _recipes.Remove(id);
            return Result.Deleted;
        }
    }
}
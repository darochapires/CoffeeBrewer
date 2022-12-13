using CoffeeBrewer.Database;
using CoffeeBrewer.Models;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Services.Recipes
{
    public class RecipeService : IRecipeService
    {
        private readonly CoffeeBrewerContext _context;

        public RecipeService(CoffeeBrewerContext context) 
        {
            _context = context;
        }

        public ErrorOr<Created> CreateRecipe(Recipe recipe)
        {
            _context.Add(recipe);
            if(!Save()) 
            {
                return Errors.Recipe.UnexpectedError;
            }
            return Result.Created;
        }

        public ErrorOr<Recipe> GetRecipe(int id)
        {
            var recipe = _context.Recipes.Where(r => r.Id == id).FirstOrDefault();
            return recipe == null ? Errors.Recipe.NotFound : recipe;
        }

        public ErrorOr<List<Recipe>> GetRecipesByMethod(int methodId)
        {
            return _context.Recipes.Where(r => r.Method.Id == methodId).ToList();
        }

        public ErrorOr<Updated> UpsertRecipe(Recipe recipe)
        {
            if(!_context.Recipes.Any(r => r.Id == recipe.Id)) 
            {
                return Errors.Recipe.NotFound;
            }            

            _context.Update(recipe);
            if(!Save()) 
            {
                return Errors.Recipe.UnexpectedError;
            }
            return Result.Updated;
        }

        public ErrorOr<Deleted> DeleteRecipe(int id)
        {
            var recipe = _context.Recipes.FirstOrDefault(r => r.Id == id);
            if(recipe == null)
            {
                return Errors.Recipe.NotFound;
            }

            _context.Remove(recipe);
            if(!Save()) 
            {
                return Errors.Recipe.UnexpectedError;
            }
            return Result.Deleted;
        }

        private bool Save()
        {
            var saved = _context.SaveChanges();
            return saved > 0 ? true : false;
        }
    }
}
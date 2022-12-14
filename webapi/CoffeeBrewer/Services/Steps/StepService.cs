using CoffeeBrewer.Database;
using CoffeeBrewer.Models;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Services.Steps
{
    public class StepService : IStepService
    {
        private readonly CoffeeBrewerContext _context;

        public StepService(CoffeeBrewerContext context) 
        {
            _context = context;
        }

        public ErrorOr<Created> CreateStep(Step step)
        {
            _context.Add(step);
            if(!Save()) 
            {
                return Errors.Step.UnexpectedError;
            }
            return Result.Created;
        }

        public ErrorOr<Step> GetStep(int id)
        {
            var step = _context.Steps.Where(r => r.Id == id).FirstOrDefault();
            return step == null ? Errors.Step.NotFound : step;
        }

        public ErrorOr<List<Step>> GetStepsByRecipe(int recipeId)
        {
            return _context.Steps.Where(s => s.RecipeId == recipeId).ToList();
        }

        public ErrorOr<Updated> UpsertStep(Step step)
        {
            if(!_context.Steps.Any(r => r.Id == step.Id)) 
            {
                return Errors.Step.NotFound;
            }            

            _context.Update(step);
            if(!Save()) 
            {
                return Errors.Step.UnexpectedError;
            }
            return Result.Updated;
        }

        public ErrorOr<Deleted> DeleteStep(int id)
        {
            var step = _context.Steps.FirstOrDefault(r => r.Id == id);
            if(step == null)
            {
                return Errors.Step.NotFound;
            }

            _context.Remove(step);
            if(!Save()) 
            {
                return Errors.Step.UnexpectedError;
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
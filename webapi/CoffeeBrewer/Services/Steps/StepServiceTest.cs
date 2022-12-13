using CoffeeBrewer.Models;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Services.Steps
{
    public class StepServiceTest : IStepService
    {
        private static readonly Dictionary<int, Step> _steps = new();

        public ErrorOr<Created> CreateStep(Step step)
        {
            _steps.Add(step.Id, step);
            return Result.Created;
        }

        public ErrorOr<Step> GetStep(int id)
        {
            if(_steps.TryGetValue(id, out var step))
            {
                return step;
            }
            return Errors.Step.NotFound;
        }

        public ErrorOr<List<Step>> GetStepsByRecipe(int recipeId)
        {
            return _steps.Values.Where(s => s.Recipe.Id == recipeId).ToList();
        }

        public ErrorOr<Updated> UpsertStep(Step step)
        {
            var IsNewlyCreated = !_steps.ContainsKey(step.Id);
            _steps[step.Id] = step;
            return Result.Updated;
        }

        public ErrorOr<Deleted> DeleteStep(int id)
        {
            _steps.Remove(id);
            return Result.Deleted;
        }
    }
}
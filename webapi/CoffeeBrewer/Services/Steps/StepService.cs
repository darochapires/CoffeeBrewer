using CoffeeBrewer.Models;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Services.Steps
{
    public class StepService : IStepService
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

        public ErrorOr<UpsertedStep> UpsertStep(Step step)
        {
            var IsNewlyCreated = !_steps.ContainsKey(step.Id);
            _steps[step.Id] = step;
            return new UpsertedStep(IsNewlyCreated);
        }

        public ErrorOr<Deleted> DeleteStep(int id)
        {
            _steps.Remove(id);
            return Result.Deleted;
        }
    }
}
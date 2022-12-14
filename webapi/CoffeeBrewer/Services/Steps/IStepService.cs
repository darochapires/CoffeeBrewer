using CoffeeBrewer.Models;
using ErrorOr;

namespace CoffeeBrewer.Services.Steps;

public interface IStepService
{
    ErrorOr<Created> CreateStep(Step step);
    ErrorOr<Step> GetStep(int id);
    ErrorOr<List<Step>> GetStepsByRecipe(int recipeId);
    ErrorOr<Updated>  UpsertStep(Step step);
    ErrorOr<Deleted> DeleteStep(int id);
}
using CoffeeBrewer.Models;
using ErrorOr;

namespace CoffeeBrewer.Services.Steps;

public interface IStepService
{
    ErrorOr<Created> CreateStep(Step step);
    ErrorOr<Step> GetStep(int id);
    ErrorOr<UpsertedStep>  UpsertStep(Step step);
    ErrorOr<Deleted> DeleteStep(int id);
}
using CoffeeBrewer.Contracts.Util;

namespace CoffeeBrewer.Contracts.CoffeeBrewer.Step;

public record StepResponse(
    int Id,
    int Order,
    StepType StepType,
    string Title,
    string? Description,
    int DurationInSeconds,
    int RecipeId,
    double? WaterAmount
);
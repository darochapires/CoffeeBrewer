namespace CoffeeBrewer.Contracts.CoffeeBrewer.Step;

public record CreateStepRequest(
    int Order,
    int StepType,
    string Title,
    string? Description,
    int DurationInSeconds,
    int RecipeId,
    double? WaterAmount
);
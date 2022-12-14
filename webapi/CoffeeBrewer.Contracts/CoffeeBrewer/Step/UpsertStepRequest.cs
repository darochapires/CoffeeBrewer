namespace CoffeeBrewer.Contracts.CoffeeBrewer.Step;

public record UpsertStepRequest(
    int Order,
    int StepType,
    string Title,
    string? Description,
    int DurationInSeconds,
    double? WaterAmount
);
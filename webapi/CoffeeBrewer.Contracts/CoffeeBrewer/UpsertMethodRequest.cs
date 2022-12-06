namespace CoffeeBrewer.Contracts.CoffeeBrewer;

public record UpsertMethodRequest(
    string Name,
    string Description
);
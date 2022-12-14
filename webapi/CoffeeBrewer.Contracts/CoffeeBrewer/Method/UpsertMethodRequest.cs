namespace CoffeeBrewer.Contracts.CoffeeBrewer.Method;

public record UpsertMethodRequest(
    string Name,
    string Description
);
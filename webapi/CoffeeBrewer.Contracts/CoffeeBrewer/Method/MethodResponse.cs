namespace CoffeeBrewer.Contracts.CoffeeBrewer.Method;

public record MethodResponse(
    int Id,
    string Name,
    string? Description
);
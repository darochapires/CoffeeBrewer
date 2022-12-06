namespace CoffeeBrewer.Contracts.CoffeeBrewer.Method;

public record MethodResponse(
    Guid Id,
    string Name,
    string Description,
    DateTime LastModifiedDateTime
);